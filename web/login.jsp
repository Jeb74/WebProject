<%@taglib prefix="std" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>

<%
    request.getSession().setAttribute("index", 4);
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
                    <section>
                        <h2>Sign in</h2>
                        <form action="login" method="post">
                            <label for="email">E-Mail:</label>
                            <span id="wordcount_email"></span>
                            <span id="error_email"></span>
                            <input type="text" name="email" id="email" required>
                            
                            <label for="password">Password:</label>
                            <span id="wordcount_password"></span>
                            <span id="error_password"></span>
                            <input type="password" name="password" id="password" required>
                            
                            <input type="submit" id="submit" value="SIGN IN">
                        </form>
                        <p class="align-center">
                            <a href="register.jsp">Register an account</a>
                        </p>
                    </section>
                </div>                    
            </main>
            <%@include file="jspfs/ads.jspf" %>
        </div>
        <%@include file="jspfs/footer.jspf" %>
    </body>
</html>