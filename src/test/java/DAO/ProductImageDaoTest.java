/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ProductImage;
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
public class ProductImageDaoTest {

    private final String TEST_DATABASE = "test_store";
    
    public ProductImageDaoTest() {
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
     * Test of addImageToProduct method, of class ProductImageDao.
     */
    @Test
    public void testAddImageToProduct() {
        System.out.println("addImageToProduct");
        ProductImage img = new ProductImage();
        img.setProduct_id(2);
        img.setImage_url("ahhhhhhh");
        ProductImageDao instance = new ProductImageDao(TEST_DATABASE);
        
        boolean expResult = true;
        
        boolean result = instance.addImageToProduct(img);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteProductImage method, of class ProductImageDao.
     */
    @Test
    public void testDeleteProductImage() {
        System.out.println("deleteProductImage");
        
        ProductImageDao instance = new ProductImageDao(TEST_DATABASE);
        
        boolean expResult = true;
        
        ArrayList<ProductImage> images =  instance.getProductImageById(2);
        System.out.println(images.size());
        boolean result = instance.deleteProductImage(images.get(0).getImage_id());
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getProductImageById method, of class ProductImageDao.
     */
    @Test
    public void testGetProductImageById() {
        System.out.println("getProductImageById");
        int id = 1;
        ProductImageDao instance = new ProductImageDao(TEST_DATABASE);
        int expResult = 1;
        ArrayList<ProductImage> result = instance.getProductImageById(id);
        assertEquals(expResult, result.size());
    }
    
}
