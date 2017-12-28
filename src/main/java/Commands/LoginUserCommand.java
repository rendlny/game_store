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
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Conno
 */
public class LoginUserCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        
        HttpSession session = request.getSession();
        String forwardToJsp = null, message = null;
        boolean error = false;
        
        String username = request.getParameter("username");
        String pass = request.getParameter("pass");
        
        if((username != null && !username.equals("")) && 
            (pass != null && !pass.equals(""))) {
            
            UserDao userDao = new UserDao("gamestore");
            
            User user_check = userDao.checkLogin(username, pass);
            
            if(user_check != null) {
            session.setAttribute("logged_in", user_check);
            int user_id = user_check.getUserId();
            AddressDao addressDao = new AddressDao("gamestore");
            ArrayList<Address> addresses = addressDao.getAddressByUserId(user_id);
            session.setAttribute("getAddress", addresses);
            } else {
                message = "No User with matching details found";
                error = true;
            }
        
        } else {
            message = "Missing values, Cannot register with incomplete data";
            error = true;
        }
        
        if(!error) {
            forwardToJsp = "index.jsp";
        } else {
            session.setAttribute("error_msg", message);
            forwardToJsp = "login.jsp";
        }
        
        return forwardToJsp;
    }
    
}
