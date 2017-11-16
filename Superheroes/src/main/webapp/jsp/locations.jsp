<%-- 
    Document   : locations
    Created on : Oct 24, 2017, 3:05:36 PM
    Author     : kylecaaspers
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
            #locationDetailsMain{
                visibility: hidden;
            }

            .type{
                float: left;
            }

            .value{
                float: right;
            }

            #locationList {
                /*position: absolute;
                top: 20px;
                bottom: 20px;
                left: 20px;
                right: 20px;*/
                height: 200px;
                overflow: scroll;
                margin: 0;
                padding: 0;

                border: 2px solid #ccc;
            }
            #edit-Location-ID{
                display:none;
            }

            li {
                padding: 10px 20px;
                border-bottom: 1px solid #ccc;
            }
            #selectList:hover{
                background-color: lightgoldenrodyellow;
            }
            .subHeader{
                text-align: center;
            }
            #errorMessage{
                display:none;
            }
        </style>
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

            <div id="errorMessage" class="alert alert-danger">
                
            </div>

            <div class="row">
                <div class="col-md-5">
                    <h4 class="subHeader">Locations</h4>
                    <!--put location scrolling list here-->
                    <div>
                        <ul id="locationList">
                            <!--<li>Location</li>
                            <li>Location</li>
                            <li>Location</li>
                            <li>Location</li>
                            <li>Location</li>
                            <li>Location</li>-->
                        </ul>
                    </div>
                    <button type="button" id="addLocationBtn"
                            data-toggle="modal" data-target="#addLocationModal">
                        Add Location
                    </button>
                </div>

                <div class="modal fade" id="addLocationModal">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h2>Add Location</h2>
                            </div>
                            <form class="form-horizontal" role="form">
                                <div class="form-group">
                                    <label for="add-location-name" class="col-md-4 control-label">Location Name</label>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" id="add-location-name"
                                               name="locationName" placeholder="Location Name"/>
                                    </div>
                                </div>
                            </form>
                            <form class="form-horizontal" role="form">
                                <div class="form-group">
                                    <label for="add-location-description" class="col-md-4 control-label">Location description</label>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" id="add-location-description" 
                                               name="locationDescription" placeholder="Location Description"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="add-location-address" class="col-md-4 control-label">Location Address</label>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" id="add-location-address" 
                                               name="locationAddress" placeholder="Location Address"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="add-location-latitude" class="col-md-4 control-label">Location Latitude</label>
                                    <div class="col-md-8">
                                        <input type="number" class="form-control" id="add-location-latitude" 
                                               step=".01" name="locationLatitude" placeholder="Location Latitude"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="add-location-longitude" class="col-md-4 control-label">Location Longitude</label>
                                    <div class="col-md-8">
                                        <input type="number" class="form-control" id="add-location-longitude" 
                                               step=".01" name="locationLongitude" placeholder="Location Longitude"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-md-offset-4 col-md-8">
                                        <button id="addLocation">Add Location</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>



                <div class="col-md-7">
                    <h4 class="subHeader">Location Details</h4>
                    <div id="locationDetailsMain">
                        <table class="table table-hover">
                            <tr>
                                <th style="width: 30%">
                                    
                                </th>
                                <th>
                                    
                                </th>
                            </tr>
                            <tr>
                                <td>
                                    Location ID
                                </td>
                                <td id="locationID">

                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Location Name
                                </td>
                                <td id="locationName">

                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Location Description
                                </td>
                                <td id="locationDescription">

                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Address
                                </td>
                                <td id="locationAddress">

                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Latitude/Longitude
                                </td>
                                <td id="latLong">

                                </td>
                            </tr>
                        </table>
                        <div>
                            <button type="button" id="editLocationBtn" onclick="supplyEditData()"
                                    data-toggle="modal" data-target="#editLocationModal">
                                Edit Location
                            </button>
                            <button type="button" id="deleteLocationBtn"">
                                Delete Location
                            </button>
                        </div>

                    </div>
                </div>

                <div class="modal fade" id="editLocationModal">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h2>Edit Location</h2>
                            </div>
                            <input type="text" id="edit-Location-ID"/>
                            <form class="form-horizontal" role="form">
                                <div class="form-group">
                                    <label for="edit-location-name" class="col-md-4 control-label">Location Name</label>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" id="edit-location-name"
                                               name="newLocationName" placeholder="Location Name"/>
                                    </div>
                                </div>
                            </form>
                            <form class="form-horizontal" role="form">
                                <div class="form-group">
                                    <label for="edit-location-description" class="col-md-4 control-label">Location description</label>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" id="edit-location-description" 
                                               name="newLocationDescription" placeholder="Location Description"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="edit-location-address" class="col-md-4 control-label">Location Address</label>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" id="edit-location-address" 
                                               name="newLocationAddress" placeholder="Location Address"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="edit-location-latitude" class="col-md-4 control-label">Location Latitude</label>
                                    <div class="col-md-8">
                                        <input type="number" class="form-control" id="edit-location-latitude" 
                                                step=".01" name="newLocationLatitude" placeholder="Location Latitude"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="edit-location-longitude" class="col-md-4 control-label">Location Longitude</label>
                                    <div class="col-md-8">
                                        <input type="number" class="form-control" id="edit-location-longitude" 
                                               step=".01" name="newLocationLongitude" placeholder="Location Longitude"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-md-offset-4 col-md-8">
                                        <button onclick="editLocation()">Edit Location</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>  
        <script src="${pageContext.request.contextPath}/js/location.js"
        type="text/javascript"></script>
    </body>
</html>
