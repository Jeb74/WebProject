<%@page import="it.css.blackmarket.utils.exceptions.AccessException"%>
<%@page import="it.css.blackmarket.servlet.LoginServlet"%>
<%@taglib prefix="std" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>

<%
    request.getSession().setAttribute("index", 2);
%>

<std:if test="${sessionScope.get('username') == null}">
    <%AccessException.raiseIllegalAccessError("product upload section", request, response);%>
</std:if>

<!DOCTYPE html>
<html lang="en">
    <%@include file="jspfs/head.jspf" %>
    <body>
        <%@include file="jspfs/header.jspf" %>
        <%@include file="jspfs/navbar.jspf" %>
        <div class="clearfix">
            <main class="col-9">
                <div class="content common-text common-theme" id="main-content">
                    <section>
                        <h2>Sell your items:</h2>
                        <form action="new-product" method="post" enctype="multipart/form-data">
                            <label for="item_name">* Item name</label>
                            <span id="wordcount_item_name"></span>
                            <span id="error_item_name"></span>
                            <input type="text" name="item_name" id="item_name" required>
                            
                            <label for="item_description">* Description</label>
                            <span id="wordcount_item_description"></span>
                            <span id="error_item_description"></span>
                            <textarea name="item_description" id="item_description" required></textarea>

                            <label for="item_quantity">* Quantity</label>
                            <span id="error_item_quantity"></span>
                            <input type="number" name="item_quantity" id="item_quantity" min="1" max="999" value="1" required>
                            
                            <label for="item_price">* Price per item</label>
                            <span id="error_item_price"></span>
                            <input type="number" name="item_price" id="item_price" min="0.1" step="0.1" required>
                            
                            <label for="item_image">Item image</label>
                            <input type="file" name="item_image" id="item_image">
                            
                            <label for="item_city">* Where is the item? (Royal City or Map)</label>
                            <span id="wordcount_item_city"></span>
                            <span id="error_item_city"></span>
                            <input type="text" name="item_city" id="item_city">
                            
                            <input type="submit" name="item_submit" id="submit" value="ADD ITEM" required>
                        </form>
                    </section>
                </div>
            </main>
            <%@include file="jspfs/ads.jspf" %>
        </div>
        <%@include file="jspfs/footer.jspf" %>
    </body>
</html>