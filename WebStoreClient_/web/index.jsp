<%-- 
    Document   : index
    Created on : 12.12.2017, 15:23:47
    Author     : Вячеслав
--%>

<%@page import="web.java.controllers.Types"%>
<%@page import="web.java.controllers.Manufs"%>
<%@page import="web.java.controllers.Products"%>
<%@page import="web.service.Product"%>
<%@page import="web.service.Manufacturer"%>
<%@page import="web.java.connector.Service"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>WebStore</title>
        <link rel="stylesheet" type="text/css" href="main.css"/>
        <script src="jquery.min.js"></script>
        <script src="main.js"></script>
    </head>
    <body>
        <!--                                                HEAD                                            -->        
        <nav class="left-menu">
            <h2 class="left-menu-logo" id="left-menu-logo">WebStore</h2>
            <br/><br/><br/>

            <form id="left-menu-form" method="post">

                <ul class="left-menu-nav" id="left-menu-nav">

                    <li>Товары</li>

                    <li id="left-menu-types">

                        <ul>
                            <%
                                     for (String t : Types.get()) {
                            %>
                            <li>
                                <input type="submit" class="full" name="type" value="<%= t%>">
                            </li>
                            <%
                                     }
                            %>
                        </ul> 

                    </li>

                    <li>Контакты</li>

                    <li>О нас</li>

                </ul>

            </form>

        </nav>
        <!--                                                HEAD                                            --> 

        <!--                                                MAIN                                            --> 
        <section class="main-conteiner">

            <form id="manufs-form">

                <div class="main-conteiner-manufs" id="manufs">
                    <%
                             for (Manufacturer m : Manufs.get()) {
                    %>
                        <div class="main-conteiner-manuf">
                            <div class="image" style="background: url(<%= m.getLogo()%>) no-repeat center"></div>
                            <h3><%= m.getNameM()%></h3>
                        </div>
                    <%
                             }
                    %>

                </div>   

            </form>

            <div class="main-conteiner-products"  id="products">

                <%
                         Products.sortBy("type", request.getParameter("type"));
                         for (Product p : Products.get()) {
                %>

                <div class="main-conteiner-product">

                    <div class="image" style="background: url(<%= p.getImage()%>) no-repeat center"></div>

                    <h3 class="f-l"><%= p.getType()%></h3>
                    <h4 class="f-r"><%= p.getManuf()%></h4>

                    <% if (p.getDiscont() > 0) {%>
                        <h5 class="f-n allert"><%= p.getCost()%>$    ( -<%= p.getDiscont()%>% )</h5>
                    <% } else {%>
                        <h5 class="f-n allert"><%= p.getCost()%>$</h5>
                    <% } %>  
                    
                </div>

                <%
                         }
                %>

            </div>

        </section>
        <!--                                                MAIN                                            --> 

        <!--                                               PRODUCT                                          
        <div class="conteiner-product disabled" id="conteiner-product">

            <h:panelGroup class="product" id="product" layout="block">

                <div class="image" style="background: url() no-repeat center"/>

                <div class="product-description conteiner-sm">

                    <h2 class="exit" onclick="show('#conteiner-product')">X</h2>
                    <h2></h2>
                    <h4 class="f-l"></h4>
                    <h4 class="f-r"></h4>
                    <br class="f-n"/>

                    <h:form id="product-form">
                        <p>
                            <span></span>
                        </p>

                        <div class="flex center">
                            <h4>Размер</h4>
                            <h:inputText id="size"/>

                            <h4>Количество</h4>
                            <h:inputText id="amount"/>
                        </div>

                        <br/>
                        <h:commandLink class="btn-black">

                            В корзину
                            <f:ajax render=":basket" event="click" onevent="show('#conteiner-product')" 
                                    listener=""/>

                        </h:commandLink>

                    </h:form>
                </div>

            </h:panelGroup>
        </div>
                                                       PRODUCT                                          -->
</body>
</html>
