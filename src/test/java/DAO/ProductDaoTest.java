/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Product;
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
public class ProductDaoTest {
    
    private final String TEST_DATABASE = "test_store";
    
    public ProductDaoTest() {
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
     * Test of addProduct method, of class ProductDao.
     */
    @Test
    public void testAddProduct() {
        System.out.println("addProduct");
        
        Product p = new Product();
        p.setProduct_name("product");
        p.setProduct_desc("test");
        p.setProduct_price(10.20);
        p.setStock(15);
        p.setVat_percentage(0.20);
        
        ProductDao instance = new ProductDao(TEST_DATABASE);
        boolean expResult = true;
        boolean result = instance.addProduct(p);
        assertEquals(expResult, result);
        
        int id = instance.searchProducts("product").get(0).getProduct_id();
        instance.deleteProduct(id);
    }

    /**
     * Test of deleteProduct method, of class ProductDao.
     */
    @Test
    public void testDeleteProduct() {
        System.out.println("deleteProduct");
        
        ProductDao instance = new ProductDao(TEST_DATABASE);
        
        Product p = new Product();
        p.setProduct_name("product");
        p.setProduct_desc("test");
        p.setProduct_price(10.20);
        p.setStock(15);
        p.setVat_percentage(0.20);
        
        instance.addProduct(p);
        
        int product_id = instance.searchProducts("product").get(0).getProduct_id();
        
        boolean expResult = true;
        boolean result = instance.deleteProduct(product_id);
        assertEquals(expResult, result);
    }

    /**
     * Test of listAllProducts method, of class ProductDao.
     */
    @Test
    public void testListAllProducts() {
        System.out.println("listAllProducts");
        ProductDao instance = new ProductDao(TEST_DATABASE);
        int expResult = 3;
        ArrayList<Product> result = instance.listAllProducts();
        assertEquals(expResult, result.size());
    }

    /**
     * Test of searchProducts method, of class ProductDao.
     */
    @Test
    public void testSearchProducts() {
        System.out.println("searchProducts");
        String searchTerm = "test_Product";
        ProductDao instance = new ProductDao(TEST_DATABASE);
        int expResult = 1;
        ArrayList<Product> result = instance.searchProducts(searchTerm);
        assertEquals(expResult, result.size());
    }

    /**
     * Test of searchProductsLike method, of class ProductDao.
     */
    @Test
    public void testSearchProductsLike() {
        System.out.println("searchProductsLike");
        String searchTerm = "test";
        ProductDao instance = new ProductDao(TEST_DATABASE);
        int expResult = 2;
        ArrayList<Product> result = instance.searchProductsLike(searchTerm);
        assertEquals(expResult, result.size());
    }

    /**
     * Test of getProductById method, of class ProductDao.
     */
    @Test
    public void testGetProductById() {
        System.out.println("getProductById");
        int product_id = 2;
        ProductDao instance = new ProductDao(TEST_DATABASE);
        boolean expResult = true;
        boolean result = false;
        
        Product p = instance.getProductById(product_id);
        
        if(p != null) {
            result = true;
        }
        
        assertEquals(expResult, result);
    }

    /**
     * Test of recentProducts method, of class ProductDao.
     */
    @Test
    public void testRecentProducts() {
        System.out.println("recentProducts");
        ProductDao instance = new ProductDao(TEST_DATABASE);
        int expResult = 3;
        ArrayList<Product> result = instance.recentProducts();
        assertEquals(expResult, result.size());
    }

    /**
     * Test of updateProduct method, of class ProductDao.
     */
    @Test
    public void testUpdateProduct() {
        System.out.println("updateProduct");
        Product p = new Product(3, "test_product_3", "test_product_3", 10, 10, 10);
        ProductDao instance = new ProductDao(TEST_DATABASE);
        boolean expResult = true;
        boolean result = instance.updateProduct(p);
        p = new Product(3, "test_product_2", "test_product_2", 10, 10, 10);
        instance.updateProduct(p);
    }

    /**
     * Test of updateProductQuantity method, of class ProductDao.
     */
    @Test
    public void testUpdateProductQuantity() {
        System.out.println("updateProductQuantity");
        int product_id = 2;
        int newQty = 13;
        ProductDao instance = new ProductDao(TEST_DATABASE);
        boolean expResult = true;
        boolean result = instance.updateProductQuantity(product_id, newQty);
        assertEquals(expResult, result);
        instance.updateProductQuantity(product_id, 15);
    }
    
}
