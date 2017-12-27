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
public class RemoveFromCartCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String forwardToJsp;
        
        if(session.getAttribute("logged_in") != null) {
            ArrayList<OrderLine> cart_list;

            if(session.getAttribute("cart") != null){
                cart_list = (ArrayList<OrderLine>)session.getAttribute("cart");
             
                try { 
                    int index = Integer.parseInt(request.getParameter("index"));

                    OrderLine removed = cart_list.remove(index);
                    
                    if(removed != null) {
                        if(cart_list.size() > 0) {
                            session.setAttribute("cart", cart_list);
                        } else {
                            session.setAttribute("cart", null);
                        }
                        session.setAttribute("notify_msg", "Product removed from cart");
                        forwardToJsp = "cart.jsp";
                    } else {
                        session.setAttribute("error_msg", "No product found at selected index");
                        forwardToJsp = "cart.jsp";
                    }
                    
                } catch (NumberFormatException ex) {
                    session.setAttribute("error_msg", "An error occured when trying to remove product from cart");
                    forwardToJsp = "view_product.jsp";
                }
            } else {
                session.setAttribute("error_msg", "You have nothing in your cart to remove");
                forwardToJsp = "cart.jsp";
            }
        } else {
            session.setAttribute("error_msg", "You must be logged in to access cart");
            forwardToJsp = "login.jsp";
        }
        
        
        return forwardToJsp;
    }
    
}
