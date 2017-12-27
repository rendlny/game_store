/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.User;
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
public class UserDao extends Dao implements UserDaoInterface {

    public UserDao(String databaseName) {
        super(databaseName);
    }

    /**
     * This method Brings back all users stored on the table user on the database
     * @return arraylist of users
     */
    @Override
    public ArrayList<User> getAllUsers() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<User> users = new ArrayList();

        try {
            con = getConnection();

            String query = "Select * from users";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                User u = new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("pass"),
                        rs.getString("salt"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("last_password_change"),
                        rs.getInt("is_admin"));
                users.add(u);
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
                System.out.println("Exception occured in the finally section of the getAllMembers() method: " + e.getMessage());
            }
        }

        return users;
    }

    /**
     * This method takes in username and password from the user, if they are correct, and match in the users table,
     * the user is allowed to log in, if not they are not allowed access
     * @param username - user input for username from login form
     * @param password - user input for password from login form
     * @return - returns the user object for the specific user that logged in 
     */
    @Override
    public User checkLogin(String username, String password) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User u = null;

        try {
            con = getConnection();

            String query = "Select * from users WHERE username = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, username);

            rs = ps.executeQuery();

            if (rs.next()) {

                String temp_password = User.generateSaltedHash(password, rs.getString("salt"));

                if (temp_password.equals(rs.getString("pass"))) {

                    u = new User(
                            rs.getInt("user_id"),
                            rs.getString("username"),
                            rs.getString("email"),
                            rs.getString("pass"),
                            rs.getString("salt"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("last_password_change"),
                            rs.getInt("is_admin"));
                }
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
                System.out.println("Exception occured in the finally section of the getAllMembers() method: " + e.getMessage());
            }
        }
        return u;
    }

    /**
     *  This method takes user input information through a form to register a user on the site
     * @param u - is a user object that is constructed of the users information they enetered on form on the sign_up.jsp page
     * @return - returns rows effected, 1 is the add worked successfully, 0 if it failed
     */
    @Override
    public int addUser(User u) {
        Connection con = null;
        PreparedStatement ps = null;
        int rs = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement("INSERT INTO users(username, email, pass, salt, first_name, last_name, last_password_change, is_admin) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

            ps.setString(1, u.getUsername());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getPassword());
            ps.setString(4, u.getSalt());
            ps.setString(5, u.getFirstName());
            ps.setString(6, u.getLast_name());
            ps.setString(7, u.getPassword_change_date());
            ps.setInt(8, u.getIs_admin());

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
                System.out.println("Exception occured in the finally section of the getAllProducts() method: " + e.getMessage());
            }
        }
        return rs;
    }

    /**
     * This method uses the username of the user to search the user table and find the rest of that users information
     * on the table
     * @param username - is the username of the user you are searching for
     * @return the user object of the specific users information
     */
    @Override
    public User getUserByUsername(String username) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User u = null;

        try {
            con = getConnection();

            String query = "Select * from users WHERE username = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, username);

            rs = ps.executeQuery();

            if (rs.next()) {
                u = new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("pass"),
                        rs.getString("salt"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("last_password_change"),
                        rs.getInt("is_admin"));
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
                System.out.println("Exception occured in the finally section of the getAllMembers() method: " + e.getMessage());
            }
        }
        return u;
    }

    /**
     * This method takes username of a specific user as a parameter and uses it to delete that user
     * 
     * @param username - is the username of the user you want to delete
     * @return - returns the rows effected, 1 if the user was deleted successfully, 0 if the user was not deleted successfully 
     */
    @Override
    public int deleteUser(String username) {
        User u = getUserByUsername(username);
        int rs = 0;

        if (u != null) {
            Connection con = null;
            PreparedStatement ps = null;

            try {
                con = getConnection();
                ps = con.prepareStatement("DELETE FROM users WHERE username = ? ");
                ps.setString(1, username);

                rs = ps.executeUpdate();

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
                    System.out.println("Exception occured in the finally section of the getAllProducts() method: " + e.getMessage());
                }
            }
        }
        return rs;
    }

    /**
     * This method takes the username of a user as a parameter to check if the user exists in the database
     * @param username - is the username of the user your looking for 
     * @return returns true if the the user exists, false if they dont
     */
    @Override
    public boolean checkUsername(String username) {
        boolean check = false;

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            String query = "SELECT * FROM users "
                    + "WHERE username = ?";

            ps = con.prepareStatement(query);
            ps.setString(1, username);

            rs = ps.executeQuery();

            if (rs.next()) {
                check = true;
            }

        } catch (SQLException ex) {
            System.out.println("An SQL Exception Occured in the checkUsername() "
                    + "method: " + ex.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException ex) {
                System.out.println("An Exception Occured in the finally section "
                        + "checkUsername() method: " + ex.getMessage());
            }
        }

        return check;
    }

    /**
     *This method takes an email as a parameter to check if a user with that email exists
     * @param email - the email of the user you want to check
     * @return returns true if the the user exists, false if they dont
     */
    @Override
    public boolean checkEmail(String email) {
        boolean check = false;

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            String query = "SELECT * FROM users "
                    + "WHERE email = ?";

            ps = con.prepareStatement(query);
            ps.setString(1, email);

            rs = ps.executeQuery();

            if (rs.next()) {
                check = true;
            }

        } catch (SQLException ex) {
            System.out.println("An SQL Exception Occured in the checkEmail() "
                    + "method: " + ex.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException ex) {
                System.out.println("An Exception Occured in the finally section "
                        + "checkEmail() method: " + ex.getMessage());
            }
        }

        return check;
    }

    /**
     * This method takes salt key as parameter and uses it to see if a user with that salt exists 
     * @param salt - the salt key of the user 
     * @return - returns true if the the user exists, false if they dont
     */
    @Override
    public boolean checkSalt(String salt) {
        boolean check = false;

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            String query = "SELECT * FROM users "
                    + "WHERE salt = ?";

            ps = con.prepareStatement(query);
            ps.setString(1, salt);

            rs = ps.executeQuery();

            if (rs.next()) {
                check = true;
            }

        } catch (SQLException ex) {
            System.out.println("An SQL Exception Occured in the checkSalt() "
                    + "method: " + ex.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException ex) {
                System.out.println("An Exception Occured in the finally section "
                        + "checkSalt() method: " + ex.getMessage());
            }
        }

        return check;
    }

    /**
     * This method alows a user to update their password
     * @param username - the username of the user to want to update 
     * @param oldPass - the users current password to verify the user
     * @param newPass - the updated password
     * @param salt - the new salt generated for new password
     * @param date - the date the password was updated 
     * @return returns true if password was updated successfully, false if it was failed
     */
    @Override
    public boolean updatePassword(String username, String oldPass, String newPass, String salt, String date) {
        boolean updated = false;

        Connection con = null;
        PreparedStatement ps = null;
        int affectedRows = 0;
        try {
            con = getConnection();

            String query = "UPDATE users SET pass = ?, salt = ?, last_password_change = ?  WHERE username = ? and pass = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, newPass);
            ps.setString(2, salt);
            ps.setString(3, date);
            ps.setString(4, username);
            ps.setString(5, oldPass);
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

    /**
     * This method updates the users email, uses username to find the correct user
     * @param username - username of the user you want to update
     * @param newEmail - the updated email of the user
     * @return true if the user was updated successfully, 0 if the update failed
     */
    @Override
    public boolean updateEmail(String username, String newEmail) {
        boolean updated = false;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();

            String query = "UPDATE users SET email = ? WHERE username = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, newEmail);
            ps.setString(2, username);
            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                updated = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the updateEmail() method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the updateEmail() method: " + e.getMessage());
            }
        }

        return updated;
    }

    /**
     * This method takes input from the user to update their name name on their account
     * @param first_name - the new value the user enters to update their first name
     * @param last_name- the new value the user enters to update their second name
     * @param user_id - the id that finds the specifc user to update
     * @return true if the user was updated successfully, 0 if the update failed
     */
    @Override
    public boolean updateName(String first_name, String last_name, int user_id) {
        boolean updated = false;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();

            String query = "UPDATE users SET first_name = ?,last_name = ? WHERE user_id = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, first_name);
            ps.setString(2, last_name);
            ps.setInt(3, user_id);
            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                updated = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the updateName() method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the updateName() method: " + e.getMessage());
            }
        }

        return updated;
    }
}
