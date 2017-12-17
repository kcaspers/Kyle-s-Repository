<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Static page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
        <link href="${pageContext.request.contextPath}/css/Home.css" rel="stylesheet">
    </head>
    <body>
        <div class="container-fluid" >
            <div id="header">
            </div>
        </div>

        <div class="col-md-8" id="main-post-left">
            <div id="staticPageContent">
                <strong>
                    <h1><c:out value="${pageToDisplay.name}"/>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <a href="deletePage?staticPageId=${pageToDisplay.staticPageId}" style="font-size: 14px; color: blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Delete]
                            </a>
                        </sec:authorize>
                    </h1>
                </strong>
                <c:out value="${pageToDisplay.content}" escapeXml="false"/>
                
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


