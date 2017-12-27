<%-- 
    Document   : logout
    Created on : 10-Dec-2016, 13:49:45
    Author     : Conno
--%>
<%
    if(session.getAttribute("logged_in") != null) {
        session.invalidate(); 
    } else {
        session.setAttribute("error_msg", "You have to be logged in to logout");
    }
    
    response.sendRedirect("index.jsp");
%>
