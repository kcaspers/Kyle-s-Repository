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
            
        </div>


        <%@include file="pageFooter.jsp" %>
        </div>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="../js/ourmission.js" type="text/javascript"></script>
    </body>
</html>
