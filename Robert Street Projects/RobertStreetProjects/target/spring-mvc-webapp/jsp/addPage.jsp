<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Add Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
        <link href="${pageContext.request.contextPath}/css/Home.css" rel="stylesheet">
        <script src='https://cloud.tinymce.com/stable/tinymce.min.js'></script>
        <script>
            tinymce.init({
                selector: '#addPostArea',
                plugins: 'advlist autolink link image lists charmap print preview',
                a_plugin_option: true,
                a_configuration_option: 400,
                height : "200px"
            });
        </script>
    </head>
    <body>

        <div class="container-fluid navbar" >
            <div id="header">
            </div>
        </div>



        <!-- we will need to use the tinyMCE to make new posts-->
        <div class="container" id="margin">
            <h2>New Page</h2>

            <div class="col-md-8">
                <!--how do we send the userID?-->
                <form id="newPostForm" method="POST"  action="${pageContext.request.contextPath}/addPage">
                    <input type="text" name="newPageTitle" value="New Page Title">
                    <textarea id = "addPostArea" name='newPage'>
                    </textarea>
                    <input type="submit" placeholder="Add Post"/>
                </form>
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
