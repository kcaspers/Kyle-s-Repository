<%-- 
    Document   : postDetail
    Created on : Oct 27, 2017, 10:04:58 AM
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
        <title>Post Details</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
        <link href="${pageContext.request.contextPath}/css/Home.css" rel="stylesheet">
        <style>
            #postCategory{
                font-style: italic;
                color: grey;
            }

            #postTitle{
                font-weight: bold;
                font-size: 30px;
            }

            #tags{
                margin-top: 10px; 
            }

            .tag{
                color: blue;
            }
        </style>
    </head>
    <body>
        <div class="container-fluid navbar" >
            <div id="header">
            </div>
        </div>
    </div>
    <div class="col-md-5" id="main-post-left">
        <div id="postCategory">
            <c:out value="${fullPost.category.name}"/>
        </div>

        <div id="postTitle">
            <c:out value="${fullPost.title}"/>
        </div>

        <div class="postInfo">
            <span>
                Published on: 
            </span>
            <c:out value="${fullPost.publishDate}"/>
            by: 
            <strong>
                <c:out value="${fullPost.user.userName}"/>
            </strong>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <a class="btn-danger" href="${pageContext.request.contextPath}/editPost/${fullPost.blogPostId}">
                    Edit
                </a>
                    
            </sec:authorize>
        </div>
        <div>
            <c:out value="${fullPost.content}"  escapeXml="false"/>
        </div>
        <div id="tags">
            <!--tags-->
            <span >
                <c:forEach var="currentTag" items="${fullPost.tags}">
                    <a class="tag" href="${pageContext.request.contextPath}/search/${currentTag.tagName}">
                        <c:out value="${currentTag.tagName}"/>
                    </a>
                </c:forEach>   
            </span>
        </div>

    </div>
    <div class="col-md-5" id="main-post-right">
        <img src="<c:out value="${fullPost.imagePath}"/>" />
    </div>








    <div id="footer">  
    </div>
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/loadHeader.js"></script>

</body>
</html>
