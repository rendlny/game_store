<%-- 
    Document   : profile
    Created on : 12-Dec-2016, 19:11:27
    Author     : Ren
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="DTO.Address"%>
<%@page import="DTO.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <% User user = (User) session.getAttribute("logged_in");
        ArrayList<Address> addresses = (ArrayList<Address>) session.getAttribute("getAddress");
        if (user == null) {
            session.setAttribute("error_msg", "You must be logged in to access that page");
            response.sendRedirect("login.jsp");
        } else {
    %>
    <head>
        <jsp:include page="include/head.jsp"/>
        <link href="css/home.css" rel="stylesheet" type="text/css">
        <link href="css/settings.css" rel="stylesheet" type="text/css">

    </head>
    <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
    <script>
        $(document).ready(function ()
        {
            //name form
            $('#nameForm').hide();
            $('#nameBtn').click(function () {
                $('#nameForm').show();
                $('#nameBtn').hide();
            });

            //password form
            $('#passForm').hide();
            $('#passBtn').click(function () {
                $('#passForm').show();
                $('#passBtn').hide();
            });

            //email form
            $('#emailForm').hide();
            $('#emailBtn').click(function () {
                $('#emailForm').show();
                $('#emailBtn').hide();
            });

            //Address form
            $('#addFrom').hide();
            $('#AddBtn').click(function () {
                $('#addFrom').show();
                $('#AddBtn').hide();
            });
        });

    </script>
    <body>
        <jsp:include page = "include/nav.jsp" />
        <main>
            <br/><br/>
            <jsp:include page = "include/message_display.jsp" />

            <h1>Settings Menu</h1>
            <section>
                <h2>Name: <%=user.getFirstName()%> <%=user.getLast_name()%></h2><br>
                <button id="nameBtn">Change Name</button>
                <form class="update_form" id="nameForm" method="post" action="Controller">
                    <input type = "hidden" name= "action" value = "update_name" />
                    <label for="first_name">First Name:</label><br/>
                    <input class="text_input" type="text" name="first_name" placeholder="Firstname" value="<%=user.getFirstName()%>" required/><br/>
                    <label for="last_name">Last Name:</label><br/>
                    <input class="text_input" type="text" name="last_name" placeholder="Lastname" value="<%=user.getLast_name()%>" required/><br/>
                    <input class="button" type="submit" name="submit" value="Update Name" />
                </form>
            </section>



            <section>
                <h2> Password: ********* </h2><br>
                <button id="passBtn">Change Password</button>
                <form class="update_form" id="passForm" method="post" action="Controller">
                    <input type = "hidden" name= "action" value = "update_password" />
                    <label for="old_pass">Password: must contain a number, capital and lowercase letter</label><br/>
                    <input class="text_input" type="password" name="old_pass" placeholder="Old password" required/><br/>
                    <input class="text_input" type="password" name="new_pass" placeholder="New password" required/><br/>
                    <input class="text_input" type="password" name="conf_pass" placeholder="Confirm password" required/><br/>
                    <input class="button" type="submit" name="submit" value="Update Password" />
                </form>
            </section>



            <section>
                <h2>Email: <%=user.getEmail()%> </h2><br>
                <button id="emailBtn">Change Email</button>
                <form class="update_form" id="emailForm" method="post" action="Controller">
                    <input type = "hidden" name="action" value = "update_email" />
                    <label for="email">Email:</label><br/>
                    <input class="text_input" type="email" name="email" placeholder="You@Awesome.com" value="<%= user.getEmail()%>" required/>
                    <input class="button" type="submit" name="submit" value="Update Email" />
                </form>
            </section>

            <section>
                <h2>Update address</h2><br>
                <button id="AddBtn">Change Address</button>
                <form class="update_form" id="addFrom" method="post" action="Controller">          
                    <input type = "hidden" name= "action" value = "update_address" />

                    <%
                        if (addresses != null && !addresses.isEmpty()) {
                            for (Address a : addresses) {
                    %>
                    <label for="first_name">Enter Address Line 1</label><br/>
                    <input class="text_input" type="text" name="addressLine1" placeholder="address line 1" value="<%=a.getAddressLine1()%>" required/><br/>
                    <label for="first_name">Enter Address Line 2</label><br/>
                    <input class="text_input" type="text" name="addressLine2" placeholder="address line 2" value="<%=a.getAddressLine2()%>" required/><br/>
                    <label for="first_name">Enter city</label><br/>
                    <input class="text_input" type="text" name="city" placeholder="city" value="<%=a.getCity()%>" required/><br/>
                    <label for="first_name">Enter county</label><br/>
                    <input class="text_input" type="text" name="county" placeholder="county" value="<%=a.getCounty_state()%>" required/><br/>
                    <label for="first_name">Enter country</label><br/>
                    <input class="text_input" type="text" name="country" placeholder="country" value="<%=a.getCountry()%>" required/><br/>
                    <% }
                        }%>
                    <input class="button" type="submit" name="submit" value="Update Address" />
                </form>
            </section>

            <jsp:include page = "include/divider_bottom.jsp" />
        </main>
    </body>
    <% }%>
</html>
