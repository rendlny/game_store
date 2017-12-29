<%--
    Document   : grid
    Created on : 14-Dec-2016, 13:20:36
    Author     : Conno
--%>
<%@page import="DTO.ProductImage"%>
<%@page import="DAO.ProductImageDao"%>
<%@page import="DTO.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DAO.ProductDao"%>
<%
    ProductDao productDao = new ProductDao("gamestore");
    ProductImageDao imageDao = new ProductImageDao("gamestore");

    ArrayList<Product> products = productDao.recentProducts();
    if(products.size() > 0 || products == null) {
%>
<div class="rig">
    <%
        for(Product p : products) {

            ArrayList<ProductImage> images = imageDao.getProductImageById(p.getProduct_id());

    %>
    <div class = "product_container">
        <a href = "view_product.jsp?product_id=<%=p.getProduct_id()%>">
            <img src="<%= images.get(0).getImage_url() %>" />
            <div class = "info_container">
                <h3><%= p.getProduct_name() %></h3>
            </div>
        </a>
    </div>
    <%
        }
    %>
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
