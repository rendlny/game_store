<%-- 
    Document   : add_product_image
    Created on : 10-Dec-2016, 18:40:25
    Author     : Ren
--%>

<%@page import="DTO.Product"%>
<%@page import="DTO.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        if (session.getAttribute("logged_in") == null) {
            session.setAttribute("error_msg", "You must be logged in to access that page");
            response.sendRedirect("login.jsp");
        } else {
            User u = (User) session.getAttribute("logged_in");
            if (u.getIs_admin() == 0) {
                session.setAttribute("error_msg", "You must be an admin to access that page");
                response.sendRedirect("index.jsp");
            } else {
                Product p = (Product) session.getAttribute("product");
                int p_id = p.getProduct_id();
    %>
    <head>
        <jsp:include page="include/head.jsp"/>
        <link href="css/account.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <main>
            <jsp:include page = "include/divider_top.jsp" />
            <jsp:include page = "include/message_display.jsp" />
            <form method="post" enctype="multipart/form-data" action="Controller">
                <input type="hidden" name="action" value="add_product_image"/>
                <input type="hidden"  name="product_id" value="<%=p_id%>"/>
                <label for="image">Upload Image:</label><br/>
                <input name="image" type="file" size="50" required/><br/><br/>
                <input type="submit" name="submit" value="Upload"/>
            </form>
        </main>
    </body>
    <% }
        }%>
</html>
