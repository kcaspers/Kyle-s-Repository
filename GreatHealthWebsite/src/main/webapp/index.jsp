<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Great Health Nutrition</title>
        <meta name="description" content="Great Health Nutrition is the Twin Cities premium natural supplement retailer. Since opening
            our first Great Health location we have expanded to three locations, including Saint Paul, West Saint Paul, and Columbia Heights">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"> 
        <link href="css/index.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Courgette" rel="stylesheet">
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
                             src="images/PURABE8BTXE28KTR4W3QTSHU5VD36VKF.jpg" alt="Great Health Nutrition Logo"/>
                    </div>
                </div>
            </div>  
            <div class="row canBlur" id="locationRow">  
                <!--<div id="locations">-->
                <div id="locationOne" class="col-md-4">
                    <a href="${pageContext.request.contextPath}/jsp/midway.jsp">
                        <button class="locationTitle">
                            <span class="locationText">St Paul - Midway</span>
                        </button>
                    </a>
                    <div class="locationInfo">
                        1360 University Ave,<br>
                        Saint Paul<br>
                        <b> (651) 645-2315</b>
                    </div>
                </div>
                <div id="locationTwo" class="col-md-4">   
                    <a href="${pageContext.request.contextPath}/jsp/westsaintpaul.jsp">
                        <button class="locationTitle">
                            <span class="locationText">West Saint Paul</span>
                        </button>
                    </a>
                    <div class="locationInfo">
                        1668 S Robert St, <br>
                        West Saint Paul <br>
                        <b> (651) 453-9150</b>
                    </div>
                </div>
                <div id="locationThree" class="col-md-4">
                    <a href="${pageContext.request.contextPath}/jsp/columbia.jsp">
                        <button class="locationTitle">
                            <span class="locationText">Columbia Heights</span>
                        </button>
                    </a>
                    <div class="locationInfo">
                        5192 Central Ave NE, 
                        <br>Columbia Heights<br>
                        <b> (763) 571-5544</b>
                    </div>
                </div>
                <!--</div>-->
            </div>
            <div class="row">
                <div class="col-md-12" id="mapHours">
                    <div id="greenTop">
                        <!--this is for the map, the store hours and the logo-->
                        <h1 id="expertAdvice">Expert advice, low price!</h1>
                    </div>
                    <div id="mapDiv"></div>
                    <div class="row" id="greenBottom">
                        <h4>Store Hours</h4>
                        <div class="col-xs-4">
                            <p>
                                Monday-Friday: 10am- 8pm<br>
                            </p>
                        </div>
                        <div class="col-xs-4">
                            <p>
                                Saturday: 10am-6pm<br>
                            </p>
                        </div>
                        <div class="col-xs-4">
                            <p>
                                Sunday: 12pm-5pm
                            </p>
                        </div>

                    </div>
                </div>
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
        <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCknG2-6iBZT-rSHPaGIm4-A1-U-AeXZB0&callback=initMap">
        </script>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="js/index.js" type="text/javascript"></script>
    </body>
</html>

