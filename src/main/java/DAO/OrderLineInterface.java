/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Order;
import DTO.OrderLine;
import DTO.User;
import java.util.ArrayList;

/**
 *
 * @author Conno
 */
public interface OrderLineInterface {

    public boolean createOrderLine(User activeUser, OrderLine ol, Order order);
    public boolean removeOrderLine(User activeUser, OrderLine ol, Order order);
    public ArrayList<OrderLine> getOrderLines(User activeUser, Order order);
//    public boolean updateOrderLineQuantity(int order_line_id, int new_quantity);

}
