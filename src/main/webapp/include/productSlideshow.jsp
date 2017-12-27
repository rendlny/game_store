<%-- 
    Document   : productSlideshow
    Created on : 13-Dec-2016, 17:35:45
    Author     : Ren
--%>
<%@page import="DTO.ProductImage"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DAO.ProductImageDao"%>
<link href="css/slideshow.css" rel="stylesheet" type="text/css">
<%
    int product_id = 0;
    try {
        product_id = Integer.parseInt(request.getParameter("product_id"));
    } catch (Exception e) {
        out.print("Failed to get product id");
    }
    ProductImageDao pImgDao = new ProductImageDao("gamestore");
    ArrayList<ProductImage> images = pImgDao.getProductImageById(product_id);
    int currentImg = 0;
    int totalImg = images.size();
%>
<div class="slideshow-container">
    <% for (ProductImage img : images) { %>
    <div class="mySlides fade">
        <div class="numbertext"><%=currentImg %> / <%=totalImg %></div>
        <img class="displayImage" src="<%=img.getImage_url() %>" style="width:100%">
    </div>
    <% currentImg++; } %>
    <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
    <a class="next" onclick="plusSlides(1)">&#10095;</a>
</div>
<br>

<div style="text-align:center">
    <% for(int i = 1; i<=images.size(); i++){ %>
    <span class="dot" onclick="currentSlide(<%=i %>)"></span> 
    <% } %>
</div>
<script type="text/javascript" src="js/slideshow.js"></script>
