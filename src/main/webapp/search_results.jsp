<%-- 
    Document   : search_results
    Created on : 12-Dec-2016, 17:04:08
    Author     : Ren
--%>

<%@page import="DAO.ProductImageDao"%>
<%@page import="DTO.ProductImage"%>
<%@page import="DTO.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="include/head.jsp"/>
        <link href="css/home.css" rel="stylesheet" type="text/css">
        <link href="css/search_grid.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <jsp:include page = "include/nav.jsp" />
        <main>
            <br/><br/>
            <jsp:include page = "include/message_display.jsp" />
            <% ArrayList<Product> products = (ArrayList<Product>) session.getAttribute("search_results");
                if (products.size() > 0) {

                    for (Product p : products) { 
                    ProductImageDao imageDao = new ProductImageDao("gamestore");
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
                 <%   }

                } else {
                    session.setAttribute("error_msg", "Error getting products, contact admin");
                    response.sendRedirect("index.jsp");
                }
            %>
        </main>
    </body>
</html>
