<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Home</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
        <link href="${pageContext.request.contextPath}/css/Home.css" rel="stylesheet">
        <style>

            #postTitle{
                font-weight: bold;
                font-size: 30px;
            }
        </style>
    </head>
    <body>
        <div class="container-fluid">
            <div id="header">
            </div>
        </div>
        <div class="col-md-5" id="main-post-left">
            <c:forEach var="currentPost" items="${postList}">
                <div class="post">
                    <div class="postCategory">
                        <c:out value="${currentPost.category.name}"/>
                    </div>
                    <a href="${pageContext.request.contextPath}/displayPostDetails/${currentPost.blogPostId}">    
                        <div id="postTitle">
                            <c:out value="${currentPost.title}"/>
                        </div>
                    </a>
                    <div class="postInfo">
                        <span>
                            Published on: 
                        </span>
                        <c:out value="${currentPost.publishDate}"/>
                        by: 
                        <strong>
                            <c:out value="${currentPost.user.userName}"/>
                        </strong>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <a class="btn-danger" href="${pageContext.request.contextPath}/editPost/${currentPost.blogPostId}">
                                Edit
                            </a>
                        </sec:authorize>
                    </div>
                    <div>
                        <c:out value="${currentPost.content}" escapeXml="false"/>
                        <!--We need some way of displaying the content as formatted HTML-->
                    </div>
                    <!-- no tags displayed on main page -->
                </div>
                <hr>
            </c:forEach>
        </div>
        <div class="col-md-5" id="main-post-right">
            <img src="http://www.smithsmagazine.co.uk/wp-content/uploads/2013/09/Sarah-Morris-Bye-Bye-Brazil-A4-5.jpg"/>
            <!--            <div class="col-md-5">
                            <img src="${pageContext.request.contextPath}/picture/art1.png"/>
                        </div>   
                        <div class="col-md-5" >
                            <img src="${pageContext.request.contextPath}/picture/art3.png"/>
                        </div>
                        <div>
                            <img src="${pageContext.request.contextPath}/picture/art4.png"/>
                        </div>-->
        </div>
        <div id="footer">  
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/search.js"></script>
        <script src="${pageContext.request.contextPath}/js/loadHeader.js"></script>
    </body>
</html>

