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
                
                <input type = "hidden" name = "action" value = "sign_up" />
                
                <label>Name:</label><br/>
                <div class = "name_container">
                    <input class = "name_input" type = "text" name = "first_name" placeholder = "First Name" />
                    <input class = "name_input" type = "text" name = "last_name" placeholder = "Last Name" />
                </div>
                <br/><br/>
                <label>Username:</label><br/>
                <input type = "text" name = "username" placeholder = "Username" />
                <br/><br/>
                <label>Email:</label><br/>
                <input type = "text" name = "email" placeholder = "You@Awesome.com" />
                <br/><br/>
                <label>Password:</label><br/>
                <input type = "password" name = "pass" placeholder = "At least 9 characters, 1 upper and lower case, and one number" />
                <br/><br/>
                <label>Confirm Password:</label><br/>
                <input type = "password" name = "conf_pass" placeholder = "Confirm Password" />
                <br/><br/>
                <label>Address Line 1:</label><br/>
                <input type = "Address1" name = "add1" placeholder = "address Line 1" />
                <br/><br/>
                <label>Address Line 2</label><br/>
                <input type = "Address2" name = "add2" placeholder = "address Line 2" />
                <br/><br/>
                <label>City:</label><br/>
                <input type = "City" name = "city" placeholder = "City" />
                <br/><br/>
                <label>County</label><br/>
                <input type = "county" name = "county" placeholder = "County" />
                <br/><br/>
                <label>Country</label><br/>
                <input type = "country" name = "country" placeholder = "Country" />
                <br/><br/>
                <input class = "button" type = "submit" value = "Sign Up" />
                <input class = "button" type = "reset" value = "Reset" />
            </form>
            <jsp:include page = "include/divider_bottom.jsp" />
        </main>
    </body>
</html>
