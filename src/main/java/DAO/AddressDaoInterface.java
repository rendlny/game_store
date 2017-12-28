/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Address;
import java.util.ArrayList;

/**
 *
 * @author Aaron
 */
public interface AddressDaoInterface {
    
     public int addAddress(Address address);
     public int deleteAddressByAddressId(int addressId);
     public ArrayList<Address> getAddressByUserId(int userId );
     public ArrayList<Address> getAllAddresses();
     public boolean updateAddress(int userId,String add1, String add2, String city, String county, String country);
    
}
