<%-- 
    Document   : createUser
    Created on : Oct 27, 2017, 11:39:27 AM
    Author     : kylecaaspers
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create User</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
        <link href="${pageContext.request.contextPath}/css/Home.css" rel="stylesheet">
        <style>
            .textInput{
                width: 400px;
            }
        </style>
    </head>
    <body>
        <div class="container-fluid navbar" >
            <div id="header">
            </div>
        </div>
        <div class="container" id="margin">
            <h3>Create User</h3>
            <form class="form-horizontal" role="form" method="POST" action="${pageContext.request.contextPath}/createNewUser">
                <div class="form-group">
                    <label for="username" class="col-md-4 control-label">Username:</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control textInput" name="username" placeholder="Username"/>
                    </div>
                    <label for="password" class="col-md-4 control-label">Password:</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control textInput" name="password" placeholder="Password"/>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-8">
                            <input type="submit" class="btn btn-default" id="create-user-button" value="Create Account"/>
                        </div>
                    </div>
                </div>
            </form>
        </div>
          <div id="footer">  
            </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/loadHeader.js"></script>

    </body>

</html>