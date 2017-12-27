<%-- 
    Document   : nav
    Created on : 30-Nov-2016, 19:47:28
    Author     : Conno
--%>
<%@page import="DTO.OrderLine"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DTO.User"%>
<%
    User logged_in = null;
    if (session.getAttribute("logged_in") != null) {
        logged_in = (User) session.getAttribute("logged_in");
    }
%>

<div class="bar">
    <nav>
        <ul>
            <audio id="icon_sound" src="audio/icon_jingle.mp3"></audio>
            <img class="logo" src="image/icon/logo.png" alt="GameStore" onclick="playSound()"/>
            <a href = "index.jsp"><li>Home</li></a>
                    <%
                        if (logged_in != null && logged_in.getIs_admin() == 1) {
                    %>
            <div class = "left dropdown">
                <a><li>Admin</li></a>
                <div class="dropdown-content">
                    <a href="add_product.jsp">Add product</a>
                </div>
            </div>
            <%
                }
            %>
            <%
                if (logged_in != null) {
            %>
            <a class = "right" href = "cart.jsp">
                <li>
                    Cart
                    <%
                        if(session.getAttribute("cart") != null) {
                            ArrayList<OrderLine> cart = (ArrayList<OrderLine>)session.getAttribute("cart");                        
                    %>
                    <div class="cart_container">
                        <span class='cart_counter'>
                            <%=cart.size()%>
                        </span>
                    </div>
                    <%
                        }
                    %>
                </li>
            </a>
            <div class = "right dropdown">
                <a class = "dropdown-trigger" href = "view_profile.jsp"><li><%= logged_in.getUsername()%></li></a>
                <div class="dropdown-content">
                    <a href="view_profile.jsp">Profile</a>
                    <a href="orders.jsp">Orders</a>
                    <a href="settings.jsp">Settings</a>
                    <a href="logout.jsp">Logout</a>
                </div>
            </div>
            <%
            } else {
            %>
            <a class = "right" href = "sign_up.jsp"><li>Sign Up</li></a>
            <a class = "right" href = "login.jsp"><li>Login</li></a>
                    <%
                        }
                    %>
            <li class  = "search_container">
                <form method = "post" action = "Controller">
                    <input type = "hidden" name = "action" value = "search_products" />
                    <input class = "search_bar" type = "text" name = "search_critera" placeholder = "Search..." >
                </form>
            </li>
        </ul>
    </nav>
</div>

<script type="text/javascript" src="js/play.js"></script>