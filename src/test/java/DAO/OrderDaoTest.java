/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Order;
import DTO.User;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Conno
 */
public class OrderDaoTest {
    
    private final String TEST_DATABASE = "test_store";
    UserDao userDao = new UserDao(TEST_DATABASE);
    User u;
    
    public OrderDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createOrder method, of class OrderDao.
     */
    @Test
    public void testCreateOrder() {
        System.out.println("createOrder");
        Order newOrder = new Order();
        newOrder.setUser_id(1);
        newOrder.setAddress_id(1);
        newOrder.setTotal_amount_due(0);
        newOrder.setTotal_quanity(0);
        newOrder.setOrder_date(User.getCurrentDate());
        
        OrderDao instance = new OrderDao(TEST_DATABASE);
        boolean expResult = true;
        
        u=userDao.getUserByUsername("ConnorPakenham");
        
        boolean result = instance.createOrder(u,newOrder);
        assertEquals(expResult, result);
    }

    /**
     * Test of cancelOrder method, of class OrderDao.
     */
    @Test
    public void testCancelOrder() {
        System.out.println("cancelOrder");
        
        OrderDao instance = new OrderDao(TEST_DATABASE);
        u=userDao.getUserByUsername("ConnorPakenham");
        ArrayList<Order> orders = instance.getUserOrders(u,1);
        
        
        int order_id = orders.get(0).getOrder_id();
        Order order = orders.get(order_id);
        System.out.println(order_id);
        boolean expResult = true;
        boolean result = instance.cancelOrder(u,order);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserOrders method, of class OrderDao.
     */
    @Test
    public void testGetUserOrders() {
        System.out.println("getUserOrders");
        int user_id = 1;
        OrderDao instance = new OrderDao(TEST_DATABASE);
        int expResult = 2;
        u=userDao.getUserByUsername("ConnorPakenham");
        ArrayList<Order> result = instance.getUserOrders(u,user_id);
        assertEquals(expResult, result.size());
    }
    
}