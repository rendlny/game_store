/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import DAO.UserDao;
import DTO.User;
import DTO.Address;
import DAO.AddressDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Conno
 */
public class CreateUserCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        String forwardToJsp = null, message = null;
        boolean error = false;

        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        String conf_pass = request.getParameter("conf_pass");
        String addressLine1 = request.getParameter("add1");
        String addressLine2 = request.getParameter("add2");
        String city = request.getParameter("city");
        String county = request.getParameter("county");
        String country = request.getParameter("country");

        if ((first_name != null && !first_name.equals(""))
                && (last_name != null && !last_name.equals(""))
                && (username != null && !username.equals(""))
                && (email != null && !email.equals(""))
                && (pass != null && !pass.equals(""))
                && (conf_pass != null && !conf_pass.equals(""))
                && (addressLine1 != null && !addressLine1.equals(""))
                && (addressLine2 != null && !addressLine2.equals(""))
                && (city != null && !city.equals(""))
                && (county != null && !county.equals(""))
                && (country != null && !country.equals(""))) {

            if (!User.checkEmail(email)) {
                message = "Email does not match requested pattern";
                error = true;
            } else if (!User.checkPassword(pass)) {
                message = "Password does not match requested pattern";
                error = true;
            } else {
                if (pass.equals(conf_pass)) {

                    UserDao userDao = new UserDao("gamestore");
                    AddressDao addressDao = new AddressDao("gamestore");
                    int addAddress = 0;

                    if (!userDao.checkUsername(username)) {
                        if (!userDao.checkEmail(email)) {

                            User newUser = new User();
                            newUser.setFirstName(first_name);
                            newUser.setLast_name(last_name);
                            newUser.setUsername(username);
                            newUser.setEmail(email);

                            do {

                                String salt = User.generateSalt();

                                if (!userDao.checkSalt(salt)) {
                                    String hashedPassword = User.generateSaltedHash(pass, salt);
                                    newUser.setPassword(hashedPassword);
                                    newUser.setSalt(salt);
                                    break;
                                }

                            } while (true);

                            newUser.setPassword_change_date(User.getCurrentDate());

                            if (userDao.addUser(newUser) == 1) {

                                User u = userDao.getUserByUsername(username);
                                int userId = u.getUserId();
                                Address a = new Address(userId, addressLine1, addressLine2, city, county, country);
                                addAddress = addressDao.addAddress(a);
                                if (addAddress > 0) {
                                    message = "User " + username + " added!";
                                } else {
                                    message = "An error occured when registering, please try again later";
                                    error = true;
                                }

                            } else {
                                message = "An error occured when registering, please try again later";
                                error = true;
                            }

                        } else {
                            message = "Email already in use. Please try a different one";
                            error = true;
                        }
                    } else {
                        message = "Username already in use. Please try a different one";
                        error = true;
                    }
                } else {
                    message = "Passwords do not match, please try again";
                    error = true;
                }
            }
        } else {
            message = "Missing values, Cannot register with incomplete data";
            error = true;
        }

        if (!error) {
            session.setAttribute("notify_msg", message);
            forwardToJsp = "login.jsp";
        } else {
            session.setAttribute("error_msg", message);
            forwardToJsp = "sign_up.jsp";
        }

        return forwardToJsp;
    }
}
