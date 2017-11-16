<%-- 
    Document   : addUserForm
    Created on : Nov 1, 2017, 1:34:30 PM
    Author     : Alejandro
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Add User</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>

        <div class="container-fluid">
            <div id="header">
            </div>
        </div>

        <div class="col-md-5" id="main-post-left">
            
            <h1>Add User Form</h1>

            <form method="POST" action="addUser">
                Username: <input type="text" 
                                 name="username"/><br/>
                Password: <input type="password" 
                                 name="password"/><br/>
                Admin User? <input type="checkbox" 
                                   name="isAdmin" value="yes"/> <br/>
                <input type="submit" value="Add User"/><br/>
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