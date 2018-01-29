<%-- 
    Document   : westsaintpaul
    Created on : Jan 3, 2018, 7:07:06 PM
    Author     : kylecaaspers
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Great Health Nutrition West Saint Paul location.">
        <link href="../css/westsaintpaul.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
        <title>Locations - West Saint Paul</title>
    </head>
    <body>
        <div class="container-fluid">
            <%@include file="pageHeader.jsp"%>
            <div class="canBlur">
                <div class="row">
                    <div id="picDiv" class="col-md-8">
                        <div class="row">
                            <h2 class="pixelMargin">
                                <b>Visit our West Saint Paul store</b>
                            </h2>
                            <div id="wsaintpaulCarousel" class="carousel slide" data-ride="carousel">
                                <ol class="carousel-indicators">
                                    <li data-target="#wsaintpaulCarousel" data-slide-to="0"></li>
                                    <li data-target="#wsaintpaulCarousel" data-slide-to="1"></li>
                                    <li data-target="#wsaintpaulCarousel" data-slide-to="2"></li>
                                </ol>
                                <div class="carousel-inner">
                                    <div class="item active">
                                        <img  alt=""/>
                                    </div>
                                    <div class="item">
                                        <img  alt=""/>
                                    </div>
                                    <div class="item">
                                        <img  alt=""/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="mapCol" class="col-ms-4">
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
                                About Great Health - West Saint Paul
                                <hr id="aboutRule">
                            </h4>
                            <p>
                                Our West Saint Paul location is your one-stop shop for vitamins, herbs, sports supplements and herbal teas. 
                                Come in today for expert advice and a superior selection of the highest quality products. 
                                Visitors have noted our excellent selection of both topical and aromatic essential oils, as well as 
                                children's vitamins. We spared no effort sourcing only the finest holistic health products for you
                                and your family.
                            </p>
                            <p>
                                We are located just a short drive from downtown Saint Paul and easily accessible from Interstate 494 or 
                                Highway 52. Visitors to our West Saint Paul store will find plenty of parking as well as other retail and 
                                food options nearby. Make sure to check our hours before planning your visit.
                                We look forward to helping you in all of your health needs!
                                

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
                                1668 South Robert St <br>
                                West Saint Paul, MN
                                55118
                            </p>
                            <b>Phone:</b>
                            <p>
                                (651) 453-9150
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
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="../js/bootstrap.min.js" type="text/javascript"></script>
        <script src="../js/westsaintpaul.js" type="text/javascript"></script>
        <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCknG2-6iBZT-rSHPaGIm4-A1-U-AeXZB0&callback=initMap">
        </script>
    </body>
</html>
