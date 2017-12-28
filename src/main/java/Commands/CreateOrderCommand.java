/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import DAO.AddressDao;
import DAO.OrderDao;
import DAO.OrderLineDao;
import DAO.ProductDao;
import DTO.Address;
import DTO.Order;
import DTO.OrderLine;
import DTO.Product;
import DTO.User;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ren
 */
public class CreateOrderCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String forwardToJsp = null;
        
        if(session.getAttribute("logged_in") != null) {
            
            User logged_in = (User)session.getAttribute("logged_in");
            ArrayList<OrderLine> cart;
            if(((cart = (ArrayList<OrderLine>) session.getAttribute("cart")) != null) && (cart.size() > 0)) {
                
                ProductDao productDao = new ProductDao("gamestore");
                
                boolean quantity_error = false;
                String prod_name = null;
                int index = 0;
                while((!quantity_error) && cart.size() > index) {
                    OrderLine cart_line = cart.get(index);
                    Product p = productDao.getProductById(cart_line.getProduct_id());
                    
                    int ordered_quantity = 0;
                    for(OrderLine temp_line : cart) {
                        if(p.getProduct_id() == temp_line.getProduct_id()) {
                            ordered_quantity += temp_line.getQuantity();
                        }
                    }
                    
                    if(ordered_quantity > p.getStock() ) {
                        prod_name = p.getProduct_name();
                        quantity_error = true;
                    }
                    
                    index++;
                }
                
                if(quantity_error) {
                    session.setAttribute("error_msg", "Requested quantity of " 
                            + prod_name  + " exceeds stock. Please check again later");
                    forwardToJsp = "cart.jsp";
                } else {
                    
                    try {
                        
                        int total_quantity = Integer.parseInt(request.getParameter("total_quantity"));
                        double total_amount_due = Double.parseDouble(request.getParameter("total_amount_due"));
                        
                        AddressDao addressDao = new AddressDao("gamestore");
                        ArrayList<Address> userAddresses = addressDao.getAddressByUserId(logged_in.getUserId());
                        
                        if(userAddresses.size() > 0) {
                        
                            OrderDao orderDao = new OrderDao("gamestore");

                            Order newOrder = new Order();
                            newOrder.setUser_id(logged_in.getUserId());
                            newOrder.setTotal_amount_due(total_amount_due);
                            newOrder.setTotal_quanity(total_quantity);

                            newOrder.setAddress_id(userAddresses.get(0).getAddressId());
                            newOrder.setOrder_date(User.getCurrentDate());

                            if(orderDao.createOrder(newOrder)) {
                                ArrayList<Order> orders = orderDao.getUserOrders(logged_in.getUserId());
                                int last_order = orders.get((orders.size() - 1)).getOrder_id();
                                OrderLineDao orderLineDao = new OrderLineDao("gamestore");
                                
                                for(OrderLine cart_line : cart) {
                                    
                                    cart_line.setOrder_id(last_order);
                                    Product p = productDao.getProductById(cart_line.getProduct_id());
                                    int newQty = p.getStock() - cart_line.getQuantity();
                                    
                                    productDao.updateProductQuantity(p.getProduct_id(), newQty);
                                    
                                    orderLineDao.createOrderLine(cart_line); 
                                }
                                
                                session.setAttribute("cart", null);
                                session.setAttribute("notify_msg", "Your order has been placed");
                                forwardToJsp = "orders.jsp";
                                
                            }
                        
                        } else {
                            session.setAttribute("error_msg", "Your address is missng, please enter one");
                            forwardToJsp = "settings.jsp";
                        }
                        
                    } catch (NumberFormatException ex) {
                        session.setAttribute("error_msg", "Missing values, please try again");
                        forwardToJsp = "cart.jsp";
                    }
                }
            }
        }
        
        return forwardToJsp;
    }
}