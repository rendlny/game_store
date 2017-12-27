/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import DAO.ProductDao;
import DTO.Product;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ren
 */
public class UpdateProductCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String forwardToJsp = null;
        boolean error = false;
        String msg = null;

        String name = request.getParameter("product_name");
        String desc = request.getParameter("product_desc");
        int product_id = 0;
        try {
            Double price = Double.parseDouble(request.getParameter("product_price"));
            Double vat = Double.parseDouble(request.getParameter("product_vat"));
            int stock = Integer.parseInt(request.getParameter("product_stock"));
            product_id = Integer.parseInt(request.getParameter("product_id"));

            if ((name != null && !name.equals(""))
                    && (desc != null && !desc.equals(""))
                    && (price >= 0) && (vat >= 0) && (stock >= 0)
                    && (product_id > 0)) {

                ProductDao productDao = new ProductDao("gamestore");
                Product p = new Product();
                p.setProduct_name(name);
                p.setProduct_desc(desc);
                p.setProduct_price(price);
                p.setVat_percentage(vat);
                p.setStock(stock);
                p.setProduct_id(product_id);
                Boolean product_updated = productDao.updateProduct(p);

                if (product_updated) {
                    msg = "Successfully updated product details";
                } else {
                    msg = "Failed to update product details";
                    error = true;
                }

            } else {
                msg = "Missing values, Cannot register with incomplete data";
                error = true;
            }
        } catch (NumberFormatException e) {
            msg = "Product, Vat and Stock must be numbers";
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
