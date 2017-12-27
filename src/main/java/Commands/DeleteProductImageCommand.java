/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import DAO.ProductImageDao;
import DTO.ProductImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ren
 */
public class DeleteProductImageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String forwardToJsp = null;
        boolean error = false;
        String msg = null;

        int img_id = Integer.parseInt(request.getParameter("imageSelect"));
        int product_id = Integer.parseInt(request.getParameter("product_id"));

        if (img_id > 0 && product_id > 0) {
            ProductImageDao pImgDao = new ProductImageDao("gamestore");
            ProductImage image = pImgDao.getImageById(img_id);

            //getting directory to delete image from
            Class cls = this.getClass();
            ProtectionDomain pDomain = cls.getProtectionDomain();
            CodeSource cSource = pDomain.getCodeSource();
            URL loc = cSource.getLocation();
            String currentLoc = loc.toString();
            String[] urlParts = currentLoc.split("/");

            String newUrl = "";
            for (int i = 1; i < urlParts.length - 2; i++) {
                newUrl = newUrl + urlParts[i] + "/";
            }

            String dir = newUrl + image.getImage_url();

            //if there is spaces in the path it will replace them w/ %20
            if (dir.contains("%20")) {
                String[] parts = dir.split("%20");
                String fixed_dir = "";
                for (int j = 0; j < parts.length; j++) {
                    if (j < parts.length - 1) {
                        fixed_dir = fixed_dir + parts[j] + " ";
                    } else {
                        fixed_dir = fixed_dir + parts[j];
                    }

                }
                dir = fixed_dir;
            }

            Path path = Paths.get(dir);
            try {
                Files.delete(path);
            } catch (NoSuchFileException x) {
                msg = "File not found";
                error = true;
            } catch (DirectoryNotEmptyException x) {
                msg = "Not empty";
                error = true;
            } catch (IOException x) {
                // File permission problems are caught here.
                msg = "Permission error";
                error = true;
            }
            File file = new File(dir);
            //checking id file has been deleted
            boolean exists = file.exists();

            if (exists == false) {

                boolean deleted = pImgDao.deleteProductImage(img_id);
                if (deleted) {
                    msg = "Image successfully deleted";
                } else {
                    msg = "Failed to delete Image from database";
                    error = true;
                }
            } else {
                msg = "Failed to delete Image from path";
                error = true;
            }

        } else {
            msg = "You must select an image";
            error = true;
        }

        if (!error) {
            session.setAttribute("notify_msg", msg);
            forwardToJsp = "view_product.jsp?product_id=" + product_id;
        } else {
            session.setAttribute("error_msg", msg);
            forwardToJsp = "view_product.jsp?product_id=" + product_id;
        }

        return forwardToJsp;
    }

}
