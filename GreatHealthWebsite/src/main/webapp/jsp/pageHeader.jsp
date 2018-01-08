<%-- 
    Document   : pageHeader
    Created on : Jan 3, 2018, 9:58:23 PM
    Author     : kylecaaspers
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"> 
<link href="${pageContext.request.contextPath}/css/pageHeader.css" rel="stylesheet" type="text/css"/>
<div id="headerRow" class="row">
    <div class="col-md-3">
        <div id="menulink">
            <!--it would be nice just to use a symbol here, especially on the home page-->
            <span id="menuText">Menu</span>
        </div>
        <div id="left-menu">
            <ul>
                <li><a href="#">Home</a></li>
                <li><a href="#">About</a></li>
                <li><a href="#">Midway</a></li>
                <li><a href="#">West Saint Paul</a></li>
                <li><a href="#">Columbia Heights</a></li>
                <li><a href="#">Contact</a></li>
            </ul>
        </div>
    </div>

    <div class="col-md-3 col-md-offset-6 canBlur">
        <a href="${pageContext.request.contextPath}/index.jsp">
            <!--<img src="../images/honeybeeSmall.png" alt=""/>-->
            <img id="logo" class="img-responsive" src="../images/ghlogo.png" alt=""/>
        </a>
    </div>
    
</div>
<hr id="headerRule">
<!-- Placed at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/pageHeader.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
