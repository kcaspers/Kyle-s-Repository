<%-- 
    Document   : midway
    Created on : Jan 3, 2018, 7:06:46 PM
    Author     : kylecaaspers
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Midway</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"> 
        <link href="../css/midway.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container-fluid">
            <!--Insert header... this may not work great-->
            <%@include file="pageHeader.jsp" %>
            <div class="row">
                <!--This should include both the big picture and description
                as well as the location info with the embedded google map-->
                <div class="col-md-9">
                    <div class="row">
                        <h3>Midway - Saint Paul</h3>
                            <img id="midwayImage" class="img-responsive"
                                 src="../images/UK-medicines-regulator-slams-door-on-herbal-food-supplements.jpg" alt=""/>
                        <p>Text text text text text text text text text</p>
                        <div class="col-md-6">
                            Photo
                        </div>
                        <div class="col-md-6">
                            Photo
                        </div>
                    </div>
                </div>

                <div class="col-md-3">
                    Map and location info goes here.
                </div>
            </div>

        </div>
    </div>

    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>
