<%-- 
    Document   : drafts
    Created on : Nov 8, 2017, 9:52:41 AM
    Author     : kylecaaspers
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Drafts</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
        <link href="${pageContext.request.contextPath}/css/Home.css" rel="stylesheet">
    </head>
    <body>

        <div class="container-fluid navbar" >
            <div id="header">
            </div>
        </div>

        <div class="col-md-8" id="main-post-left">
            <div class="row">
                Viewing drafts for: <c:out value="${user.userName}"/>
            </div>
            <div class="row pendingRow">
                <span class="col-md-2">
                    <strong>Post Id</strong>
                </span>
                <span class="col-md-2">
                    <strong>Post Title</strong>
                </span>
                <span class="col-md-2">

                </span>
                <span class="col-md-2">

                </span>
                <hr>
            </div>
            <c:forEach var="currentPost" items="${userDrafts}">
                <div class="row pendingRow">
                    <form method="GET" action="submitDraft">
                        <span class="col-md-2">
                            <input type="hidden" name="postId" value="${currentPost.blogPostId}"/>
                            <c:out value="${currentPost.blogPostId}"/>
                        </span>
                        <span class="col-md-2">
                            <c:choose>
                                <c:when test="${currentPost.status.statusId == 2}">
                                    <span style="color: grey">
                                        <c:out value="${currentPost.title}"/>
                                        (pending)
                                    </span>
                                </c:when>
                                <c:otherwise>
                                    <a href="${pageContext.request.contextPath}/editPost/${currentPost.blogPostId}">
                                        <span style="color: blue">
                                            <c:out value="${currentPost.title}"/>
                                        </span>
                                    </a>
                                </c:otherwise>
                            </c:choose>
                        </span>
                        <span class="col-md-2">
                            <c:choose>
                                <c:when test="${currentPost.status.statusId == 2}">
                                    <span class="btn btn-default" style="color:grey">Submit</span>
                                </c:when>
                                <c:otherwise>
                                    <input class="btn btn-default"  value="Submit" type="submit"/>
                                </c:otherwise>
                            </c:choose>
                        </span>
                        <span class="col-md-2">
                            <c:choose>
                                <c:when test="${currentPost.status.statusId == 2}">
                                    <span class="btn btn-default" style="color:grey" >Delete</span>
                                </c:when>
                                <c:otherwise>
                                    <a class="btn btn-default" href="deletePost?postId=${currentPost.blogPostId}">
                                        Delete
                                    </a> 
                                </c:otherwise>
                            </c:choose>

                        </span>
                    </form>
                </div>
                <hr>
            </c:forEach>
        </div>




        <div id="footer">  
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/pendingPosts.js"></script>
        <script src="${pageContext.request.contextPath}/js/loadHeader.js"></script>
    </body>
</html>
