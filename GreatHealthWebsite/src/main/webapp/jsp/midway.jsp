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
    </head>
    <body>
        <div class="container-fluid">
            <!--Insert header-->
            <%@include file="pageHeader.jsp" %>
            <div class="row">
                <!--This should include both the big picture and description
                as well as the location info with the embedded google map-->
                <div class="col-md-9">
                    <!-- -->
                </div>
                <div class="col-md-3">
                    
                </div>
            </div>
        </div>

        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
