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

        <title>About</title>
    </head>
    <body>
        <div class="container-fluid">
            <%@include file="pageHeader.jsp" %>
            <div class="row canBlur">
                <div class="row" id="aboutbanner">
                    <div class="col-md-12">
                        <!--about Great Health banner-->
                        <h1>About Great Health Nutrition</h1>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <img id="greatHealthImage" class="img-responsive" src="../images/1484600631-asset.jpg" alt=""/>
                        <p class="centeredContent">
                            Since 1987, Great Health Nutrition has been the Twin Cities premiere independent supplement
                            retailer, specializing in holistic health, sports nutrition and detox products.<br>
                            Since 1987, Great Health Nutrition has been the Twin Cities premiere independent supplement
                            retailer, specializing in holistic health, sports nutrition and detox products.<br>
                            Since 1987, Great Health Nutrition has been the Twin Cities premiere independent supplement
                            retailer, specializing in holistic health, sports nutrition and detox products.
                        </p>
                    </div>
                </div>
                <div class="row">
                    <!--product line-->
                    <div class="col-md-12">
                        <h3>Products</h3>
                        <p class="centeredContent">
                            We proudly carry a diverse range of supplements and are the primary retailer of NOW brand products 
                        </p>
                        <!--Make some kind of table here with all the different product logos-->
                        <div class="row">
                            <div class="col-md-4 productSlot">
                                <img class="productPhoto" src="../images/SourceN_logo_arch_WEB.jpg" alt=""/>
                            </div>
                            <div class="col-md-4 productSlot">
                                <img class="productPhoto" src="../images/now-foods-logo.png" alt=""/>
                            </div>
                            <div class="col-md-4 productSlot">
                                
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-4 productSlot">
                                col
                            </div>
                            <div class="col-md-4 productSlot">
                                col
                            </div>
                            <div class="col-md-4 productSlot">
                                col
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <!--Customer testimonials-->
                    <div class="col-md-12">
                        <h3>Customer Testimonials</h3>
                    </div>
                </div>
            </div>
            <%@include file="pageFooter.jsp"%>
        </div>

        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    </body>
</html>
