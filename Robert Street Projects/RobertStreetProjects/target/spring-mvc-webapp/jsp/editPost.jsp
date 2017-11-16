<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit Post</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
        <link href="${pageContext.request.contextPath}/css/Home.css" rel="stylesheet">
        <script src='https://cloud.tinymce.com/stable/tinymce.min.js?apiKey=svdull2tjjgeii0x3pwtldwsx8qw3clh9o824jungbgrge0w'></script>
        <script>
            tinymce.init({
                selector: '#editPostArea',
                plugins: 'advlist autolink link image lists charmap print preview',
                a_plugin_option: true,
                a_configuration_option: 100,
                height: "200px"
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
            <h2>Edit Post</h2>
            <div class="col-md-8">
                <form id="newPostForm" method="POST" action="${pageContext.request.contextPath}/editPost">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="postTitle">Post Title:</label>
                                <input id="postTitle" type="text" name="editPostTitle" value="${postTitle}">
                            </div>
                            <div class="form-group">
                                <input type="button" value="Add Photo" onclick="addPhoto()"/>
                                <input type="text" name="photo" id="addPostPhoto"/>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="newPostCategory">Post Category:</label>    
                                <select id="newPostCategory" name="newPostCategory">
                                    <option name="newPostCategory" value="0">Select Category</option>
                                    <!--                        <option name="newPostCategory" value="option1">Option 1</option>
                                                            <option name="newPostCategory" value="option1">Option 2</option>-->

                                    <c:forEach var="currentCategory" items="${categories}" >
                                        <option name="newPostCategory" 
                                                <c:if test="${currentCategory.categoryId == postCategory}">
                                                    selected
                                                </c:if>
                                                value="<c:out value='${currentCategory.categoryId}'/>">

                                            <c:out value="${currentCategory.name}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>  
                            <div class="form-group">
                                <input type="button" id="addTagbut" value="Add Tags" onclick="addTag()"/>
                                <input type="text" list="tagList" name="getTag" id="getTags"/>
                                <datalist id="tagList">
                                    <c:forEach var="currentTag" items='${tagList}' >
                                        <option name="newTags">
                                            <c:out value="${currentTag.tagName}"/>
                                        </option>        
                                    </c:forEach>
                                </datalist>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div id="mceInfo">
                            Note: including the tag '~break~' in your post will set the limit for content displayed on main page.
                        </div>

                        <textarea id = "editPostArea" name='newPost'>
                            <c:out value="${postContent}"/>
                        </textarea>
                        <input type="hidden" name="username" value="<security:authentication property='principal.username' />"/>
                        <input type="hidden" name="postId" value="${postID}"/>
                        <div class="col-md-12">
                            <textarea type="text" name="tags" class="tagArea"
                                  id="addTags" style=" height:25px;"> ${postTags} </textarea>
                        </div>
                        <div class="col-md-2 col-md-offset-10 text-right">
                            <input type="submit" class="postSubmit" placeholder="Edit Post"/> 
                        </div>
                        
                                  
                                 
                    </div>



                </form>
            </div>
            <div class="col-md-4">
                <img id="photoInput" src="${photoPath}"/>
            </div>
        </div>
        <div id="footer">  
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/jshome.js"></script>
        <script src="${pageContext.request.contextPath}/js/loadHeader.js"></script>
    </body>
</html>
