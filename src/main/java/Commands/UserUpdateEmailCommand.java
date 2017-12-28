/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import DAO.UserDao;
import DTO.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ren
 */
public class UserUpdateEmailCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String msg = null;
        boolean error = false;
        String forwardToJsp = null;
        HttpSession session = request.getSession();
        UserDao userDao = new UserDao("gamestore");

        User logged_in = (User) session.getAttribute("logged_in");
        if (logged_in == null) {
            //if user is not logged in
            error = true;
            msg = "You have to be logged in to use this functionality";
        } else {
            String newEmail = request.getParameter("email");
            //if no email was set
            if (newEmail == null || newEmail.equals("")) {
                error = true;
                msg = "Please set new email";
            } else {
                //checking if email is valid
                boolean emailValid = User.checkEmail(newEmail);
                if (emailValid == false) {
                    error = true;
                    msg = "Invalid email";
                } else {
                    //checking email is not already in use by another user
                    boolean emailNotUnique = userDao.checkEmail(newEmail);
                    if (emailNotUnique == true) {
                        error = true;
                        msg = "That email is already in use by another user";
                    } else {
                        String username = logged_in.getUsername();
                        //email is unique so update it
                        boolean emailUpdated = userDao.updateEmail(username, newEmail);
                        if (emailUpdated == false) {
                            error = true;
                            msg = "Failed to update email";
                        } else {
                            msg = "Email updated";
                            logged_in.setEmail(newEmail);
                            session.setAttribute("logged_in", logged_in);
                        }
                    }
                }
            }
        }
        if(error){
            session.setAttribute("error_msg", msg);
            forwardToJsp = "view_profile.jsp";
        }else{
            session.setAttribute("notify_msg", msg);
            forwardToJsp = "view_profile.jsp";
        }
        return forwardToJsp;
    }

}
