<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Index Page</title>
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




        <!--        <div class="col-md-6" id="main-post-left">
                    Pending posts.
                     
                    <form id="publishPostForm" method="GET" action="publishPost">
                    <table id="pendingPostsTable" class="table table-hover">
                        <tr>
                            <th>Post Id</th>
                            <th>Post Title</th>
                            <th>Author</th>
                            <th>Expiration Date</th>
                            <th>Publish button</th>
                            <th>Reject button</th>
        
                        </tr>
                        
        <c:forEach var="currentPost" items="${pendingPosts}">
            <tr>
                <td>
                    <input type="hidden" name="postId" value="${currentPost.blogPostId}"/>
            <c:out value="${currentPost.blogPostId}"/>
        </td>
        <td>
            <c:out value="${currentPost.title}"/>
        </td>
        <td>
            <c:out value="${currentPost.user.userName}"/>
        </td>
        <td>
            set exp date
            <input type="date" id ="expDate" name="expDate${currentPost.blogPostId}"/>
        </td>
        <td>
            call a postController method which publishes this post and brings us back to this page
            <a href="publishPost?postId=${currentPost.blogPostId}&">
                Publish
            </a>
            <input class="btn btn-default"  value="publish" type="submit"/>
        </td>
        <td>
            <a class="btn btn-default" href="rejectPost?postId=${currentPost.blogPostId}">
                Reject Post
            </a>
        </td>

    </tr>
    
        </c:forEach>
           
    </table>
    </form>    
</div>-->
        <div class="col-md-8" id="main-post-left">
            <div class="row pendingRow">
                <span class="col-md-2">
                    <strong>Post Id</strong>
                </span>
                <span class="col-md-2">
                    <strong>Post Title</strong>
                </span>
                <span class="col-md-2">
                    <strong>Author</strong>
                </span>
                <span class="col-md-2">
                    <strong>Expiration Date</strong>
                </span>
                <span class="col-md-2">

                </span>
                <span class="col-md-2">

                </span>
                <hr>
            </div>
            <c:forEach var="currentPost" items="${pendingPosts}">
                <div class="row pendingRow">
                    <form class="publishPostForm" id="${currentPost.blogPostId}" method="GET" action="publishPost">
                          <span class="col-md-2">
                          <input type="hidden" name="postId" value="${currentPost.blogPostId}"/>
                        <c:out value="${currentPost.blogPostId}"/>
                        </span>
                        <span class="col-md-2">
                            <a href="${pageContext.request.contextPath}/displayPostDetails/${currentPost.blogPostId}">
                                <span style="color: blue">
                                <c:out value="${currentPost.title}"/>
                                </span>
                            </a>
                        </span>
                        <span class="col-md-2">
                            <c:out value="${currentPost.user.userName}"/>
                        </span>
                        <span class="col-md-2">
                            <input type="date" id="expDate${currentPost.blogPostId}" name="expDate"/>
                        </span>
                        <span class="col-md-2">
<!--                            <a class="btn btn-default" href="publishPost?postId=${currentPost.blogPostId}&">
                                Publish
                            </a>-->
                            <input class="btn btn-default"  value="publish" type="submit"/>
                        </span>
                        <span class="col-md-2">
                            <a class="btn btn-default" href="rejectPost?postId=${currentPost.blogPostId}">
                                Reject Post
                            </a>     
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