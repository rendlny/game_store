/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import DAO.ProductDao;
import DTO.Product;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ren
 */
public class ProductSearchCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String forwardToJsp = null;
        boolean error = false;
        String message = null;
        String searchTerm = request.getParameter("search_critera");
        
        ArrayList<Product> results = null;
        if (searchTerm != null) {
            ProductDao productDao = new ProductDao("gamestore");
            results = productDao.searchProducts(searchTerm);

            if (results.size() > 0) {
                session.setAttribute("search_results", results);
            } else {
                results = productDao.searchProductsLike(searchTerm);
                if (results.size() > 0) {
                    session.setAttribute("search_results", productDao.searchProductsLike(searchTerm));

                } else {
                    error = true;
                    message = "No products found";
                }
            }

        } else {
            error = true;
            message = "Please enter a search term";
        }

        if (!error) {
            if (results.size()==1) {
                Product p = results.get(0);
                forwardToJsp = "view_product.jsp?product_id=" + p.getProduct_id();
            } else {
                forwardToJsp = "search_results.jsp";
            }
        } else {
            session.setAttribute("error_msg", message);
            forwardToJsp = "index.jsp";
        }

        return forwardToJsp;
    }

}
