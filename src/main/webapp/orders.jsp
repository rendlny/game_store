<%-- 
    Document   : orders
    Created on : 14-Dec-2016, 23:05:17
    Author     : Ren
--%>

<%@page import="DTO.Product"%>
<%@page import="DAO.ProductDao"%>
<%@page import="DTO.OrderLine"%>
<%@page import="DAO.OrderLineDao"%>
<%@page import="DAO.AddressDao"%>
<%@page import="DTO.Order"%>
<%@page import="DAO.OrderDao"%>
<%@page import="DTO.Address"%>
<%@page import="DTO.User"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        User user = (User) session.getAttribute("logged_in");
        ArrayList<Address> addresses = (ArrayList<Address>) session.getAttribute("getAddress");
        if (user == null) {
            session.setAttribute("error_msg", "You must be logged in to access that page");
            response.sendRedirect("login.jsp");
        } else {
    %>
    <head>
        <jsp:include page="include/head.jsp"/>
        <link href="css/home.css" rel="stylesheet" type="text/css">
        <link href="css/orders.css" rel="stylesheet" type="text/css">
    </head>
    <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
    <script>
        $(document).ready(function ()
        {
            $num = 0;
            $('.orderLineTable').hide();
            $('.viewProductsBtn').click(function () {
                if ($num === 0) {
                    $id = (this).id;
                    $('#' + $id + '.orderLineTable').show();
                    $('#' + $id + '.viewProductsBtn').html('Hide Products');
                    $num = 1;
                } else {
                    $('#' + $id + '.orderLineTable').hide();
                    $('#' + $id + '.viewProductsBtn').html('View Products');
                    $num = 0;
                }
            });

        });
    </script>
    <body>
        <jsp:include page = "include/nav.jsp" />
        <br/><br/>
        <jsp:include page = "include/message_display.jsp" />
        <main>
            <br/><br/>
            <h1>Orders</h1>
            <br/>
            <%
                OrderDao orderDao = new OrderDao("gamestore");
                ArrayList<Order> orders = orderDao.getUserOrders(user.getUserId());

                AddressDao addressDao = new AddressDao("gamestore");
                ArrayList<Address> addressLines = addressDao.getAddressByUserId(user.getUserId());
                Address a = addressLines.get(0);
                String address = a.getAddressLine1() + ", "
                        + a.getAddressLine2() + ", " + a.getCity() + ", "
                        + a.getCounty_state() + ", " + a.getCountry();

                int id = 1;
                if (orders.size() > 0) {
            %>

            <%
                for (Order o : orders) {
            %>
            <table class = "ordersTable">
                <%
                    if (id == 1) {
                %>
                <tr>
                    <th>Date</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Address</th>
                    <th></th>
                </tr>
                <%
                    }
                %>
                <tr>
                    <td><%=o.getOrder_date()%></td>
                    <td><%=o.getTotal_quanity()%></td>
                    <td><%=o.getTotal_amount_due()%></td>
                    <td><%=address%></td>
                    <td><button class="viewProductsBtn" id ="<%=id%>">View Products</button></td>
                </tr>
            </table>

            <table class="orderLineTable"  id ="<%=id%>">
                <tr>
                    <th>Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                </tr>
                <%
                    OrderLineDao oLineDao = new OrderLineDao("gamestore");
                    ArrayList<OrderLine> orderLines = oLineDao.getOrderLines(o.getOrder_id());
                    ProductDao productDao = new ProductDao("gamestore");
                    for (OrderLine l : orderLines) {
                        Product p = productDao.getProductById(l.getProduct_id());
                %>
                <tr>
                    <td><%=p.getProduct_name()%></td>
                    <td><%=l.getQuantity()%></td>
                    <td><%=l.getSale_price()%></td>
                </tr>
                <%
                    }
                %>
            </table>



            <%
                    id++;
                }
            %>
        </table>
        <%
            } else {
                out.print("You've made no orders");
            }
        %>
    </main>
</body>
<% }%>
</html>
