<%-- 
    Document   : sightings
    Created on : Oct 24, 2017, 3:06:02 PM
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

            #sightingDetailsMain{
                visibility: hidden;
            }

            #sightingList {
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

            #edit-sighting-ID{
                /*display:none;*/
            }

            #supersPresentTable {
                height: 200px;
                overflow: scroll;
                margin: 0;
                padding: 0;
                border: 2px solid #ccc;
            }

            #editSupersPresentTable{
                height: 200px;
                overflow: scroll;
                margin: 0;
                padding: 0;
                border: 2px solid #ccc;
                margin-left: 50px;
            }

            #sightingID{
                display:none;
            }

            #supersAddedTable {
                height: 200px;
                width: 80%;;
                overflow: scroll;
                margin: 0px;
                padding: 0;
                border: 2px solid #ccc;
                display: block;
                margin-left: auto;
                margin-left: 50px;
            }

            #space{
                height: 200px;
            }

            li {
                padding: 10px 20px;
                border-bottom: 1px solid #ccc;

            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-3">
                    <h4>Superhero Sightings</h4>
                </div>

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
            </div>
            <hr>
            <div class="row">
                <div class="col-md-6">
                    <h4>Sightings</h4>
                    <div>
                        <ul id="sightingList">

                        </ul>
                    </div>
                    <button type="button" id="addSighting" onclick="supplyEditData()"
                            data-toggle="modal" data-target="#addSightingModal">
                        Add sighting
                    </button>
                </div>
                <div class="modal fade" id="addSightingModal">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h2>Add Sighting</h2>
                            </div>
                            <form class="form-horizontal" role="form">
                                <div class="form-group">
                                    <label for="add-sighting-date" class="col-md-4 control-label">Date</label>
                                    <div class="col-md-8">
                                        <input type="date" class="form-control" id="add-sighting-date"
                                               name="newSightingDate">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="addLocationList" class="col-md-4 control-label">Sighting Location</label>
                                    <div class="col-md-8">
                                        <select name="addLocationList" id="addLocationList">
                                            <!--the script will fill this -->
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-4 control-label">Heroes Present</label>
                                    <div class="col-md-8">
                                        <select name="addHeroesList" id="addHeroesList">

                                        </select>
                                        <span class="btn btn-default" onclick="selectSuper(addHeroesList.value)">Add Super</span>
                                    </div>
                                </div>
                                <div class="form-group" id="superAddGroup">
                                    <!--Display supers we have added -->
                                    <ul id="supersAddedTable" class="col-md-8">
                                        <li><b>Super People Present: (Click to remove)</b></li>
                                        <div id="supersAddedList">

                                        </div>
                                    </ul>
                                </div>
                                <div class="form-group">
                                    <div class="col-md-offset-4 col-md-8">
                                        <button onclick="addSighting()">Add Sighting</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

                <div id="sightingDetailsMain">
                    <span id="sightingID"></span>
                    <div class="col-md-3">
                        <h4 id="locationName">Location Name</h4>
                        <div>
                            <ul id="supersPresentTable">
                                <li><b>Super People Present:</b></li>
                                <div id="supersPresentList">

                                </div>
                            </ul>
                        </div>
                        <button type="button" id="editSightingBtn" onclick="supplyEditData()"
                                data-toggle="modal" data-target="#editSightingModal">
                            Edit Sighting
                        </button>
                        <button type="button" id="deleteSightingBtn" onclick="deleteSighting()">
                            Delete Sighting
                        </button>
                    </div>
                    <div class="col-md-3">
                        <h4 id="sightingDate">Sighting Date</h4>
                        <div id="space">

                        </div>
                    </div>
                    <div class="col-md-12">


                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="editSightingModal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h2>Edit Sighting</h2>
                    </div>
                    <input hidden id="edit-sighting-ID"/>
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="edit-sighting-date" class="col-md-4 control-label">Date</label>
                            <div class="col-md-8">
                                <input type="date" class="form-control" id="edit-sighting-date"
                                       name="newSightingDate">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="editLocationList" class="col-md-4 control-label">Sighting Location</label>
                            <div class="col-md-8">
                                <select name="editLocationList" id="editLocationList">
                                    <!--the script will fill this -->
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label">Heroes Present</label>
                            <div class="col-md-8">
                                <select name="editHeroesList" id="editHeroesList">

                                </select>
                                <span class="btn btn-default" onclick="selectEditSuper(editHeroesList.value)">Add Super</span>
                            </div>
                        </div>
                        <div class="form-group" id="superAddGroup">
                            <!--Display supers we have added -->
                            <ul id="editSupersPresentTable" class="col-md-8">
                                <b>Super People Present: (Click to remove)</b>
                                <div id="editSupersPresentList">
                                    <!--I should have a delete option on each super-->
                                </div>
                            </ul>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <button onclick="editSighting()">Edit Sighting</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

    </div>

    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>  
    <script src="${pageContext.request.contextPath}/js/sightings.js"
    type="text/javascript"></script>
</body>
</html>
