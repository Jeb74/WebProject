<%@page import="it.css.blackmarket.database.entities.DBUser"%>
<%@page import="it.css.blackmarket.database.entities.DBTransaction"%>
<%@page import="it.css.blackmarket.database.Factory"%>
<%@taglib prefix="std" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>

<std:if test="${sessionScope.get('username') == null}">
    <std:redirect url="login.jsp"/>
</std:if>

<%
    session.setAttribute("index", 4);
    
    Factory factory = Factory.getInstance();
    String username = (String)session.getAttribute("username");
    
    DBUser user = factory.user.getByUsername(username);
    DBTransaction stats = factory.transaction.getStatisticsFor(username);
            
    session.setAttribute("user_photo", user.getPhoto());
    session.setAttribute("user_email", user.getEmail());
    session.setAttribute("user_items", (Integer)stats.get("items"));
    session.setAttribute("user_sales", (Integer)stats.get("sales"));
    session.setAttribute("user_purchases", (Integer)stats.get("purchases"));
%>

<!DOCTYPE html>
<html lang="en">
    <%@include file="jspfs/head.jspf" %>
    <body>
        <%@include file="jspfs/header.jspf" %>
        <%@include file="jspfs/navbar.jspf" %>
        <div class="clearfix">
            <main class="col-9">
                <div class="content common-text" id="main-content">
                    <div class="about common-theme-single">
                        <div class="about-image">
                            <img class="smoke-border" src="${user_photo}" alt="Your profile photo">
                        </div>
                        <section>
                            <h2>Welcome, ${username}</h2>
                            <div class="align-left">
                                <p>E-Mail: ${user_email}</p>
                                <p>Items added: ${user_items}</p>
                                <p>Items bought: ${user_purchases}</p>
                                <p>Items sold: ${user_sales}</p>
                            </div>
                            <button type="button" id="logout">LOGOUT</button>
                        </section>
                    </div>
                </div>
            </main>
            <%@include file="jspfs/ads.jspf" %>
        </div>
        <%@include file="jspfs/footer.jspf" %>
    </body>
</html>