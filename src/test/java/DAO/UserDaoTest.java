/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
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
public class UserDaoTest {
    
    private final String TEST_DATABASE = "test_store";
    
    public UserDaoTest() {
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
     * Test of getAllUsers method, of class UserDao.
     */
    @Test
    public void testGetAllUsers() {
        System.out.println("getAllUsers");
        UserDao instance = new UserDao(TEST_DATABASE);
        int expResult = 3;
        ArrayList<User> result = instance.getAllUsers();
        assertEquals(expResult, result.size());
    }

    /**
     * Test of checkLogin method, of class UserDao.
     */
    @Test
    public void testCheckLogin() {
        System.out.println("checkLogin");
        String username = "ConnorPakenham";
        String password = "Password12345";
        UserDao instance = new UserDao(TEST_DATABASE);
        
        boolean expResult = true;
        boolean result = false;
        
        User u = instance.checkLogin(username, password);
        
        if(u != null) {
            result = true;
        }
        
        assertEquals(expResult, result);
    }

    /**
     * Test of addUser method, of class UserDao.
     */
    @Test
    public void testAddUser() {
        System.out.println("addUser");
        
        UserDao instance = new UserDao(TEST_DATABASE);
        
        User u = new User();
        u.setUsername("TestUser");
        u.setEmail("TestUser@gmail.com");
        u.setFirstName("Test");
        u.setLast_name("User");
        
        String salt = null;
        boolean error = false;
        do {
            error = false;
            
            salt = User.generateSalt();
            
            if(instance.checkSalt(salt)) error = true; 
            
        } while(error);
        
        u.setSalt(salt);
        u.setPassword(User.generateSaltedHash("Password12345", salt));
        u.setPassword_change_date(User.getCurrentDate());
        u.setIs_admin(0);
        
        int expResult = 1;
        int result = instance.addUser(u);
        assertEquals(expResult, result);
        
        instance.deleteUser("TestUser");
    }

    /**
     * Test of getUserByUsername method, of class UserDao.
     */
    @Test
    public void testGetUserByUsername() {
        System.out.println("getUserByUsername");
        String username = "ConnorPakenham";
        UserDao instance = new UserDao(TEST_DATABASE);
        
        boolean expResult = true;
        boolean result = false;
        
        User u = instance.getUserByUsername(username);
        
        if(u != null) {
            result = true;
        }
        
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteUser method, of class UserDao.
     */
    @Test
    public void testDeleteUser() {
        System.out.println("deleteUser");
        
        UserDao instance = new UserDao(TEST_DATABASE);
        
        User u = new User();
        u.setUsername("TestUser");
        u.setEmail("TestUser@gmail.com");
        u.setFirstName("Test");
        u.setLast_name("User");
        
        String salt = null;
        boolean error = false;
        do {
            error = false;
            
            salt = User.generateSalt();
            
            if(instance.checkSalt(salt)) error = true; 
            
        } while(error);
        
        u.setSalt(salt);
        u.setPassword(User.generateSaltedHash("Password12345", salt));
        u.setPassword_change_date(User.getCurrentDate());
        u.setIs_admin(0);
        
        instance.addUser(u);
        
        String username = "TestUser";
        int expResult = 1;
        int result = instance.deleteUser(username);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkUsername method, of class UserDao.
     */
    @Test
    public void testCheckUsername() {
        System.out.println("checkUsername");
        String username = "ConnorPakenham";
        UserDao instance = new UserDao(TEST_DATABASE);
        boolean expResult = true;
        boolean result = instance.checkUsername(username);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkEmail method, of class UserDao.
     */
    @Test
    public void testCheckEmail() {
        System.out.println("checkEmail");
        String email = "ConnorPakenham@gmail.com";
        UserDao instance = new UserDao(TEST_DATABASE);
        boolean expResult = true;
        boolean result = instance.checkEmail(email);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkSalt method, of class UserDao.
     */
    @Test
    public void testCheckSalt() {
        System.out.println("checkSalt");
        String salt = "JlzHy7b3slHqqxzIKEqbPveMFU8ld03pcRf02Bgjf00=";
        UserDao instance = new UserDao(TEST_DATABASE);
        boolean expResult = true;
        boolean result = instance.checkSalt(salt);
        assertEquals(expResult, result);
    }
    
}