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
public class OrderLineDaoTest {

    private final String TEST_DATABASE = "test_store";
    UserDao userDao = new UserDao(TEST_DATABASE);
    User u;
    Order order;
    OrderDao orderDao = new OrderDao(TEST_DATABASE);
    
    public OrderLineDaoTest() {
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
     * Test of createOrderLine method, of class OrderLineDao.
     */
    @Test
    public void testCreateOrderLine() {
        System.out.println("createOrderLine");
        OrderLine ol = new OrderLine();
        
        ol.setOrder_id(1);
        ol.setProduct_id(1);
        ol.setQuantity(0);
        ol.setSale_price(0);
        
        OrderLineDao instance = new OrderLineDao(TEST_DATABASE);
        u=userDao.getUserByUsername("ConnorPakenham");
        order = new Order();
        order.setOrder_id(1);
        boolean expResult = true;
        boolean result = instance.createOrderLine(u, ol, order);
        
        
        assertEquals(expResult, result);
    }

    /**
     * Test of removeOrderLine method, of class OrderLineDao.
     */
    @Test
    public void testRemoveOrderLine() {
        System.out.println("removeOrderLine");
        
        
        OrderLineDao instance = new OrderLineDao(TEST_DATABASE);
        u=userDao.getUserByUsername("ConnorPakenham");
        order = new Order();
        order.setOrder_id(1);
        ArrayList<OrderLine> lines = instance.getOrderLines(u, order);
        
        int line_id = lines.get(0).getLine_id();
        OrderLine ol = new OrderLine();
        ol.setLine_id(line_id);
        boolean expResult = true;
        boolean result = instance.removeOrderLine(u,ol,order);
        assertEquals(expResult, result);
    }

    /**
     * Test of getOrderLines method, of class OrderLineDao.
     */
    @Test
    public void testGetOrderLines() {
        System.out.println("getOrderLines");
        int order_id = 2;
        order = new Order();
        order.setOrder_id(order_id);
        OrderLineDao instance = new OrderLineDao(TEST_DATABASE);
        int  expResult = 1;
        u=userDao.getUserByUsername("ConnorPakenham");
        ArrayList<OrderLine> result = instance.getOrderLines(u,order);
        assertEquals(expResult, result.size());
    }
    
}
