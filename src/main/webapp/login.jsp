<%-- 
    Document   : login
    Created on : 09-Dec-2016, 23:31:24
    Author     : Conno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="include/head.jsp"/>
        <link href="css/account.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <jsp:include page = "include/nav.jsp" />
        <main>
            <jsp:include page = "include/divider_top.jsp" />
            <jsp:include page = "include/message_display.jsp" />
            <form method = "post" action = "Controller">
                <input type = "hidden" name = "action" value = "login"/>
                <label>Username:</label><br/>
                <input type = "text" name = "username" placeholder = "Username" />
                <br/><br/>
                <label>Password:</label><br/>
                <input type = "password" name = "pass" placeholder = "Password" />
                <br/><br/>
                <input class = "button" type = "submit" value = "Login" />
                <input class = "button" type = "reset" value = "Reset" />
            </form>
            <jsp:include page = "include/divider_bottom.jsp" />
        </main>
    </body>
</html>
