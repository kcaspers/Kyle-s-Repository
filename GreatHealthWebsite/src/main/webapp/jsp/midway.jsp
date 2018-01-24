<%-- 
    Document   : midway
    Created on : Jan 3, 2018, 7:06:46 PM
    Author     : kylecaaspers
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Locations - Midway</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="description" content="Great Health Nutrition Saint Paul Midway location.">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"> 
        <link href="../css/midway.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
    <body>

        <div class="container-fluid">
            <!--Insert header... this may not work great-->
            <%@include file="pageHeader.jsp" %>
            <div class="canBlur">

                <!--This should include both the big picture and description
                as well as the location info with the embedded google map-->

                <!--
                <div class="row" id="locationBanner">
                    <div class="col-md-12" >
                        <h1>Saint Paul - Midway</h1>
                    </div>
                </div>
                -->
                <div class="row">
                    <div id="picDiv" class="col-md-8">
                        <div class="row">
                            <h2 class="pixelMargin">
                                <b>Visit our original Saint Paul Location</b> 
                                <!--<hr>-->
                            </h2>
                            <div id="midwayCarousel" class="carousel slide" data-ride="carousel">
                                <ol class="carousel-indicators">
                                    <li data-target="#midwayCarousel" data-slide-to="0"></li>
                                    <li data-target="#midwayCarousel" data-slide-to="1"></li>
                                    <li data-target="#midwayCarousel" data-slide-to="2"></li>
                                </ol>
                                <div class="carousel-inner">
                                    <div class="item active">
                                        <img src="../images/storeCropped2.jpg" alt="Great Health Saint Paul"/>
                                    </div>
                                    <div class="item">
                                        <img src="../images/storeCropped1.jpg" alt="Great Health Saint Paul"/>
                                    </div>
                                    <div class="item">
                                        <img src="../images/storeCropped3.jpg" alt="Great Health Saint Paul"/>
                                    </div>
                                </div>
                            </div>
                            <!--<img id="midwayImage" class="img-responsive"
                                 src="../images/UK-medicines-regulator-slams-door-on-herbal-food-supplements.jpg" alt=""/>-->

                            <div class="col-md-6">
                                <!--<img class="productPhoto" src="../images/150425-F-XM588-001.jpg" alt=""/>-->
                            </div>
                            <div class="col-md-6">
                                <!--<img class="productPhoto" src="../images/170322-F-OT558-0001.jpg" alt=""/>-->
                            </div>
                        </div>
                    </div>
                    <div id="mapCol" class="col-md-4">
                        <div class="row">
                            <h2 class="pixelMargin">
                                Find us
                                <!--<hr>-->
                            </h2>

                            <div id="mapDiv">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-8">
                        <div id="locationInfoA">
                            <!--                            <h3>
                                                            Visit our original Saint Paul Location
                                                        </h3>-->
                            <h4>
                                About Great Health - Midway
                                <hr id="aboutRule">
                            </h4>

                            <p>Conveniently located across from the Green Line Hamline Avenue Station, our 
                                Saint Paul location is the oldest of our three stores, and is fully-stocked with the full
                                line of NOW products, as well as many other brands.</p>
                            <p>In addition to our regular product line, the Midway store also specializes in:</p>
                            <ul>
                                <li><b>detox products</b></li>
                                <li><b>powdered vitamins</b></li>
                                <li><b>men's health supplements</b></li>
                            </ul>
                            <p>
                                If you are unable to find what you are looking for, our friendly
                                sales staff will help you locate the product at one of our other locations. We are more than
                                eager to stock items that we do not currently carry and are committed to providing the best
                                possible shopping experience to our customers.
                            </p>
                        </div>

                    </div>

                    <div class="col-md-4">
                        <div id="locationInfoB">
                            <h4>
                                Address
                                <hr id="addressRule">
                            </h4>
                            <!--                                <b>Address</b>-->
                            <p>
                                Midway Marketplace <br>
                                1360 University Ave W <br>
                                St Paul, MN <br>
                                55104
                            </p>
                            <b>Phone:</b>
                            <p>
                                (651) 645-2315
                            </p>
                            <b>Store hours:</b>
                            <p>
                                Mon-Fri	10am- 8pm<br>
                                Saturday 10am-6pm <br>
                                Sunday 12pm-5pm <br>
                            </p>
                        </div>
                    </div>
                </div>
                <span id="mapRepos"></span>
                <div class="row">
                    <div class="col-sm-12 ol-xs-12">
                        <div id="learnMore">
                            <a href="./about.jsp">
                                <h3>
                                    Learn more about Great Health Nutrition
                                    <span class="fa fa-chevron-right"></span>
                                </h3>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            <%@include file="pageFooter.jsp"%>
        </div>
    </div>

    <!-- Placed at the end of the document so the pages load faster -->
    <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCknG2-6iBZT-rSHPaGIm4-A1-U-AeXZB0&callback=initMap">
    </script>
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="../js/midway.js" type="text/javascript"></script>
</body>
</html>
