<%-- 
    Document   : columbia
    Created on : Jan 3, 2018, 7:06:56 PM
    Author     : kylecaaspers
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Great Health Nutrition Columbia Heights location.">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="../css/columbia.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
        <title>Locations - Columbia Heights</title>
    </head>
    <body>
        <div class="container-fluid">
            <%@include file="pageHeader.jsp"%>
            <div class="canBlur">
                <div class="row">
                    <div id="picDiv" class="col-md-8">
                        <div class="row">
                            <h2 class="pixelMargin">
                                <b>Visit our Columbia Heights store</b>
                            </h2>
                            <div id="columbiaCarousel" class="carousel slide" data-ride="carousel">
                                <ol class="carousel-indicators">
                                    <li data-target="#columbiaCarousel" data-slide-to="0"></li>
                                    <li data-target="#columbiaCarousel" data-slide-to="1"></li>
                                    <li data-target="#columbiaCarousel" data-slide-to="2"></li>
                                </ol>
                                <div class="carousel-inner">
                                    <div class="item active">
                                        <img src="../images/columbiaheights1cropped.jpg" alt="Great Health Nutrition Columbia Heights 1"/>
                                    </div>
                                    <div class="item">
                                        <img src="../images/columbiaheights2cropped.jpg" alt="Great Health Nutrition Columbia Heights 2"/>
                                    </div>
                                    <div class="item">
                                        <img src="../images/columbiaheights3cropped.jpg" alt="Great Health Nutrition Columbia Heights 3"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="mapCol" class="col-md-4">
                        <div class="row">
                            <h2 class="pixelMargin">
                                Find us
                            </h2>
                            <div id="mapDiv"></div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-8">
                        <div id="locationInfoA">
                            <h4>
                                About Great Health - Columbia Heights
                                <hr id="aboutRule">
                            </h4>
                            <p>
                                Our Columbia Heights store is located on Central Avenue just south of Interstate 694 and
                                next to Alqurashi Travel Agency. Slightly larger than the Saint Paul store, you will find 
                                a similar product line with an added emphasis on targeted nutrition supplements as well as
                                an extensive detox line to help you meet your weight loss and health goals.
<!--                                As with all of our stores, our Columbia Heights location is open from 10-8 on weekdays,
                                from 10-6 Saturday, and 12-5 on Sunday.-->
                            </p>
                            <p>
                                We pride ourself on serving our customers and the Columbia Heights community as a whole. 
                                Our owner, Joe Reiners, is frequently in the store and is more than eager to assist you with
                                all your supplement needs and has more than 30 years experience.
<!--                                we accept all major credit cards-->
                                
                            </p>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div id="locationInfoB">
                            <h4>
                                Address
                                <hr id="addressRule">
                            </h4>
                            <p>
                                5192 Central Ave NE <br>
                                Columbia Heights, MN
                                55421
                            </p>
                            <b>Phone:</b>
                            <p>
                                (763) 571-5544
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
                    <div class="col-sm-12 col-xs-12">
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
        <!-- Placed at the end of the document so the pages load faster -->
        <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCknG2-6iBZT-rSHPaGIm4-A1-U-AeXZB0&callback=initMap">
        </script>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="../js/bootstrap.min.js" type="text/javascript"></script>
        <script src="../js/columbia.js" type="text/javascript"></script>
    </body>
</html>
