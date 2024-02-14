<%@taglib prefix="std" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<%
    request.getSession().setAttribute("index", 3);
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
                        <h2>About us</h2>
                        <p>We are two guys, 21 and 22 years old, who met by chance two years ago, thanks to a common friend. Since then, a great collaboration has developed, both personally and academically, as we embarked on a long series of projects, and what you have in your hands is one of them.</p>
                    </section>
                    <ul class="zero-padding">
                        <li class="about banner common-theme-single">
                            <div class="about-image">
                                <img class="smoke-border" alt="Marco La Civita picture" src="./assets/propics/marco-la-civita.jpg" alt="Marco La Civita">
                            </div>
                            <section>
                                <h2>Marco La Civita</h2>
                                <p>
                                    <i>Founder & Developer of the Albion Black Market project.</i>
                                </p>
                                <p> Never lose hope, be persistent and stubborn and never give up. There are many instances in history where apparent losers suddenly turn out to be winners unexpectedly, so you should never conclude all hope is lost. </p>
                            </section>
                        </li>
                        <li class="about banner common-theme-single">
                            <div class="about-image">
                                <img class="smoke-border" alt="Antonio Masala picture" src="./assets/propics/antonio-masala.jpg" alt="Antonio Masala">
                            </div>
                            <section>
                                <h2>Antonio Masala</h2>
                                <p>
                                    <i>Developer & Escrow of the Albion Black Market project.</i>
                                </p>
                                <p> There is nothing wrong with violence in itself. In any particular case, whether violence is good or bad depends on how it is used and the purpose for which it is used. </p>
                            </section>
                        </li>
                    </ul>
                </div>
            </main>
            <%@include file="jspfs/ads.jspf" %>
        </div>
        <%@include file="jspfs/footer.jspf" %>
    </body>
</html>