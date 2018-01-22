<%-- 
    Document   : about
    Created on : Jan 3, 2018, 7:07:36 PM
    Author     : kylecaaspers
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="../css/about.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
        <title>About Great Health</title>
    </head>
    <body>
        <div class="container-fluid">
            <%@include file="pageHeader.jsp" %>
            <div class="canBlur">
                <div class="row">
                    <div id="photoWithText">
                        <!--<img id="bigPhoto" class="img-responsive" src="../images/5-Questions-to-Ask-Yourself-When-Building-a-Health-Plan.jpg" alt=""/>-->
                        <img id="bigPhoto"  src="../images/aboutPhotoCropped2000.jpg" alt=""/>
                        <div id="overlayText"><b>Experience your best self</b></div>
                    </div>
                </div>
                <div class="row marginTop10" id="aboutbanner">
                    <div class="col-md-12">
                        <!--about Great Health banner-->
                        <h1>About Great Health Nutrition</h1>
                    </div>

                    <div id="imageCol" class="col-md-6 col-sm-6">
                        <!-- put a keyed image here-->
                        <div id="aboutImageDiv">
                            <img class="img-responsive" id="aboutImage" src="../images/2013_04_25_14_35_34.jpg" alt=""/>
                            <!--                            <div id="aboutImageBlur"></div>-->
                        </div>
                    </div>
                    <div class="col-md-4 col-sm-4">
                        <!--<img id="greatHealthImage" class="img-responsive" src="../images/1484600631-asset.jpg" alt=""/>-->
                        <p class="leftAlignContent">
                            Since 1987, <b>Great Health Nutrition has been the Twin Cities premiere independent supplement
                                retailer</b>, specializing in holistic health, sports nutrition and natural detox products.
                            Repeat customers value us for our knowledge of health and wellness, our commitment to 
                            our community, and integrity as a small business.
                        </p>
                        <p class="leftAlignContent">
                            After opening our first location in Saint Paul over 30 years ago, <b>Great Health Nutrition now encompasses
                                three different locations</b>, including Saint Paul - Midway, Columbia Heights, and West Saint Paul.
                            We look forward to serving you in all of your natural health needs.
                        </p>

                    </div>
                </div>
                <div class="row" id="products">
                    <!--product line-->
                    <div class="col-md-12" id="productsHeader">
                        <h1>Products</h1>
                        <p class="justifyContent">
                            We proudly carry a diverse range of supplements and stock only the most reputable brands.
                            In addition to vitamins, minerals and high-performance sports supplements we also stock natural 
                            body care products including Dr. Bronner's Castile Soap, NOW brand body oils, and assorted natural skin treatments.
                        </p>
                        <p class="justifyContent">
                            <br>
                            Each of our stores are specially tailored to the needs of the clientele, many of whom are valued long-time customers.
                            Every location carries standard targeted nutrition products, a range of multi-vitamins, and both pre-and post-workout
                            athletic supplements to help you on your health and wellness journey.
                        </p>
                        <p class="justifyContent">
                            <br>
                            Don't see what you are looking for?<br>
                            We are regularly adding new items to our stores and welcome suggestions for products that you love.
                            Feel free to <a href='${pageContext.request.contextPath}/jsp/contact.jsp'>contact us</a> and find the Great Health location near you.
                        </p>
                    </div>



                </div>
                <div class="row">
                    <div class="col-md-12 marginTop5">
                        <h1>Featured Brands:</h1>
                    </div>
                    <!--Make some kind of table here with all the different product logos-->
                    <div class="col-md-12">
                        <!--                                <div class="carousel slide" id="productCarousel">
                                                            <div class="carousel-inner">
                                                                <div class="item active centeredContent">
                                                                    <div class="col-xs-4 centeredContent">
                                                                        <img class="productPhoto" id="sourceNaturals" src="../images/SourceN_logo_arch_WEB.jpg" alt=""/>
                                                                    </div>
                                                                </div>
                                                                <div class="item centeredContent">
                                                                    <div class="col-xs-4 centeredContent">
                                                                        <img class="productPhoto" src="../images/now-foods-logo.png" alt=""/>
                                                                    </div>
                                                                </div>
                                                                <div class="item centeredContent">
                                                                    <div class="col-xs-4 centeredContent">
                                                                        <img class="productPhoto" src="../images/naturesplus.jpg" alt=""/>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <a class="left carousel-control" href="#productCarousel" data-slide="prev"><i class="glyphicon glyphicon-chevron-left"></i></a>
                                                            <a class="right carousel-control" href="#productCarousel" data-slide="next"><i class="glyphicon glyphicon-chevron-right"></i></a>
                                                        </div>-->




                        <div class="col-sm-4 productDiv">
                            <div class="tile red">
                                <a  target="_blank">
                                    <img class="productPhoto" src="../images/now-foods-logo.png" alt=""/></a>
                            </div>
                        </div>
                        <div class="col-sm-4 productDiv">
                            <div class="tile orange">
                                <a  target="_blank">
                                    <img class="productPhoto" src="../images/naturesplus.jpg" alt="" /></a>
                            </div>
                        </div>
                        <div class="col-sm-4 productDiv">
                            <div class="tile purple">
                                <a  target="_blank">
                                    <img class="productPhoto" id="sourceNaturals" src="../images/SourceN_logo_arch_WEB.jpg" alt=""/></a>
                            </div>
                        </div>
                    </div>
                </div>
                <!--
                <div class="row" style="margin-bottom: 30px">
                    <h1>Customer Testimonials</h1>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="col-md-4">
                                <blockquote class="quote-box">
                                    <p class="quotation-mark">
                                        “
                                    </p>
                                    <p class="quote-text">
                                        Don't believe anything that you read on the internet, it may be fake. 
                                    </p>
                                    <hr>
                                    <div class="blog-post-actions">
                                        <p class="blog-post-bottom pull-left">
                                            Abraham Lincoln
                                        </p>

                                    </div>
                                </blockquote> 
                            </div>
                            <div class="col-md-4">
                                <blockquote class="quote-box">
                                    <p class="quotation-mark">
                                        “
                                    </p>
                                    <p class="quote-text">
                                        Don't believe anything that you read on the internet, it may be fake. 
                                    </p>
                                    <hr>
                                    <div class="blog-post-actions">
                                        <p class="blog-post-bottom pull-left">
                                            Abraham Lincoln
                                        </p>

                                    </div>
                                </blockquote> 
                            </div>
                            <div class="col-md-4">
                                <blockquote class="quote-box">
                                    <p class="quotation-mark">
                                        “
                                    </p>
                                    <p class="quote-text">
                                        Don't believe anything that you read on the internet, it may be fake. 
                                    </p>
                                    <hr>
                                    <div class="blog-post-actions">
                                        <p class="blog-post-bottom pull-left">
                                            Abraham Lincoln
                                        </p>

                                    </div>
                                </blockquote> 
                            </div>
                        </div>
                    </div>
                </div>
            </div>
                -->
                <div class="row marginTop5">
