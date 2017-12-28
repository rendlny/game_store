/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.OrderLine;
import java.util.ArrayList;

/**
 *
 * @author Conno
 */
public interface OrderLineInterface {
    
    public boolean createOrderLine(OrderLine ol);
    public boolean removeOrderLine(int line_id);
    public ArrayList<OrderLine> getOrderLines(int order_id);
//    public boolean updateOrderLineQuantity(int order_line_id, int new_quantity);
    
}
