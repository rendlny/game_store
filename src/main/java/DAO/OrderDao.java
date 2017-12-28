/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Order;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Conno
 */
public class OrderDao extends Dao implements OrderDaoInterface {
    
    /**
     *
     * @param database
     */
    public OrderDao(String database) {
        super(database);
    }

    /**
     * Creates a new order from the supplied order object
     * 
     * @param newOrder object of order passed from the create order form
     * @return boolean returns if the order is created successfully
     */
    @Override
    public boolean createOrder(Order newOrder) {
        boolean added = false; 
        
        Connection con = null;
        PreparedStatement ps = null;
        int result = 0;
        
        try {
            con = getConnection();
            String query = "INSERT INTO orders( user_id, total_amount_due, "
                    + "total_quantity, address_id, order_date) "
                    + "VALUES (?, ?, ?, ?, ?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, newOrder.getUser_id());
            ps.setDouble(2, newOrder.getTotal_amount_due());
            ps.setInt(3, newOrder.getTotal_quanity());
            ps.setInt(4, newOrder.getAddress_id());
            ps.setString(5, newOrder.getOrder_date());
            
            result = ps.executeUpdate();
            
            if(result == 1) {
                added = true;
            }
            
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("An Constraint Exception Occured in the createOrder() "
                    + "method: " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("An SQL Exception Occured in the createOrder() "
                    + "method: " + ex.getMessage());
        } finally {
            try {
                if(ps != null) {
                    ps.close();
                }
                if(con != null) {
                    freeConnection(con);
                }
            } catch (SQLException ex) {
                System.out.println("An Exception Occured in the finally section "
                        + "createOrder() method: " + ex.getMessage());
            }
        }
        
        return added;
    }

    /**
     * Cancels a previously created order
     * 
     * @param order_id integer representing the id of the order
     * @return boolean to check if order has been successfully cancelled
     */
    @Override
    public boolean cancelOrder(int order_id) {
        
        boolean removed = false;
        
        Connection con = null;
        PreparedStatement ps = null;
        int result = 0;
        
        try {
            con = getConnection();
            String query = "DELETE FROM orders "
                    + "WHERE order_id = ?";
            
            ps = con.prepareStatement(query);
            ps.setInt(1, order_id);
            
            result = ps.executeUpdate();
            
            if(result == 1) {
                removed = true;
            }
        } catch (SQLException ex) {
            System.out.println("An SQL Exception Occured in the cancelOrder() "
                    + "method: " + ex.getMessage());
        } finally {
            try {
                if(ps != null) {
                    ps.close();
                }
                if(con != null) {
                    freeConnection(con);
                }
            } catch (SQLException ex) {
                System.out.println("An Exception Occured in the finally section "
                        + "cancelOrder() method: " + ex.getMessage());
            }
        }
        
        return removed;
    }

    /**
     * Retrieves all the users orders that are currently in the database
     * 
     * @param user_id integer representing the id of the user
     * @return ArrayList of all the orders found
     */
    @Override
    public ArrayList<Order> getUserOrders(int user_id) {
        ArrayList<Order> found = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            con = getConnection();
            String query = "SELECT * FROM orders "
                    + "WHERE user_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, user_id);
            
            rs = ps.executeQuery();
            
            found = new ArrayList<>();
            while(rs.next()) {
                Order o = new Order(
                    rs.getInt("order_id"),
                    rs.getInt("user_id"),
                    rs.getDouble("total_amount_due"),
                    rs.getInt("total_quantity"),
                    rs.getInt("address_id"),
                    rs.getString("order_date")
                );
                found.add(o);  
            }
            
        } catch (SQLException ex) {
            System.out.println("An SQL Exception Occured in the cancelOrder() "
                    + "method: " + ex.getMessage());
        } finally {
            try {
                if(ps != null) {
                    ps.close();
                }
                if(rs != null) {
                    rs.close();
                }
                if(con != null) {
                    freeConnection(con);
                }
            } catch (SQLException ex) {
                System.out.println("An Exception Occured in the finally section "
                        + "cancelOrder() method: " + ex.getMessage());
            }
        }
        
        return found;
    }
    
}