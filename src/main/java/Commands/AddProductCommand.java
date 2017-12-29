/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import DAO.ProductDao;
import DAO.ProductImageDao;
import DTO.Product;
import DTO.ProductImage;
import DTO.User;
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
public class AddProductCommand implements Command {

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

        HttpSession session = request.getSession();
        InputStream inputStream = null;
        String forwardToJsp = null, message = null;
        boolean error = false;
        int product_id = -1;

        String savePath = save_dir;
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        String name = request.getParameter("product_name");
        String desc = request.getParameter("product_desc");
        Part part = null;
        try {
            part = request.getPart("image");
        } catch (IOException ex) {
            Logger.getLogger(AddProductCommand.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(AddProductCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {

            User logged_in = new User();
            if (session.getAttribute("logged_in") != null) {

                logged_in = (User) session.getAttribute("logged_in");
            }

            Double price = Double.parseDouble(request.getParameter("product_price"));
            Double vat = Double.parseDouble(request.getParameter("product_vat"));
            int stock = Integer.parseInt(request.getParameter("product_stock"));
            int steam_app_id = Integer.parseInt(request.getParameter("steam_app_id"));

            if ((name != null && !name.equals(""))
                    && (desc != null && !desc.equals(""))
                    && (price >= 0) && (vat >= 0) && (stock >= 0)
                    && (part != null)) {

                ProductDao productDao = new ProductDao("gamestore");
                Product p = new Product();
                p.setProduct_name(name);
                p.setProduct_desc(desc);
                p.setProduct_price(price);
                p.setVat_percentage(vat);
                p.setStock(stock);

                Boolean product_added = false;
                if (steam_app_id < 1) {
                    product_added = productDao.addProduct(logged_in, p);
                } else {
                    p.setSteam_app_id(steam_app_id);
                    product_added = productDao.addProductWithSteamId(logged_in, p);
                }
                String filePath = null;
                String fileName = null;

                ProductImage pi = new ProductImage();
                if (product_added) {
                    try {
                        //savePath = C:\image/product_image
                        fileName = pi.extractFileName(part); //imageName.imageType
                        part.write(savePath + File.separator + fileName);

                        filePath = savePath + File.separator + fileName;
                    } catch (IOException e) {
                        System.out.println("ERROR getting filePath");
                    }
                    if (filePath != null) {

                        product_id = productDao.searchProducts(name).get(0).getProduct_id();

                        ProductImageDao productImageDao = new ProductImageDao("gamestore");
                        pi.setProduct_id(product_id);
                        pi.setImage_url("image/product_image/" + fileName);

                        boolean image_added = productImageDao.addImageToProduct(pi);

                        if (image_added) {
                            message = "Product added successfully";
                        } else {
                            message = "An error occured when adding the product image";
                            error = true;
                        }
                    } else {
                        message = "Failed to get filePath" + savePath;
                        error = true;
                    }
                } else {
                    message = "Failed to add product";
                    error = true;
                }

            } else {
                message = "Missing values, Cannot register with incomplete data";
                error = true;
            }
        } catch (NumberFormatException e) {
            message = "Product, Vat and Stock must be numbers";
            error = true;
        }

        if (!error) {
            session.setAttribute("notify_msg", message);
            forwardToJsp = "view_product.jsp?product_id=" + product_id;
        } else {
            session.setAttribute("error_msg", message);
            forwardToJsp = "add_product.jsp";
        }

        return forwardToJsp;
    }
}
