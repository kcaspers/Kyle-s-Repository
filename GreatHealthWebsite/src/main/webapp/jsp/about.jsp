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
        <link href="../css/about.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
        <title>About Great Health Nutrition</title>
    </head>
    <body>
        <div class="container-fluid">
            <%@include file="pageHeader.jsp" %>
            <div class="row canBlur">
                <div class="row">
                    <div id="photoWithText">
                        <!--<img id="bigPhoto" class="img-responsive" src="../images/5-Questions-to-Ask-Yourself-When-Building-a-Health-Plan.jpg" alt=""/>-->
                        <img id="bigPhoto" class="img-responsive" src="../images/aboutPhotoCropped.jpg" alt=""/>
                        <div id="overlayText"><b>Experience your best self</b></div>
                    </div>
                </div>
                <div class="row" id="aboutbanner">
                    <div class="col-md-12">
                        <!--about Great Health banner-->
                        <h1>About Great Health Nutrition</h1>
                    </div>

                    <div class="col-md-6 col-sm-6">
                        <!-- put a keyed image here-->
                        <div>
                            <img class="img-responsive" id="aboutImage" src="../images/2013_04_25_14_35_34.jpg" alt=""/>
                            <div id="aboutImageBlur"></div>
                        </div>
                    </div>
                    <div class="col-md-4 col-sm-4">
                        <!--<img id="greatHealthImage" class="img-responsive" src="../images/1484600631-asset.jpg" alt=""/>-->
                        <p class="leftAlignContent">
                            Since 1987, Great Health Nutrition has been the Twin Cities premiere independent supplement
                            retailer, specializing in holistic health, sports nutrition and detox products.<br>
                            Since 1987, Great Health Nutrition has been the Twin Cities premiere independent supplement
                            retailer, specializing in holistic health, sports nutrition and detox products.<br>
                            Since 1987, Great Health Nutrition has been the Twin Cities premiere independent supplement
                            retailer, specializing in holistic health, sports nutrition and detox products.
                        </p>
                    </div>
                </div>
                <div class="row" id="products">
                    <!--product line-->
                    <div class="col-md-12" id="productsHeader">
                        <h1>Products</h1>
                        <p class="centeredContent">
                            We proudly carry a diverse range of supplements stock only the most reputable brands. 
                        </p>
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
                                <a href="https://www.nowfoods.com/" target="_blank">
                                    <img class="productPhoto" src="../images/now-foods-logo.png" alt=""/></a>
                            </div>
                        </div>
                        <div class="col-sm-4 productDiv">
                            <div class="tile orange">
                                <a href="https://naturesplus.com/" target="_blank">
                                    <img class="productPhoto" src="../images/naturesplus.jpg" alt="" /></a>
                            </div>
                        </div>
                        <div class="col-sm-4 productDiv">
                            <div class="tile purple">
                                <a href="http://www.sourcenaturals.com/" target="_blank">
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
            </div>
            <%@include file="pageFooter.jsp"%>
        </div>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="../js/jquery.slides.js" type="text/javascript"></script>
        <script src="../js/about.js" type="text/javascript"></script>
    </body>
</html>
