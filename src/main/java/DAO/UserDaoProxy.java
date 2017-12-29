/*
 * This project was created by Connor Pakenham 28-Dec-2017
 * All Rights Reserved * 
 */
package DAO;

import DTO.User;
import java.util.ArrayList;

/**
 *
 * @author Conno
 */
public class UserDaoProxy implements UserDaoInterface {

     private UserDao userDao;


    public UserDaoProxy() {
        userDao = new UserDao("gamestore");
    }
    
    @Override
    public ArrayList<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User checkLogin(String username, String password) {
        return userDao.checkLogin(username, password);
    }

    @Override
    public int addUser(User u) {
        return userDao.addUser(u);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    @Override
    public int deleteUser(String username) {
        return userDao.deleteUser(username);
    }

    @Override
    public boolean checkUsername(String username) {
        return userDao.checkUsername(username);
    }

    @Override
    public boolean checkEmail(String email) {
        return userDao.checkEmail(email);
    }

    @Override
    public boolean checkSalt(String salt) {
        return userDao.checkSalt(salt);
    }

    @Override
    public boolean updatePassword(String username, String oldPass, String newPass, String salt, String date) {
        return userDao.updatePassword(username, oldPass, newPass, salt, date);
    }

    @Override
    public boolean updateEmail(String username, String newEmail) {
        return userDao.updateEmail(username, newEmail);
    }

    @Override
    public boolean updateName(String first_name, String last_name, int user_id) {
        return userDao.updateName(first_name, last_name, user_id);
    }
    
}
