<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Manage User</title>
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


        <div class="col-md-6" id="main-post-left">
            Manage users

            <table id="pendingPostsTable" class="table table-hover">
                <tr>
                    <th>User ID</th>
                    <th>Username</th>
                    <th>Enable/Disable</th>


                </tr>
                <c:forEach var="currentUser" items="${allUsers}">
                    <tr>
                        <td>
                            <c:out value="${currentUser.userId}"/>
                        </td>
                        <td>
                            <c:out value="${currentUser.userName}"/>
                        </td>
                        <td>
                            <form class="form-horizontal" role="form" method="POST" action="updateStatus">
                                <c:choose>
                                    <c:when test="${currentUser.enable==true}">
                                        <input id="userDisabled" name="enabledDisabled" type="radio" value="0" checked/>
                                        <input id="userEnabled" name="enabledDisabled" type="radio" value="1"/>
                                    </c:when>
                                    <c:otherwise>
                                        <input id="userDisabled" name="enabledDisabled" type="radio" value="0"/>
                                        <input id="userEnabled" name="enabledDisabled" type="radio" value="1" checked/>
                                    </c:otherwise>


                                </c:choose>

                                <input type="hidden" value="${currentUser.userId}" name="userId">
                                <input type="submit" class="btn btn-default" id="updateStatus" value="Update"/>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>

            <div class="col-md-6">
                <a class="btn btn-default" href="${pageContext.request.contextPath}/displayUserForm">
                    Add User
                </a>
            </div>
        </div>


        <div id="footer">  
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/loadHeader.js"></script>

    </body>
</html>
