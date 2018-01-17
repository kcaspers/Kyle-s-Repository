<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Great Health Nutrition</title>
        <!-- Bootstrap core CSS -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"> 
        <link href="css/index.css" rel="stylesheet" type="text/css"/>
        <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
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
            <%@include file="jsp/pageHeader.jsp" %>
            <!--            <div class="row">
                            <div class="col-md-3" id="menu">
                                <div id="menulink">Menu</div>
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
                        </div>-->
            <div class="row canBlur">  
                <div id="header">
                    <div >
                        <!--<img id="storeLogo" class="img-responsive" src="images/ghlogo.png" alt=""/>-->
                        <img id="storeLogo" class="img-responsive"
                             src="images/PURABE8BTXE28KTR4W3QTSHU5VD36VKF.jpg" alt=""/>
                    </div>
                </div>
            </div>  
            <div class="row canBlur" id="locationRow">  
                <!--<div id="locations">-->
                <a href="${pageContext.request.contextPath}/jsp/midway.jsp">
                    <div id="locationOne" class="col-md-4">
                        <button class="locationTitle">
                            <span class="locationText">St Paul - Midway</span>
                        </button>
                    </div>
                </a>
                <a href="${pageContext.request.contextPath}/jsp/westsaintpaul.jsp">
                    <div id="locationTwo" class="col-md-4">
                        <button class="locationTitle">
                            <span class="locationText">West St Paul</span>
                        </button>
                    </div>
                </a>
                <a href="${pageContext.request.contextPath}/jsp/columbia.jsp">
                    <div id="locationThree" class="col-md-4">
                        <button class="locationTitle">
                            <span class="locationText">Columbia Heights</span>
                        </button>
                    </div>
                </a>
                <!--</div>-->
            </div>
            <!--        
            <div class="row canBlur" id="lowerRow">
                <a href="${pageContext.request.contextPath}/jsp/contact.jsp">
                    <div id="locationOne" class="col-md-4">
                        <button class="locationTitle">
                            <span class="locationText">Contact</span>
                        </button>
                    </div>
                </a>
                <a href="${pageContext.request.contextPath}/jsp/about.jsp">
                    <div id="locationOne" class="col-md-4">
                        <button class="locationTitle">
                            <span class="locationText">About</span>
                        </button>
                    </div>
                </a>
            </div>
            -->
            <%@include file="jsp/pageFooter.jsp"%>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="js/index.js" type="text/javascript"></script>
    </body>
</html>

