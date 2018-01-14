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
        <link href="../css/ourmission.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
        <title>Our Mission</title>
    </head>
    <body>
        <div class="container-fluid">
            <%@include file="pageHeader.jsp" %>
            <div class="row canBlur">
                <div class="row">
                    <div id="photoWithText">
                        <img id="bigPhoto" class="img-responsive"
                             <img src="../images/multivitamin_cropped copy.jpg" alt=""/>

                        <div id="overlayText"><b>Local, Natural, Trusted</b></div>
                    </div>
                </div>
                <div class="row" id="ourMission">
                    <div class="col-md-12">
                        <!--<img id="missionBackground" class="img-circle img-responsive" src="../images/Abstract-Green-Nature-Background.jpg" alt=""/>-->
                        <div id="missionText">
                            <h1>Our Mission</h1>
                            <p class="centeredContent">
                                We are great we are cool we sell the pills that make so so smart
                                and so beautiful. <br>
                                We are great we are cool we sell the pills that make so so smart
                                and so beautiful. 
                            </p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row" style="margin-bottom: 30px">
                    <div class="col-md-12" id="testimonials">
                        <h1>Customer Testimonials</h1>
                        <!-- this could be a carousel -->
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


        </div>


        <%@include file="pageFooter.jsp" %>
    </div>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="../js/ourmission.js" type="text/javascript"></script>
</body>
</html>
