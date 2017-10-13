<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Index Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <style>
            #amp{
                border: 1px solid black;
                padding: 20px;
            }

        </style>
    </head>
    <body>
        <div class="container">
            <h1>Speaker Impedance Tool</h1>
            <hr/>
            <div class="row" style="height:250px">
                <div class="col-md-6" > 
                    <!-- section for amp -->
                    <h2>Head</h2>
                    <div id="amp">
                        <div id="speakerOut1">
                            Speaker Out 1
                            <!-- give this button a draggable cord thing-->
                            <!-- give this button a draggable cord thing-->
                            <button></button>
                        </div>
                        <div id="speakerOut2">
                            Speaker Out 2
                            <button></button>
                        </div>
                        <div id="ohmSetting">
                            <form>
                               <input type="radio" name="ohm" value="4"> 4ohm<br>
                               <input type="radio" name="ohm" value="8"> 8ohm<br> 
                               <input type="radio" name="ohm" value="16"> 16ohm<br> 
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-md-6"> 
                    <!-- section for info -->
                    <h2>Info</h2>
                </div>
            </div>

            <div class="row" style="height:400px">
                <div class="col-md-12">
                    <!-- section for speaker cabs, should grow as we add cabs -->
                    <button>Add Speaker Cabinet</button>
                    <!-- adding a speaker cabinet will bring down a modal with a form-->
                    <div class="modal">
                        <!--This modal will contain the add speaker cab form -->
                    </div>
                </div>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

