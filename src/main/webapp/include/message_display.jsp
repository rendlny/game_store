<%-- 
    Document   : message_display
    Created on : 10-Dec-2016, 01:19:48
    Author     : Conno
--%>
<%
    if (session.getAttribute("error_msg") != null) {
        out.print("<div class = 'error_container'>");
            out.print("<p class = 'message' >");
                 out.print(session.getAttribute("error_msg"));   
            out.print("</p>");
        out.print("</div>");
        
        session.setAttribute("error_msg", null);
    } else if (session.getAttribute("notify_msg") != null) {
        out.print("<div class = 'notify_container'>");
            out.print("<p class = 'message' >");
                 out.print(session.getAttribute("notify_msg"));   
            out.print("</p>");
        out.print("</div>");
        
        session.setAttribute("notify_msg", null);
    }
%>
