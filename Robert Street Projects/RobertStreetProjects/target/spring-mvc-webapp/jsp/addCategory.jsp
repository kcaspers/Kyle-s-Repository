<%-- 
    Document   : addCategory
    Created on : Nov 9, 2017, 9:20:12 AM
    Author     : kylecaaspers
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Add Category</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
        <link href="${pageContext.request.contextPath}/css/Home.css" rel="stylesheet">
    </head>
    <body>
        <div class="container-fluid navbar" >
            <div id="header">
            </div>
            <div class="col-md-8" id="main-post-left">
                <div class="row">


                    <div class="col-md-4">
                        <h2>Categories</h2>
                        <ul class="list-group">
                            <c:forEach var="currentCategory" items="${categories}">
                                <li class="list-group-item">
                                    <c:out value="${currentCategory.name}"/>
                                </li>
                            </c:forEach>
                        </ul>    
                        <div>
                            <button type="button" data-toggle="modal" data-target="#addCategoryModal">
                                Add Category
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="addCategoryModal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h2>Add Category</h2>
                    </div>
                    <form class="form-horizontal" action="addCategory" method="POST">
                        <div class="form-group">
                            <div class="col-md-12">
                                <label for="categoryName" class="control-label">Category Name</label>    
                                <input class="form-control" type="text" name="categoryName" id="categoryName"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" name="Add Category" value="Add Category"/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/pendingPosts.js"></script>
        <script src="${pageContext.request.contextPath}/js/loadHeader.js"></script>
    </body>
</html>