<!--                    <div class="col-md-12" id="greyBlock">-->
                        <!--want a grey block-->
                        <div class="greyBlockText col-md-12">
                            <h3>
                                Want to learn more about natural health?
                            </h3>
                            <p>
                                <i>Check out these popular resources:</i>
                            </p>
                            <div class="row">
                                <div class="col-md-3 col-xs-12 resourceLink">
                                    <h4>
                                        Food and Diet
                                    </h4>
                                    <a href="http://www.summertomato.com/">summertomato.com</a>
                                    <a href="https://ketogains.com/">ketogains.com</a>
                                    <a href="http://gutsybynature.com/">gutsybynature.com</a>
                                    <a href="https://www.100daysofrealfood.com/">100daysofrealfood.com</a>
                                </div>
                                <div class="col-md-3 col-xs-12 resourceLink">
                                    <h4>
                                        Herbs
                                    </h4>
                                    <a href="http://dogwoodbotanical.com/">dogwoodbotanical.com</a>
                                    <a href="https://www.drweil.com/blog/">drweil.com</a>
                                    <a href="http://fivetothriveplan.com/">fivetothriveplan.com</a>
                                    <a href="http://queenofgreen.org/">queenofgreen.org</a>
                                    <a href="http://www.herbgeek.com/">www.herbgeek.com/</a>
                                    <a href="http://queenofgreen.org/">queenofgreen.org</a>
                                </div>
                                <div class="col-md-3 col-xs-12 resourceLink">
                                    <h4>
                                        Fitness
                                    </h4>
                                    <a href="https://www.girlsgonestrong.com/">girlsgonestrong.com</a>
                                    <a href="https://bayesianbodybuilding.com/">bayesianbodybuilding.com</a>
                                    <a href="http://www.ontheregimen.com/">www.ontheregimen.com</a>
                                    <a href="https://bayesianbodybuilding.com/">bayesianbodybuilding.com</a>
                                    <a href="https://bayesianbodybuilding.com/">bayesianbodybuilding.com</a>
                                </div>
                                <div class="col-md-3 col-xs-12 resourceLink">
                                    <h4>
                                        Miscellaneous
                                    </h4>
                                    <a href="http://www.mdheal.org/">mdheal.org</a>
                                    <a href="http://naturallysavvy.com/">naturallysavvy.com</a>
                                    <a href="https://www.motherearthnews.com/">motherearthnews.com</a>
                                    <a href="http://revolutionaryact.com/">revolutionaryact.com</a>
                                    <a href="https://www.happy-mothering.com/">happy-mothering.com</a>
                                    <a href="https://lifespa.com/">lifespa.com</a>
                                </div>
                            </div>

                        </div>
<!--                    </div>-->
                </div>
            </div>
        </div>

        <%@include file="pageFooter.jsp"%>

        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="../js/jquery.slides.js" type="text/javascript"></script>
        <script src="../js/about.js" type="text/javascript"></script>
    </body>
</html>
