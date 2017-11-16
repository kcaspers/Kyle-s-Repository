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
            #navDrop{
                margin-top: 5px;
                margin-bottom: 5px;
            }
            #mainSidebar{
                background-color: lightgoldenrodyellow;
                padding: 20px;
            }
            #latestSightings{

            }

            #map {
                height: 400px;
                width: 100%;
            }

            html, body {
                height: 100%;
                margin: 0;
                padding: 0;
            }
            li {
                padding: 10px 20px;
                border-bottom: 1px solid #ccc;
            }
        </style>
        <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAiF-zgYgsxT14UB53W444tYr1yA2OLYUE&callback=initMap">
        </script>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/home.js"
        type="text/javascript"></script>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-3">
                    <h4>Superhero Sightings</h4>
                </div>

                <!-- nav drop down -->
                <div class="col-md-1 col-md-offset-5">
                    <h4>
                        View
                    </h4>
                </div>
                <div class="col-md-3">
                    <div class="dropdown" id="navDrop">
                        <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">
                            select page
                            <span class="caret"></span></button>
                        <ul class="dropdown-menu">
                            <!--These links don't need to point to controller methods if I am using jQuery-->
                            <!--Each page will have a slightly different dropdown because we wont 
                            need to nav to the page we already are on-->
                            <li><a href="${pageContext.request.contextPath}/jsp/home.jsp">Home</a></li>
                            <li><a href="${pageContext.request.contextPath}/jsp/locations.jsp">Locations</a></li>
                            <li><a href="${pageContext.request.contextPath}/jsp/organizations.jsp">Organizations</a></li>
                            <li><a href="${pageContext.request.contextPath}/jsp/sightings.jsp">Sightings</a></li>
                            <li><a href="${pageContext.request.contextPath}/jsp/superheroes.jsp">Superheroes</a></li>
                        </ul>
                    </div>
                </div>

                <hr/>
            </div>
            <hr>
            <div class="row">
                <!-- this section is going to contain the latest sightings and \
                a sidebar for an about section-->
                <div class="col-md-8">
                    <div id="latestSightings">
                        <h4>
                            Latest Sightings:
                        </h4>
                        <!-- -->

                        <table class="table table-hover">
                            <thead>
                            <th>
                                Date
                            </th>
                            <th>
                                Location
                            </th>
                            </thead>
                            <tbody id="latestSightingsList">

                            </tbody>

                        </table>
                    </div>


                </div>
                <div class="col-md-4">
                    <div id="mainSidebar">
                        <h4>
                            About Superhero Sighting
                        </h4>
                        <p>
                            This superhero and superhero sighting application is brought
                            to you by Hero Education and Relationship Organization (HERO)
                            as well as all of our contributors. We are proud to be the
                            most comprehensive superhero and supervillan resource on the
                            web and welcome additions to our database.
                        </p>
                    </div>
                </div>
                <div id="map"></div>

            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->



    </body>
</html>

