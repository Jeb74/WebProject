<%@page import="java.util.ArrayList"%>
<%@page import="it.css.blackmarket.utils.LinkData"%>
<%@taglib prefix="std" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>

<%
    request.setAttribute("index", -1);
%>

<!DOCTYPE html>
<html lang="en">
    <%@include file="jspfs/head.jspf" %>
    <body>
        <%@include file="jspfs/header.jspf" %>
        <%@include file="jspfs/navbar.jspf" %>
        <div class="clearfix">
            <main class="col-9">
                <div class="content common-text common-theme" id="main-content">
                    <article>
                        <h2>An error has occurred</h2>
                        <p>${message}</p>
                        <p class="align-center">
                            <std:forEach items="${links}" var="link">
                            <a href="${link.getReference()}">${link.getIdentifier()}</a><br><br>
                            </std:forEach>
                        </p>
                    </article>
                    <img src="./assets/black-market-banner.jpg" alt="Black Market banner">
                </div>
            </main>
            <%@include file="jspfs/ads.jspf" %>
        </div>
        <%@include file="jspfs/footer.jspf" %>
    </body>
</html>