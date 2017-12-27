<%-- 
    Document   : index
    Created on : 30-Nov-2016, 19:50:32
    Author     : Conno
--%>
<%@page import="DTO.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="include/head.jsp"/>
        <link href="css/home.css" rel="stylesheet" type="text/css">
        <link href="css/grid.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <jsp:include page = "include/nav.jsp" />
        <main>
            <br/><br/>
            <jsp:include page = "include/message_display.jsp" />
            <jsp:include page = "include/slideshow.jsp" />
            <jsp:include page = "include/divider_bottom.jsp" />
            
            
            <h2>Recent Products:</h2>
            <hr/>
            <jsp:include page = "include/index_grid.jsp" />
            
        </main>
    </body>
</html>
