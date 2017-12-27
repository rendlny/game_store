<%-- 
    Document   : add_product
    Created on : 10-Dec-2016, 16:41:58
    Author     : Ren
--%>

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
    %>
    <head>
        <jsp:include page="include/head.jsp"/>
        <link href="css/product.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <jsp:include page = "include/nav.jsp" />
        <main>
            <jsp:include page = "include/message_display.jsp" />
            <form class = "add_form" method="post" enctype="multipart/form-data" action="Controller">
                <input type="hidden" name="action" value="add_product"/>

                <div class = "product_image_display">
                    
                    <img class = "image" src = "image/icon/none_available.png" alt = "sample_image"/>
                    <label for="image">Image:</label><br/>
                    <input accept="image/*" name="image" type="file" required/><br/><br/>
                </div>
                
                <div class = "product_details_display">
                    
                    <div class = "name_input">
                        <label for="product_name">Name:</label><br/>
                        <input name="product_name" type="text" required/><br/><br/>
                    </div>
                    <div class = "price_input">
                        <label for="product_price">Price:</label><br/>
                        <input name="product_price" type="number" step="any" required/><br/><br/>
                    </div>
                    <div class = "vat_input">
                        <label for="product_vat">VAT %:</label><br/>
                        <input name="product_vat" type="number" step="any" required/><br/><br/>
                    </div>
                    <hr/>
                    <div class = "desc_input">
                        <label for="product_desc">Description:</label><br/>
                        <textarea name="product_desc" required></textarea><br/><br/>

                        <label for="product_stock">Stock:</label><br/>
                        <input name="product_stock" type="number" required/><br/><br/>
                        
                        <label for="steam_app_id">Steam App ID:</label><br/>
                        <input name="steam_app_id" type="number"/><br/><br/>
                    </div>
                </div>
                
                <div class = "button_input">
                    <input type="submit" name="submit" value="Add"/>
                    <input type="reset" name="reset" value="Reset"/>    
                </div>
            </form>
        </main>
    </body>
    <% }
        }%>
</html>
