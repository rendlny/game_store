/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Address;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Aaron
 */
public class AddressDao extends Dao implements AddressDaoInterface {

    public AddressDao(String database) {
        super(database);
    }

    /**
     * This method allows a user to add address post register
     *
     * @param address object of user input passed in from form
     * @return the rows effected, 1 if it executed successfully, 0 if failure
     */
    @Override
    public int addAddress(Address address) {
        Connection con = null;
        PreparedStatement ps = null;
        int rs = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement("INSERT INTO addresses(user_id, address_line_1, address_line_2, city, county_state, country)"
                    + " VALUES (?, ?, ?, ?, ?, ?)");

            ps.setInt(1, address.getUserId());
            ps.setString(2, address.getAddressLine1());
            ps.setString(3, address.getAddressLine2());
            ps.setString(4, address.getCity());
            ps.setString(5, address.getCounty_state());
            ps.setString(6, address.getCountry());

            rs = ps.executeUpdate();

        } catch (MySQLIntegrityConstraintViolationException e) {
            System.out.println("Constraint Exception occurred: " + e.getMessage());
            rs = -1;
        } catch (SQLException se) {
            System.out.println("SQL Exception occurred");
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the addAddress() method: " + e.getMessage());
            }
        }
        return rs;
    }

    /**
     * This method allows a user to delete an address by using an addressId
     *
     * @param addressId - The address id that corresponds to the users specific
     * address
     * @return the rows effected, 1 if it executed successfully, 0 if failure
     */
    @Override
    public int deleteAddressByAddressId(int addressId) {
        Connection con = null;
        PreparedStatement ps = null;
        int rs = 0;
        try {
            con = getConnection();

            String query = ("DELETE FROM addresses where address_id = ?");
            ps = con.prepareStatement(query);
            ps.setInt(1, addressId);

            rs = ps.executeUpdate();

        } catch (com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException e) {
            System.out.println("Constraint Exception occurred: " + e.getMessage());
            // Set the rowsAffected to -1, this can be used as a flag for the display section
            rs = -1;
        } catch (SQLException se) {
            System.out.println("SQL Exception occurred");
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the deleteAddressByAddressId() method: " + e.getMessage());
            }
        }

        return rs;
    }

    /**
     * This method finds the address/addresses of a specific user by using
     * userId
     *
     * @param userId - id of a user passed through through to find the address
     * @return returns an arrayList of address objects the matches the userId
     */
    @Override
    public ArrayList<Address> getAddressByUserId(int userId) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Address a = null;
        ArrayList<Address> addresses = null;

        try {
            con = getConnection();

            String query = "Select * from addresses WHERE user_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, userId);

            rs = ps.executeQuery();
            addresses = new ArrayList<>();
            if (rs.next()) {
                a = new Address(
                        rs.getInt("address_id"),
                        rs.getInt("user_id"),
                        rs.getString("address_line_1"),
                        rs.getString("address_line_2"),
                        rs.getString("city"),
                        rs.getString("county_state"),
                        rs.getString("country")
                );
                addresses.add(a);
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the getAllMembers() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the getAddressByUserId() method: " + e.getMessage());
            }
        }
        return addresses;
    }

    /**
     * This methods is used to get a list of all the addresses for all users
     *
     * @return an arraylist of addresses for all users
     */
    @Override
    public ArrayList<Address> getAllAddresses() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Address> addresses = new ArrayList();

        try {
            con = getConnection();

            String query = "Select * from addresses";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Address a = new Address(
                        rs.getInt("address_id"),
                        rs.getInt("user_id"),
                        rs.getString("address_line_1"),
                        rs.getString("address_line_2"),
                        rs.getString("city"),
                        rs.getString("county_state"),
                        rs.getString("country"));
                addresses.add(a);

            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getAllBooks() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the getAllAddresses() method: " + e.getMessage());
            }
        }

        return addresses;

    }

    /** This method is used to update the address of a user, it takes in parameters of user input from a form
     *
     * @param userId - Is the the id that links the address the the specific user
     * @param add1 - Is the value entered by the user to update the address Line 1
     * @param add2 -  Is the value entered by the user to update address line 2
     * @param city -  Is the value entered by the user to update their city
     * @param county - Is the value entered by the user to update their county
     * @param country - Is the value entered by the user to update their country
     * @return boolean value, true if the update worked successfully, false if it failed
     */
    @Override
    public boolean updateAddress(int userId,String add1, String add2, String city, String county, String country){
   boolean updated = false;

        Connection con = null;
        PreparedStatement ps = null;
        int affectedRows = 0;
        try {
            con = getConnection();

            String query = "UPDATE addresses SET address_line_1 = ?, address_line_2 = ?, city = ?, county_state = ?, country = ?  WHERE user_id = ? ";
            ps = con.prepareStatement(query);
            ps.setString(1, add1);
            ps.setString(2, add2);
            ps.setString(3, city);
            ps.setString(4, county);
            ps.setString(5, country);
            ps.setInt(6, userId);
            affectedRows = ps.executeUpdate();

            if (affectedRows != 0) {
                updated = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the updatePassword() method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the updatePassword() method: " + e.getMessage());
            }
        }
        return updated;
    }

}
