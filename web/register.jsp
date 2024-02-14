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
                        <h2>Sign up</h2>
                        <form action="register" method="post" enctype='multipart/form-data'>
                            <label for="email">E-Mail:</label>
                            <input type="text" name="email" id="email" required>
                            <label for="username">Username:</label>
                            <input type="text" name="username" required>
                            <label for="password">Password:</label>
                            <input type="password" name="password" id="password" required>
                            <label for="password_repetition">Repeat password:</label>
                            <input type="password" name="password_repetition" required>
                            <label for="photo">Profile picture:</label>
                            <input type="file" name="photo" id="photo">
                            <input type="submit" id="submit" value="SIGN UP">
                        </form>
                    </section>
                </div>                    
            </main>
            <%@include file="jspfs/ads.jspf" %>
        </div>
        <%@include file="jspfs/footer.jspf" %>
    </body>
</html>