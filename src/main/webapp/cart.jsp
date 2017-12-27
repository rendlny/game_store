<%-- 
    Document   : cart
    Created on : 13-Dec-2016, 22:07:40
    Author     : Conno
--%>

<%@page import="DAO.ProductImageDao"%>
<%@page import="DTO.ProductImage"%>
<%@page import="DTO.Product"%>
<%@page import="DAO.ProductDao"%>
<%@page import="DTO.OrderLine"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DTO.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="include/head.jsp"/>
        <link href="css/cart.css" rel="stylesheet" type="text/css">
        <%
            User logged_in = null;
            if (session.getAttribute("logged_in") != null) {
                logged_in = (User) session.getAttribute("logged_in");
            } else {
                session.setAttribute("error_msg", "You must be logged in to access cart");
                response.sendRedirect("login.jsp");
            }
        %>
    </head>
    <body>
        <jsp:include page = "include/nav.jsp" />
        <main>
            <br/><br/>
            <jsp:include page = "include/message_display.jsp" />
            <h1>Your Shopping Cart: </h1>
            <%
                if (session.getAttribute("cart") != null) {
                    ProductDao productDao = new ProductDao("gamestore");
                    ProductImageDao imageDao = new ProductImageDao("gamestore");
                    ArrayList<OrderLine> cart = (ArrayList<OrderLine>) session.getAttribute("cart");
                    double total_amount = 0.0;
                    int total_items = 0;
                    int index = 0;
                    for (OrderLine line : cart) {
                        Product p = productDao.getProductById(line.getProduct_id());
                        ArrayList<ProductImage> images = imageDao.getProductImageById(p.getProduct_id());
                        int quantity = line.getQuantity();
                        double price_per_item = line.getSale_price();
                        double line_total = quantity * price_per_item;
                        total_items += quantity;
                        total_amount += line_total;
            %>
            <div class = "line_container">
                <div class = "details_container">
                    <h2><%= p.getProduct_name()%></h2>
                    <hr/>
                    <p>Price Per Item: <%= price_per_item%></p>
                    <p>quantity: <%= quantity%></p>
                    <p>Total: <%= line_total%></p>
                    <form method = "post" action="Controller">
                        <input type = "hidden" name = "action" value= "remove_from_cart" />
                        <input type = "hidden" name = "index" value= "<%=index%>" />
                        <input type = "submit" value= "Remove" />
                    </form>
                </div>
                <div class = "image_container">
                    <img src = "<%=images.get(0).getImage_url()%>" alt = "no image" /> 
                </div>
            </div>
            <%
                    index++;
                }
            %>
            <div class = "total_contaier">
                <hr/>
                <div class = "value_container">
                    <p><span>Total Quantity: </span><%= total_items%></p>
                    <p><span>Order Total: </span><%= total_amount%></p>
                </div>
                <form class="confirm" method="post" action="Controller">
                    <input type = "hidden" name = "action" value = "create_order" />
                    <input type = "hidden" name = "total_quantity" value = "<%=total_items%>" />
                    <input type = "hidden" name = "total_amount_due" value = "<%=total_amount%>" />
                    <input type = "submit" value= "Confirm" />
                </form>
            </div>
            <%
            } else {
            %>
            <p class = "empty_cart_msg">
                There is nothing in your cart at the moment! 
                Try searching for something you may like.
            </p>
            <%
                }

            %>

        </main>
    </body>
</html>
