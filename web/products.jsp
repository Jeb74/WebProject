<%@page import="it.css.blackmarket.utils.exceptions.AccessException"%>
<%@page import="it.css.blackmarket.database.entities.DBProduct"%>
<%@taglib prefix="std" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>

<%
    request.getSession().setAttribute("index", 1);
%>

<std:if test="${sessionScope.get('username') == null}">
    <%AccessException.raiseIllegalAccessError("list of products", request, response);%>
</std:if>

<!DOCTYPE html>
<html lang="en">
    <%@include file="jspfs/head.jspf" %>
    <body>
        <%@include file="jspfs/header.jspf" %>
        <%@include file="jspfs/navbar.jspf" %>
        <div class="clearfix">
            <main class="col-9">
                <div class="content common-text" id="main-content">
                    <div class="common-theme-single">
                        <section class="product-list about">
                            <div class="about-image">
                                <img class="smoke-border" src="./assets/weapons.gif" id="product-image" alt="$NAME">
                            </div>
                            <section>
                                <h2 id="product-name">Loading name...</h2>
                                <p id="product-qty-price">Loading price...</p>
                                <p id="product-description">Loading description...</p>
                                <p id="product-seller-city">Loading seller and location ...</p>
                            </section>
                        </section>
                        <section class="switch-buttons-list">
                            <div class="switch-buttons">
                                <button type="button" class="product-next" id="switch-left"><<</button>
                                <button type="button" class="product-next" id="switch-right">>></button>
                            </div>
                            <button type="button" id="product-buy">BUY</button>
                        </section>
                    </div>
                </div>
            </main>
            <%@include file="jspfs/ads.jspf" %>
        </div>
        <%@include file="jspfs/footer.jspf" %>
    </body>
</html>