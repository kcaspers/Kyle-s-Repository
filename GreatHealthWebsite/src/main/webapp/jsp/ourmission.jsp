<%-- 
    Document   : ourmission
    Created on : Jan 12, 2018, 9:38:51 AM
    Author     : kylecaaspers
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="../css/ourmission.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
        <title>Our Mission</title>
    </head>
    <body>
        <div class="container-fluid">
            <%@include file="pageHeader.jsp" %>
            <div class="canBlur">
                <div class="row">
                    <div id="photoWithText">
                        <!--                        <img id="bigPhoto" class="img-responsive"
                                                     <img src="../images/multivitamin_cropped copy.jpg" alt=""/>-->
                        <img id="bigPhoto" class="img-responsive" src="../images/multivitamin_cropped 2000.jpg" alt=""/>
                        <div id="overlayText"><b>Local, Natural, Trusted</b></div>
                    </div>
                </div>
                <div class="row" id="ourMission">
                    <div class="col-md-12">
                        <!--<img id="missionBackground" class="img-circle img-responsive" src="../images/Abstract-Green-Nature-Background.jpg" alt=""/>-->
                        <div id="missionText">
                            <h1><b>Our Mission</b></h1>
                            <p class="centeredContent">
                                <!--                                We are great we are cool we sell the pills that make so so smart
                                                                and so beautiful. <br>
                                                                We are great we are cool we sell the pills that make so so smart
                                                                and so beautiful. -->
                                <i>Great Health Nutrition is committed to the highest degree of service to our customers
                                    and as a small, locally-owned business we understand the importance of creating strong
                                    connections in our community.
                                    We believe that a healthy community starts with healthy individuals, and we have the knowledge,
                                    experience and candor to make that a reality.

                                </i>
                            </p>
                        </div>
                    </div>
                </div>

                <div class="row" style="margin-bottom: 30px">
                    <div class="col-md-12" id="testimonials">
                        <h3>Customer Testimonials</h3>
                        <!-- this could be a carousel -->
                        <div id="testimonialCarousel" class="carousel slide"  data-ride="carousel">
                            <div class="carousel-inner">
                                <div class="item active">

                                    <div class="col-md-12 col-sm-12" id="quoteContainer">
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
                                <div class="item">
                                    <div class="col-md-12 col-sm-12" id="quoteContainer">
                                        <blockquote class="quote-box">
                                            <p class="quotation-mark">
                                                “
                                            </p>
                                            <p class="quote-text">
                                                Don't believe anything that you read on the internet, it may be true. 
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
                                <div class="item">
                                    <div class="col-md-12 col-sm-12" id="quoteContainer">
                                        <blockquote class="quote-box">
                                            <p class="quotation-mark">
                                                “
                                            </p>
                                            <p class="quote-text">
                                                Don't believe anything that you read on the internet, it may be true. 
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
                        <!--                        <div class="col-md-4 col-sm-12">
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
                                                <div class="col-md-4 col-sm-12">
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
                                                </div>-->
                    </div>
                </div>
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
<!--        </div>-->


        <%@include file="pageFooter.jsp" %>
    </div>
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="../js/ourmission.js" type="text/javascript"></script>
</body>
</html>
