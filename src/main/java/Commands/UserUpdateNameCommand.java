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
public class UserUpdateNameCommand implements Command {

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
            String newFirstName = request.getParameter("first_name");
            String newLastName = request.getParameter("last_name");
            if (newFirstName != null && newLastName != null) {

                boolean updated = userDao.updateName(newFirstName, newLastName, logged_in.getUserId());
                if(updated == true){
                    logged_in.setFirstName(newFirstName);
                    logged_in.setLast_name(newLastName);
                    msg = "Name successfully updated";
                }else{
                    error = true;
                    msg = "Name updated failed";
                }
            } else {
                error = true;
                msg = "Enter new first name and last name";
            }
        }

        if (error) {
            session.setAttribute("error_msg", msg);
            forwardToJsp = "index.jsp";
        } else {
            session.setAttribute("notify_msg", msg);
            forwardToJsp = "view_profile.jsp";
        }
        return forwardToJsp;
    }

}
