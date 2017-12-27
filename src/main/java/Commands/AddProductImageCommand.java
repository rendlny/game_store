/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import DAO.ProductImageDao;
import DTO.Product;
import DTO.ProductImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Ren
 */
public class AddProductImageCommand implements Command {

    private static final String SAVE_DIR = "Users/Ren/Desktop/Computing Yr3/Web Patterns/CA3/StoreApp/web/image/product_image";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        //getting save directory
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

        String save_dir = newUrl + "image/product_image";

        //if there is spaces in the path it will replace them w/ %20
        if (save_dir.contains("%20")) {
            String[] parts = save_dir.split("%20");
            String fixed_dir = "";
            for (int j = 0; j < parts.length; j++) {
                if (j < parts.length - 1) {
                    fixed_dir = fixed_dir + parts[j] + " ";
                } else {
                    fixed_dir = fixed_dir + parts[j];
                }

            }
            save_dir = fixed_dir;
        }

        String forwardToJsp = null;
        HttpSession session = request.getSession();
        boolean error = false;
        String msg = null;
        Part part = null;

        String savePath = save_dir;
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        try {
            part = request.getPart("image");
        } catch (IOException ex) {
            Logger.getLogger(AddProductImageCommand.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(AddProductImageCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        int product_id = Integer.parseInt(request.getParameter("product_id"));

        String filePath = null;
        String fileName = null;
        ProductImage img = new ProductImage();

        try {
            //savePath = C:\image/product_image
            fileName = img.extractFileName(part); //imageName.imageType
            part.write(savePath + File.separator + fileName);

            filePath = savePath + File.separator + fileName;
        } catch (IOException e) {
            System.out.println("ERROR getting filePath");
        }

        if (filePath != null && product_id > 0) {
            img.setProduct_id(product_id);
            img.setImage_url("image/product_image/" + fileName);
            ProductImageDao productImageDao = new ProductImageDao("gamestore");
            boolean imgNameCheck = productImageDao.checkImageName(img.getImage_url());

            if (!imgNameCheck) {
                boolean added = productImageDao.addImageToProduct(img);
                if (added == true) {
                    msg = "Image successfully uploaded to DB";
                } else {
                    msg = "Failed to upload image to DB";
                    error = true;
                }
            } else {
                msg = "Please rename your image file, that name is already in use";
                error = true;
            }
        } else {
            msg = "You must upload an image";
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
