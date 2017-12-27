/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import DTO.OrderLine;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Conno
 */
public class AddProductToCart implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        String forwardToJsp;

        if (session.getAttribute("logged_in") != null) {
            ArrayList<OrderLine> cart_list;

            if (session.getAttribute("cart") != null) {
                cart_list = (ArrayList<OrderLine>) session.getAttribute("cart");
            } else {
                cart_list = new ArrayList<>();
            }

            try {
                OrderLine ol = new OrderLine();

                int product_id = Integer.parseInt(request.getParameter("product_id"));
                double sale_price = Double.parseDouble(request.getParameter("sale_price"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));

                boolean found = false;
                //checking if the game is already in the cart
                for (OrderLine o : cart_list) {
                    //if it is, the quantity of that game ordered is increased
                    if (o.getProduct_id() == product_id) {
                        o.setQuantity(o.getQuantity() + quantity);
                        found = true;
                    }
                }
                if (found == false) { //if the game is not already in the cart
                    ol.setProduct_id(product_id);
                    ol.setSale_price(sale_price);
                    ol.setQuantity(quantity);

                    cart_list.add(ol);
                }
                session.setAttribute("cart", cart_list);

                session.setAttribute("notify_msg", "Product added to cart");
                forwardToJsp = "cart.jsp";

            } catch (NumberFormatException ex) {
                session.setAttribute("error_msg", "An error occured when trying to add product to cart");
                forwardToJsp = "view_product.jsp";
            }
        } else {
            session.setAttribute("error_msg", "You must be logged in to access cart");
            forwardToJsp = "login.jsp";
        }

        return forwardToJsp;
    }

}
