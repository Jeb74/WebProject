<%@taglib prefix="std" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<%
    request.getSession().setAttribute("index", 0);
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
                        <h2>Who are we?</h2>
                        <p>Albion Black Market is a web project with the goal of trading items and currencies in the game <a href="https://en.wikipedia.org/wiki/Albion_Online">Albion Online</a>. </p>
                        <p>Our staff is composed of regular players; and because of this, we know how much it is important to maintain the anonymity; this is why every transaction is completely in-game-anonymous from start to end.</p>
                    </article>
                    <article>
                        <h2>How to buy and sell?</h2>
                        <p> First of all, register an account, if you haven't already, on the <a href="personal_area.jsp">Personal Area</a> page. <br> If you already own an account the information will be inherited from your currently active session - that is, if you have any active session. </p>
                        <p>The options you have:</p>
                        <ul>
                            <li>To see the items available for purchase, head over to <a href="products.jsp">Products</a>.</li>
                            <li>Provide the required informations of the item you want to sell in the <a href="selling.jsp">Selling Space</a>.</li>
                        </ul>
                    </article>
                    <article>
                        <h2>Volumes</h2>
                        <p>Below, <b>some</b> of the sell volumes of the website: </p>
                        <table>
                            <thead>
                                <tr>
                                    <th>Item name:</th>
                                    <th>Average price:</th>
                                    <th>Volumes (last month):</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>Elders Carving Sword - 8.3</td>
                                    <td>18 EUR</td>
                                    <td>31</td>
                                </tr>
                                <tr>
                                    <td>GrandMasters Mistcaller - 7.3</td>
                                    <td>8 EUR</td>
                                    <td>39</td>
                                </tr>
                                <tr>
                                    <td>GrandMasters Malevolent Locus - 7.1</td>
                                    <td>1.50 EUR</td>
                                    <td>42</td>
                                </tr>
                                <tr>
                                    <td>Frost Ram</td>
                                    <td>1 EUR</td>
                                    <td>234</td>
                                </tr>
                                <tr>
                                    <td>Mammouth calf</td>
                                    <td>175 EUR</td>
                                    <td>8</td>
                                </tr>
                            </tbody>
                        </table>
                    </article>
                    <article>
                        <h2>Our supporters:</h2>
                        <p>We thank each and every person that has supported this project and it's users with their generous deeds!</p>
                        <ul>
                            <li>stefenix - donated over <b>7000</b> pieces of <a href="https://wiki.albiononline.com/wiki/Marble">Marble</a>!</li>
                            <li>Blyent - donated his <a href="https://wiki.albiononline.com/wiki/Black_Panther">Black Panther</a> when the project first started!</li>
                            <li>zBuddha - ganked the entire caravan of <a href="https://wiki.albiononline.com/wiki/Elite_Wild_Boar">Elite Wild Boar</a> transportes in Red Zone - known enemies of the project.</li>
                            <li>Xice - flooded the market with sell-orders of <b>7.1</b> <a href="https://wiki.albiononline.com/wiki/Uncommon_Meteorite_Steel_Bar">Meteorite Steel Bars</a>, for a total of <b>23000</b>!</li>
                            <li>Ermes - scouted numerous <a href="https://wiki.albiononline.com/wiki/Avalonian_Dungeons">Avalonian Dungeons</a> - no wonder that he's known as the "Best spotter of Bridgewatch"</li>
                            <li>Morris - gave away <b>3 months</b> of <a href="https://wiki.albiononline.com/wiki/Premium">premium</a> to <b>50</b> users of the website!</li>
                            <li>Jeb - donated lots of <a href="https://wiki.albiononline.com/wiki/Static_Dungeons">Static Dungeon</a> builds to new players, worth over <b>20</b> million silver!</li>
                        </ul>
                    </article>
                    <img src="./assets/black-market-banner.jpg" alt="Black Market banner">
                </div>
            </main>
            <%@include file="jspfs/ads.jspf" %>
        </div>
        <%@include file="jspfs/footer.jspf" %>
    </body>
</html>