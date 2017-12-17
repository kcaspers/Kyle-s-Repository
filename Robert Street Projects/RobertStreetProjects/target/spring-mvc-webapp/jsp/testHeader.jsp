<%-- 
    Document   : testHeader
    Created on : Oct 30, 2017, 10:38:54 AM
    Author     : kylecaaspers
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"> 
        <link href="${pageContext.request.contextPath}/css/Home.css" rel="stylesheet">
    </head>
    <body>
        <div class="container-fluid navbar" >
            <div class="row">
                <div class="col-md-4 ">
                    <a href="${pageContext.request.contextPath}/">
                        <h1>ROBERT STREET PROJECTS</h1>
                    </a>
                </div>

                <div class="col-md-12">
                    <div class="col-md-2">
                        <a  href="${pageContext.request.contextPath}/">Home</a>
                    </div>
                    <div class="col-md-1">
                        <a class="dropdown-toggle" type="button" data-toggle="dropdown">About
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu" >
                            <li><a href="${pageContext.request.contextPath}/about">About</a></li>
                            <li><a href="#">Static Page</a></li>
                            <li><a href="#">Static Page</a></li>
                        </ul>    
                    </div>
                    <div class="col-md-2">

                        <span class="input-group-btn">
                            <button class="btn btn-danger" type="button">
                                <span class=" glyphicon glyphicon-search"></span>
                            </button>
                        </span>
                        <input type="text" id="searchBar" class="search-query form-control" placeholder="Search" />

                    </div>
                    <div class="col-md-2">
                        <a class="dropdown-toggle" type="button" data-toggle="dropdown">Add
                            <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="${pageContext.request.contextPath}/displayAddPostPage">Post</a></li>
                            <li><a href="${pageContext.request.contextPath}/displayAddPage">Page</a></li>
                        </ul>
                    </div>
                    <div class="col-md-2">
                        <a  href="${pageContext.request.contextPath}/displayPending">View Pending</a>
                    </div>
                    <div class="col-md-2">
                        <c:choose>
                            <c:when test="${pageContext.request.userPrincipal.name != null}">
                                <a href="<c:url value="/j_spring_security_logout" />"> Logout</a>
                            </c:when>
                            <c:otherwise>
                                <a href="${pageContext.request.contextPath}/login">Sign In</a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div> 
            </div>
        </div>

        <footer class="fixed-bottom">
            footer contents <p>&copy;Rithee Nhep</p>
        </footer>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>               
    </body>
</html>
