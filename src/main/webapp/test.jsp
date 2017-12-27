<%-- 
    Document   : test
    Created on : 15-Dec-2016, 12:57:08
    Author     : Conno
--%>

<%@page import="DTO.Order"%>
<%@page import="DTO.OrderLine"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Test</title>
    </head>
    <body>
        <%
            Order test_order = (Order)session.getAttribute("test_order");
            
            out.print(test_order);
        %>
        
    </body>
</html>
