/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import DAO.UserDao;
import DTO.User;
import DAO.AddressDao;
import DTO.Address;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Aaron
 */
public class UserUpdateAddressCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String msg = null;
        boolean error = false;
        String forwardToJsp = null;
        HttpSession session = request.getSession();
        UserDao userDao = new UserDao("gamestore");
        AddressDao addressDao = new AddressDao("gamestore");
        User logged_in = (User) session.getAttribute("logged_in");
       ArrayList< Address> addresses = (ArrayList<Address>) session.getAttribute("getAddress");
        if (logged_in == null) {
            error = true;
            msg = "You have to be logged in to use this functionality";
        } else {
            int userId = logged_in.getUserId();
            String Address1 = request.getParameter("addressLine1");
            String Address2 = request.getParameter("addressLine2");
            String city = request.getParameter("city");
            String county = request.getParameter("county");
            String country = request.getParameter("country");
            
            if (Address1 != null && Address2 != null && city !=null && county !=null && country !=null) {

                boolean updated = addressDao.updateAddress(userId, Address1, Address2, city, county, country);
                
                if(updated == true){
                    
                     for (Address a : addresses) {
                    a.setAddressLine1(Address1);
                    a.setAddressLine2(Address2);
                    a.setCity(city);
                    a.setCounty_state(county);
                    a.setCountry(country);
                     }
                    
                    msg = "Address successfully updated";
                }else{
                    error = true;
                    msg = "Address updated failed";
                }
            } else {
                error = true;
                msg = "Enter address again";
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

