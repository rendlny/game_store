/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Order;
import java.util.ArrayList;

/**
 *
 * @author Conno
 */
public interface OrderDaoInterface {

    /**
     *
     * @param newOrder
     * @return
     */
    public boolean createOrder(Order newOrder);

    /**
     *
     * @param order_id
     * @return
     */
    public boolean cancelOrder(int order_id);

    /**
     *
     * @param user_id
     * @return
     */
    public ArrayList<Order> getUserOrders(int user_id);  
}
