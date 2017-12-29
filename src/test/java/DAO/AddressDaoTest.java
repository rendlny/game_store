package DAO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import DTO.Address;
import DTO.User;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Aaron
 */
public class AddressDaoTest {

    private final String TEST_DATABASE = "test_store";

    public AddressDaoTest() {
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
     * Test of addAddress method, of class AddressDao.
     */
    @Test
    public void testAddAddress() {
        System.out.println("addAddress");
        Address address = new Address(3, "testStreet1", "TestStreet2", "testCity", "testCounty", "testCountry");
        int userId = 3;
        int expected = 1;
        AddressDao addressDao = new AddressDao(TEST_DATABASE);
        UserDao userDao = new UserDao(TEST_DATABASE);
        User u = userDao.getUserByUsername("TotallyARealPerson");
        int rs = addressDao.addAddress(u,address);
        assertEquals(expected, rs);

        
        ArrayList<Address> addresses = addressDao.getAddressByUserId(userId);
    }

    /**
     * Test of deleteAddressByAddressId method, of class AddressDao.
     */
    @Test
    public void testDeleteAddressByAddressId() {
        System.out.println("deleteAddressByAddressId");
        
        AddressDao addressDao = new AddressDao(TEST_DATABASE);
        int address_id = addressDao.getAddressByUserId(3).get(0).getAddressId();
        UserDao userDao = new UserDao(TEST_DATABASE);
        User u = userDao.getUserByUsername("TotallyARealPerson");
        
        int expected = 1;
        int rs = addressDao.deleteAddressByAddressId(u, address_id, 3);
    }

    /**
     * Test of getAddressByUserId method, of class AddressDao.
     */
    @Test
    public void testGetAddressByUserId() {
        System.out.println("getAddressByUserId");
        int userId = 1;
        AddressDao addressDao = new AddressDao(TEST_DATABASE);
        ArrayList<Address> add1 = addressDao.getAddressByUserId(userId);
        Address a = add1.get(0);
        Address compareAddress = new Address(1, 1, "Kingscourt", "kingscourt", "Cavan", "Cavan", "Ireland");
        assertEquals(a, compareAddress);

    }

    /**
     * Test of getAllAddresses method, of class AddressDao.
     */
    @Test
    public void testGetAllAddresses() {
        System.out.println("getAllAddresses");
        int userId = 0;
        AddressDao addressDao = new AddressDao(TEST_DATABASE);
        int expected = 2;
        ArrayList<Address> result = addressDao.getAllAddresses();
        assertEquals(expected, result.size());
    }

    /**
     * Test of updateAddress method, of class AddressDao.
     */
    @Test
    public void testUpdateAddress() {
        System.out.println("updateAddress");
        UserDao userDao = new UserDao(TEST_DATABASE);
        User u = userDao.getUserByUsername("TotallyARealPerson");
        AddressDao addressDao = new AddressDao(TEST_DATABASE);
        boolean result = addressDao.updateAddress(u,2,"test","test","test","test","test");
        assertEquals(result, true);
        addressDao.updateAddress(u,2,"knockbridge", "knockbridge", "Louth", "Louth", "Ireland");
        
        
    }

}
