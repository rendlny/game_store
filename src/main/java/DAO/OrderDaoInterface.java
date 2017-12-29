/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Order;
import DTO.User;
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
    public boolean createOrder(User activeUser, Order newOrder);

    /**
     *
     * @param order_id
     * @return
     */
    public boolean cancelOrder(User activeUser, Order order);

    /**
     *
     * @param user_id
     * @return
     */
    public ArrayList<Order> getUserOrders(User activeUser, int user_id);
}
