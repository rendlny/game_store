<%-- 
    Document   : view_profile.jsp
    Created on : 14-Dec-2016, 13:12:32
    Author     : Aaron
--%>

<%@page import="DTO.Address"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DTO.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <% User user = (User) session.getAttribute("logged_in");
        ArrayList<Address> addresses = (ArrayList<Address>) session.getAttribute("getAddress");

        if (user == null) {
            session.setAttribute("error_msg", "You must be logged To view Your profile");
            response.sendRedirect("login.jsp");
        } else {
    %>
    <head>
        <jsp:include page="include/head.jsp"/>
        <link href="css/home.css" rel="stylesheet" type="text/css">
        <link href="css/profile.css" rel="stylesheet" type="text/css">
    </head>
    <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
    <body>
        <jsp:include page = "include/nav.jsp" />
        <main>
            <br/><br/>
            <jsp:include page = "include/message_display.jsp" />
            <h1>Welcome <%=user.getUsername()%></h1><br>
            <h2>You can find your current information below</h2>

            <section class="PersonalInfo">
                <table>
                    <tr>
                        <td>First Name : </td>
                        <td><%=user.getFirstName()%></td><br>
                    </tr>
                    <tr>

                        <td>Second Name : </td>
                        <td><%=user.getLast_name()%></td>
                    </tr>
                    <tr>
                        <td>Email : </td>
                        <td><%=user.getEmail()%></td>
                    </tr>
                    <tr>
                        <td>usermame : </td>
                        <td><%=user.getUsername()%></td>
                    </tr>
                    <%
                        if (addresses != null && !addresses.isEmpty()) {
                            for (Address a : addresses) {
                    %>
                    <tr>
                        <td>Address Line 1</td>
                        <td><%=a.getAddressLine1()%></td>
                    </tr>
                    <tr>
                        <td>Address Line 2</td>
                        <td><%=a.getAddressLine2()%></td>
                    </tr>
                    <tr>
                        <td>City</td>
                        <td><%=a.getCity()%></td>
                    </tr>
                    <tr>
                        <td>County</td>
                        <td><%=a.getCounty_state()%></td>
                    </tr>
                    <tr>
                        <td>Country</td>
                        <td><%=a.getCountry()%></td>
                    </tr>


                </table>

            </section><br><br>
            <h3>If you would like to ammend any of your information, you can do so on the <a class="link" href="settings.jsp">Settings</a> Page</h3>

            <jsp:include page = "include/divider_bottom.jsp" />
        </main>
    </body>
    <% }
            }
        }%>
</html>
