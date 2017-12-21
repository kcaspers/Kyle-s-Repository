<%-- 
    Document   : superheroes
    Created on : Oct 24, 2017, 3:06:15 PM
    Author     : kylecaaspers
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/superheroesStyles.css" rel="stylesheet">
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
            <div id="errorMessage" class="alert alert-danger"></div>
            <div class="row">
                <div class="col-md-6">
                    <h4 class="subHeader">Superheroes</h4>
                    <div>
                        <ul id="superheroList">
                            <!--<li>Location</li>
                            <li>Location</li>
                            <li>Location</li>
                            <li>Location</li>
                            <li>Location</li>
                            <li>Location</li>-->
                        </ul>
                    </div>
                    <button type="button" id="addSuperheroBtn"
                            data-toggle="modal" data-target="#addSuperheroModal">
                        Add Superhero
                    </button>
                </div>
                <div id="superheroDetails">
                    <span id="superheroID"></span>
                    <h4 class="subHeader">Superhero Details</h4>
                    <div class="row">
                        <div class="col-md-3">
                            <div>
                                <h4>Superhero ID</h4>
                                <span id="superheroInfoID"></span>
                            </div>
                            <div>
                                <h4>Superhero Name</h4>
                                <span id="superheroName"></span>
                            </div>
                            <div>
                                <h4>Super Power</h4>
                                <span id="superPower"></span>
                            </div>
                            <ul id="superheroOrganizations">

                            </ul>
                        </div>
                        <div class="col-md-3">
                            <div>
                                <h4>Description</h4>
                                <p id="superheroDescription">
                                    Text text text Text text text Text text text
                                    Text text text Text text text Text text text
                                </p>
                                <ul id="superheroSightings">

                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-3 col-md-offset-6">
                            <button type="button" id="editSuperheroBtn" onclick="editSuperhero()"
                                    data-toggle="modal" data-target="#editSuperheroModal">
                                Edit Superhero
                            </button>
                            <button type="button" id="deleteSuperheroBtn" onclick="deleteSuperhero()">
                                Delete Superhero
                            </button>

                        </div>    
                    </div>
                </div>

                <div class="modal fade" id="addSuperheroModal">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h2>Add Superhero</h2>
                            </div>
                            <form class="form-horizontal" role="form">
                                <div class="form-group">
                                    <!--name-->
                                    <label for="addSuperheroName" class="col-md-4 control-label">Superhero Name</label>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" id="addSuperheroName"
                                               name="newSuperheroName"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <!--power-->
                                    <label for="newSuperheroPower" class="col-md-4 control-label">Superhero Power</label>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" id="addSuperheroPower"
                                               name="newSuperheroPower"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <!--description-->
                                    <label for="addSuperheroDescription" class="col-md-4 control-label">Description</label>
                                    <div class="col-md-8">
                                        <textarea id="addSuperheroDescription" class="form-control">
                                            
                                        </textarea>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <!--orgs-->
                                    <label for="superOrganizationList" class="col-md-4 control-label">Organizations</label>
                                    <div class="col-md-8">
                                        <select name="superOrganizationList" id="addOrganization"
                                                class="form-control superOrganizationList">
                                            <!--the script will fill this -->
                                        </select>
                                        <span class="btn btn-default" onclick="selectOrganization(addOrganization.value)">
                                            Add Organization
                                        </span>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <!--selected orgs list-->
                                    <ul class="col-md-8 scrollList">
                                        <b>Belongs to: (Click to remove)</b>
                                        <div id="addSuperOrganizations">

                                        </div>
                                    </ul>
                                </div>

                                <div class="form-group">
                                    <!--name-->
                                    <label for="superSightingList" class="col-md-4 control-label">Sightings</label>
                                    <div class="col-md-8">
                                        <select name="superSightingList" id="addSightings"
                                                class="form-control superSightingList">
                                            <!--the script will fill this -->
                                        </select>
                                        <span class="btn btn-default" onclick="selectSighting(addSightings.value)">
                                            Add Sighting
                                        </span>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <!--selected orgs list-->
                                    <ul class="col-md-8 scrollList">
                                        <b>Seen at: (Click to remove)</b>
                                        <div id="addSuperSightings">

                                        </div>
                                    </ul>
                                </div>
                                <div class="form-group">
                                    <div class="col-md-offset-4 col-md-8">
                                        <button onclick="addSuper()">Add Superhero</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

                <div class="modal fade" id="editSuperheroModal">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h2>Edit Superhero</h2>
                            </div>
                            <form class="form-horizontal" role="form">
                                <div class="form-group">
                                    <!--name-->
                                    <label for="editSuperheroName" class="col-md-4 control-label">Superhero Name</label>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" id="editSuperheroName"
                                               name="editSuperheroName"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <!--power-->
                                    <label for="editSuperheroPower" class="col-md-4 control-label">Superhero Power</label>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" id="editSuperheroPower"
                                               name="editSuperheroPower"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <!--description-->
                                    <label for="editSuperheroDescription" class="col-md-4 control-label">Description</label>
                                    <div class="col-md-8">
                                        <textarea id="editSuperheroDescription" class="form-control">
                                            
                                        </textarea>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <!--orgs-->
                                    <label for="superOrganizationList" class="col-md-4 control-label">Organizations</label>
                                    <div class="col-md-8">
                                        <select name="superOrganizationList" id="editOrganization"
                                                class="form-control superOrganizationList">
                                            <!--the script will fill this -->
                                        </select>
                                        <span class="btn btn-default" onclick="selectEditOrganization(editOrganization.value)">
                                            Add Organization
                                        </span>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <!--selected orgs list-->
                                    <ul class="col-md-8 scrollList">
                                        <b>Belongs to: (Click to remove)</b>
                                        <div id="editSuperOrganizations">

                                        </div>
                                    </ul>
                                </div>

                                <div class="form-group">
                                    <!--name-->
                                    <label for="superSightingList" class="col-md-4 control-label">Sightings</label>
                                    <div class="col-md-8">
                                        <select name="superSightingList" id="editsightings"
                                                class="form-control superSightingList">
                                            <!--the script will fill this -->
                                        </select>
                                        <span class="btn btn-default" onclick="selectEditSighting(editsightings.value)">
                                            Add Sighting
                                        </span>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <!--selected orgs list-->
                                    <ul class="col-md-8 scrollList">
                                        <b>Seen at: (Click to remove)</b>
                                        <div id="editSuperSightings">

                                        </div>
                                    </ul>
                                </div>
                                <div class="form-group">
                                    <div class="col-md-offset-4 col-md-8">
                                        <button onclick="editSuper()">Edit Superhero</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

                <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
                <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>  
                <script src="${pageContext.request.contextPath}/js/superheroes.js"></script>
                </body>
                </html>
