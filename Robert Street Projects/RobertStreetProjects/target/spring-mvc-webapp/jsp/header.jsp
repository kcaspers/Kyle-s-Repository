<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <link href="https://fonts.googleapis.com/css?family=Righteous" rel="stylesheet">

    <style>
        #websiteTitle{
            font-family: 'Righteous';
            font-size: 40px;
        }

        #robertStreetName{
            margin-bottom: 10px;
        }

        #testDiv{
            background-color: pink;
        }
    </style> 
</head>



<div class="container-fluid">
    <div class="row">
        <div id="robertStreetName" class="col-md-4">
            <a href="${pageContext.request.contextPath}/">
                <h1 id="websiteTitle">ROBERT STREET PROJECTS</h1>
            </a>
        </div>  
    </div>
    <div class="row">           
        <div class="col-md-12 ">
            <div class="col-sm-2 align-top">
                <a  href="${pageContext.request.contextPath}/">Home</a>
            </div>
            <div class="col-sm-1" >
                <a class="dropdown-toggle" type="button" data-toggle="dropdown" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">About
                    <span class="caret"></span>
                </a>
                <ul class="dropdown-menu" id="staticPageList">
                    <li><a href="${pageContext.request.contextPath}/about">About</a></li>
                        <c:forEach var="currentPage" items="${staticPages}">
                        <!-- each option will be an li link -->
                        <li>
                            <a href="${pageContext.request.contextPath}/displayStaticPage/<c:out value='${currentPage.staticPageId}'/>">About</a>
                        </li>
                    </c:forEach>
                </ul>    
            </div>
            <div class="col-sm-2">
                <form class="navbar-form" id="searchForm" role="search" action="${pageContext.request.contextPath}/searchBar" method="get">
                    <div id="search" class="input-group add-on">
                        <input type="text" class="form-control" placeholder="Search" name="srch-term" id="srch-term">
                        <div class="input-group-btn">
                            <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="col-sm-2">
                <sec:authorize access="hasRole('ROLE_USER')">
                    <a class="dropdown-toggle" type="button" data-toggle="dropdown">Add
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/displayAddPostPage">Post</a></li>
                        <li><a href="${pageContext.request.contextPath}/displayAddPage">Page</a></li>
                        <li><a href="${pageContext.request.contextPath}/displayDrafts">View Drafts</a></li>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <li><a href="${pageContext.request.contextPath}/displayAddCategory">Category</a></li>
                        </sec:authorize>
                    </ul>
                </sec:authorize>
            </div>
            <div class="col-sm-2">
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <a class="dropdown-toggle" type="button" data-toggle="dropdown">Admin
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a  href="${pageContext.request.contextPath}/displayPending">View Pending</a></li>
                        <li><a href="${pageContext.request.contextPath}/displayManageUsers">Manage users</a></li>
                    </ul>
                </sec:authorize>
            </div>
            <div class="col-sm-2">
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
</div>
<script src="${pageContext.request.contextPath}/js/staticPageInitializer.js"></script>
