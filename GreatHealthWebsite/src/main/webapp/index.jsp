<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Great Health Nutrition</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"> 
        <link href="css/index.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <!--        <div class="container">
                    <h1>Spring MVC Application from Archetype</h1>
                    <hr/>
                    <div class="navbar">
                        <ul class="nav nav-tabs">
                                <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
                                <li role="presentation"><a href="${pageContext.request.contextPath}/hello/sayhi">Hello Controller</a></li>
                        </ul>    
                    </div>
                    <h2>Home Page</h2>
                    <img src="images/honeybee.png" alt=""/>
                </div>-->
        <div class="container-fluid">
            <div class="row">  
                <div id="nav">
                    Navigation
                </div>
                <div id="header">
                    <div id="storeLogo">
                        <img src="images/honeybeeMain.png" alt=""/>
                    </div>
                </div>
            </div>  
            <div class="row">  
                <!--<div id="locations">-->
                <div id="locationOne" class="col-md-4">
                    <span class="locationTitle">
                        <a href="${pageContext.request.contextPath}/jsp/midway.jsp">Location One</a>
                    </span>
                </div>
                <div id="locationTwo" class="col-md-4">
                    <span class="locationTitle">Location Two</span>
                </div>
                <div id="locationThree" class="col-md-4">
                    <span class="locationTitle">Location Three</span>
                </div>
                <!--</div>-->
            </div>
            <div class="row">
                <div id="footerInfo">
                    boilerplate code
                </div>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

