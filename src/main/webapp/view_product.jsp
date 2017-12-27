<%-- 
    Document   : view_product
    Created on : 12-Dec-2016, 14:54:13
    Author     : Ren
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="REST_Steam_news.Newsitem"%>
<%@page import="REST_Steam_news.Appnews"%>
<%@page import="REST_Steam_news.RootObject"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.awt.Image"%>
<%@page import="java.io.InputStream"%>
<%@page import="DAO.ProductDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DTO.ProductImage"%>
<%@page import="DAO.ProductImageDao"%>
<%@page import="DTO.User"%>
<%@page import="DTO.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
            ProductDao productDao = new ProductDao("gamestore");
            Product display_product = null;
            if (request.getParameter("product_id") != null) {
                try {
                    int product_id = Integer.parseInt(request.getParameter("product_id"));
                    display_product = productDao.getProductById(product_id);
                } catch (NumberFormatException ex) {/*Ignore me*/
                }
            }
            User user = (User) session.getAttribute("logged_in");
        %>
        <jsp:include page="include/head.jsp"/>
        <link href="css/product.css" rel="stylesheet" type="text/css">
    </head>
    <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
    <script>
        $(document).ready(function ()
        {
            //image upload form
            $('#imgForm').hide();
            $('#imgBtn').click(function () {
                $('#imgForm').show();
                $('#imgBtn').hide();

                //hide other forms
                $('#deleteForm').hide();
                $('#editForm').hide();

                //show other buttons
                $('#deleteBtn').show();
                $('#editBtn').show();
            });

            //delete image form
            $('#deleteForm').hide();
            $('#deleteBtn').click(function () {
                $('#deleteForm').show();
                $('#deleteBtn').hide();

                //hide other forms
                $('#editForm').hide();
                $('#imgForm').hide();

                //show other buttons
                $('#editBtn').show();
                $('#imgBtn').show();
            });

            //edit product details form
            $('#editForm').hide();
            $('#editBtn').click(function () {
                $('#editForm').show();
                $('#editBtn').hide();

                //hide other forms
                $('#deleteForm').hide();
                $('#imgForm').hide();

                //show other buttons
                $('#deleteBtn').show();
                $('#imgBtn').show();
            });

            //News area
            $(".divs div").each(function (e) {
                if (e != 0)
                    $(this).hide();
            });
            //next button
            $('#brnNextNews').click(function () {
                if ($(".divs div:visible").next().length != 0)
                    $(".divs div:visible").next().show().prev().hide();
                else {
                    $(".divs div:visible").hide();
                    $(".divs div:first").show();
                }
                return false;
            });

            //previous button
            $('#brnPrevNews').click(function () {
                if ($(".divs div:visible").prev().length !== 0)
                    $(".divs div:visible").prev().show().next().hide();
                else {
                    $(".divs div:visible").hide();
                    $(".divs div:last").show();
                }
                return false;
            });
        });
    </script>
    <body>
        <jsp:include page = "include/nav.jsp" />
        <main>
            <br/><br/>
            <jsp:include page = "include/message_display.jsp" />
            <%
                if (display_product != null) {
                    String product_name = display_product.getProduct_name();
                    String product_desc = display_product.getProduct_desc();
                    double product_price = display_product.getProduct_price();
                    double product_vat = display_product.getVat_percentage();
                    double sale_price = product_price + (product_price * product_vat);
                    int product_stock = display_product.getStock();
                    int product_id = display_product.getProduct_id();
                    int steam_app_id = display_product.getSteam_app_id();

                    ProductImageDao pImgDao = new ProductImageDao("gamestore");
                    ArrayList<ProductImage> images = pImgDao.getProductImageById(product_id);
                    String img_url = null;
                    int totalImages = images.size();

            %>
            <div class = "product_image_display">

                <% if (totalImages > 1) { %>
                <jsp:include page = "include/productSlideshow.jsp" />
                <% } else if (totalImages == 1) {
                    ProductImage img = images.get(0);
                %>
                <img class = "image" src = "<%=img.getImage_url()%>" alt = "<%= product_name%>" />

                <% } else {
                        out.print("No image");
                    }
                %>

            </div>

            <div class = "product_details_display">
                <h2><%= product_name%></h2><p class = "price">Price: <%=sale_price%>  @ <%=product_vat%> VAT</p>
                <hr/>
                <h3>About <%= product_name%>:</h3>
                <p class = "description"><%= product_desc%></p>
                <%
                    if (product_stock > 0) {
                %>
                <form method = "post" action = "Controller">
                    <input type = "hidden" name = "action" value = "add_to_cart" />
                    <input type = "hidden" name = "product_id" value ="<%= product_id%>" />
                    <input type = "hidden" name = "sale_price" value ="<%= sale_price%>" />
                    <select name = "quantity">
                        <%
                            for (int i = 1; i <= product_stock; i++) {
                        %>
                        <option value = "<%= i%>" ><%= i%></option>
                        <%
                            }
                        %>
                    </select>
                    <input type = "submit" value = "Add to Cart" />
                </form>
                <%
                    }

                    //Get game news here
                    if (steam_app_id > 0) {
                %>
                <br/><hr/>
                <h3>Steam News:</h3>
                <%
                    RootObject r = new RootObject();
                    r = r.getNews(steam_app_id);
                    Appnews appnews = r.getAppnews();
                    List<Newsitem> news = appnews.getNewsitems();
                %>
                <div class="divs">
                    <%
                        for (int i = 0; i < news.size(); i++) {
                            Newsitem n = news.get(i);
                            SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMdd");
                            Date date = originalFormat.parse((n.getDate()).toString());
                    %>
                    <div>
                        <p><%=date %></p><br/><br/>
                        <p class="newsContent"><%=n.getContents()%></p>
                        <br/><br/>
                        <p><%=i + 1%>/<%=news.size()%></p>
                        <br/>
                    </div>

                    <%
                        }
                    %>
                </div>
                <%
                    if (news.size() > 1) {

                %>
                <br/>
                <button id="brnPrevNews">Previous</button><button id="brnNextNews">Next</button>
                <%                        }
                    }

                    //admin functionality
                    if (user != null && user.getIs_admin() == 1) {
                %>
                <br/><br/>
                <hr/><br/>
                <h3>Admin Functions:</h3><br/><br/>
                <button id="imgBtn">Add Image</button>
                <form id="imgForm" enctype="multipart/form-data" method="post" action="Controller">
                    <input type="hidden" name="action" value="add_product_image"/>
                    <label for="image"><p>Upload Image:</p></label><br/>
                    <input name="image" accept="image/*" type="file" required/><br/><br/>
                    <input type="hidden" name="product_id" type="text" value="<%=product_id%>"/>
                    <input type="submit" name="submit" value="Add"/>
                </form>
                <br/><br/>
                <button id="deleteBtn">Delete Image</button>
                <form id="deleteForm" method="post" action="Controller">
                    <input type="hidden" name="action" value="delete_product_image"/>
                    <label for="imageSelect"><p>Choose Image:</p></label><br/>
                    <select name="imageSelect">
                        <%
                            int img_num = 1;
                            for (ProductImage i : images) {
                        %>
                        <option value="<%=i.getImage_id()%>"><%=img_num%> </option>
                        <% img_num++;
                            }%>
                    </select>
                    <input type="hidden" name="product_id" value="<%=product_id%>"/>
                    <input type="submit" name="submit" value="Delete"/>
                </form>
                <br/><br/>
                <button id="editBtn">Edit Details</button>
                <form id="editForm" method="post" action="Controller">
                    <input type="hidden" name="action" value="update_product"/>
                    <label for="product_name">Name:</label><br/>
                    <input name="product_name" type="text" value="<%=product_name%>" required/><br/><br/>

                    <label for="product_price">Price:</label><br/>
                    <input name="product_price" type="number" step="any" value="<%=product_price%>" required/><br/><br/>

                    <label for="product_vat">VAT %:</label><br/>
                    <input name="product_vat" type="number" step="any" value="<%=product_vat%>" required/><br/><br/>

                    <label for="product_desc">Description:</label><br/>
                    <textarea name="product_desc" required><%=product_desc%></textarea><br/><br/>

                    <label for="product_stock">Stock:</label><br/>
                    <input name="product_stock" type="number" value="<%=product_stock%>" required/><br/><br/>
                    <input type="hidden" name="product_id" value="<%=product_id%>"/>
                    <input type="submit" name="submit" value="Update"/>
                    <input type="reset" name="reset" value="Reset"/>    
                </form>
                <%
                    } %>
            </div> 
            <%     } else {
                    session.setAttribute("error_msg", "We cant show you this product currently, please try again later");
                    response.sendRedirect("index.jsp");
                }
            %>
        </main>
    </body>
</html>
