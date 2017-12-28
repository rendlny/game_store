/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.User;
import java.util.ArrayList;

/**
 *
 * @author d00167267
 */
public interface UserDaoInterface {

    public ArrayList<User> getAllUsers();
    public User checkLogin(String username, String password);
    public int addUser(User u);
    public User getUserByUsername(String username);
    public int deleteUser(String username);
    public boolean checkUsername(String username);
    public boolean checkEmail(String email);
    public boolean checkSalt(String salt);
    public boolean updatePassword(String username, String oldPass, String newPass, String salt, String date);
    public boolean updateEmail(String username, String newEmail);
    public boolean updateName(String first_name, String last_name, int user_id);
}