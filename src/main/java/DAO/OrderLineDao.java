/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Order;
import DTO.OrderLine;
import DTO.User;
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
public class OrderLineDao extends Dao implements OrderLineInterface {

    public OrderLineDao(String database) {
        super(database);
    }

    /**
     * Creates an OrderLine record in the database from OrderLine Object supplied
     *
     * @param ol object generated from input from the cart
     * @return boolean returns if the OrderLine is created successfully
     */
    @Override
    public boolean createOrderLine(User activeUser, OrderLine ol, Order order) {
        boolean added = false;

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = getConnection();
            String query = "INSERT INTO order_lines(order_id, product_id, "
                    + "quantity, sale_price) VALUES(?,?,?,?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, ol.getOrder_id());
            ps.setInt(2, ol.getProduct_id());
            ps.setInt(3, ol.getQuantity());
            ps.setDouble(4, ol.getSale_price());

            int result = ps.executeUpdate();

            if (result == 1) {
                added = true;
            }

        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("An Constraint Exception Occured in the createOrderLine() "
                    + "method: " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("An SQL Exception Occured in the createOrderLine() "
                    + "method: " + ex.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException ex) {
                System.out.println("An Exception Occured in the finally section "
                        + "createOrderLine() method: " + ex.getMessage());
            }
        }

        return added;
    }

    /**
     * Removes the Order Line with supplied id
     *
     * @param line_id integer representing the id of the OrderLine you wish to delete
     * @return boolean returns if the order is removed successfully
     */
    @Override
    public boolean removeOrderLine(User activeUser, OrderLine ol, Order order) {
        boolean removed = false;

        Connection con = null;
        PreparedStatement ps = null;

        int line_id = ol.getLine_id();

        try {
            con = getConnection();
            String query = "DELETE FROM order_lines WHERE line_id = ?";

            ps = con.prepareStatement(query);
            ps.setInt(1, line_id);

            int result = ps.executeUpdate();

            if (result == 1) {
                removed = true;
            }

        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("An Constraint Exception Occured in the removeOrderLine() "
                    + "method: " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("An SQL Exception Occured in the removeOrderLine() "
                    + "method: " + ex.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException ex) {
                System.out.println("An Exception Occured in the finally section "
                        + "removeOrderLine() method: " + ex.getMessage());
            }
        }

        return removed;
    }

    /**
     * Retrieves all the OrderLines from the database relating to the supplied id
     *
     * @param order_id integer representing the OrderLine id
     * @return ArrayList of found OrderLines
     */
    @Override
    public ArrayList<OrderLine> getOrderLines(User activeUser, Order order) {

        ArrayList<OrderLine> orderlines = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int order_id = order.getOrder_id();
        try {
            con = getConnection();
            String query = "SELECT * FROM order_lines WHERE order_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, order_id);

            rs = ps.executeQuery();

            orderlines = new ArrayList<>();
            while (rs.next()) {
                OrderLine ol = new OrderLine(
                        rs.getInt("line_id"),
                        rs.getInt("order_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getDouble("sale_price")
                );

                orderlines.add(ol);
            }

        } catch (SQLException ex) {
            System.out.println("An SQL Exception Occured in the removeOrderLine() "
                    + "method: " + ex.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException ex) {
                System.out.println("An Exception Occured in the finally section "
                        + "removeOrderLine() method: " + ex.getMessage());
            }
        }

        return orderlines;
    }
}
