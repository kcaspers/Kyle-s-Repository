<%-- 
    Document   : organizations
    Created on : Oct 24, 2017, 3:05:48 PM
    Author     : kylecaaspers
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"> 
        <link href="${pageContext.request.contextPath}/css/organizationsStyles.css" rel="stylesheet">
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
            <div id="errorMessage" class="alert alert-danger">

            </div>

            <div class="row">
                <div class="col-md-5">
                    <h4 class="subHeader">Organizations</h4>
                    <div>
                        <ul id="organizationList">

                        </ul>
                    </div>
                    <button type="button" id="addOrganizationBtn" onclick="supplyEditData()"
                            data-toggle="modal" data-target="#addOrganizationModal">
                        Add Organization
                    </button>
                </div>

                <div class="modal fade" id="addOrganizationModal">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h2>Add Organization</h2>
                            </div>
                            <form class="form-horizontal" role="form">
                                <div class="form-group">
                                    <label for="add-organization-name" class="col-md-4 control-label">Organization Name</label>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" id="add-organization-name"
                                               name="organizationName" placeholder =" Organization Name"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="add-organization-description" class="col-md-4 control-label">Organization description</label>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" id="add-organization-description" 
                                               name="organizationDescription" placeholder="Organization Description"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="addLocationList" class="col-md-4 control-label">Organization Location</label>
                                    <div class="col-md-8">
                                        <!-- They will select from a list of locations-->
                                        <select name="addLocationList" id="addLocationList">

                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-md-offset-4 col-md-8">
                                        <button onclick="addOrganization()">Add Organization</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div> 
                </div>

                <div class="col-md-7">
                    <h4 class="subHeader">Organization Details</h4>
                    <div id="organizationDetailsMain">
                        <div class="col-md-6">
                            <ul id="memberList">

                            </ul>
                        </div>
                        <div class="col-md-6">
                            <span id="organizationID"></span>
                            <div id="organizationName">

                            </div>

                            <div id="organizationLocation">

                            </div>
                            <p id="organizationDescription">

                            </p>
                        </div>
                        <div class="col-md-12">
                            <button type="button" id="editOrganizationBtn" onclick="supplyEditData()"
                                    data-toggle="modal" data-target="#editOrganizationModal">
                                Edit Organization
                            </button>
                            <button type="button" id="deleteOrganizationBtn" onclick="deleteOrganization()">
                                Delete Organization
                            </button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal fade" id="editOrganizationModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h2>Edit Organization</h2>
                        </div>
                        <input type="text" id="edit-organization-ID"/>
                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <label for="edit-organization-name" class="col-md-4 control-label">Organization Name</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="edit-organization-name"
                                           name="newOrganizationName" placeholder="Organization Name"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-organization-description" class="col-md-4 control-label">Organization Description</label>
                                <div class="col-md-8">
                                    <!--<input type="text" class="form-control" id="edit-organization-description"
                                           name="newOrganiztaionDescription" placeholder="Organization Description"/>-->
                                    <textarea id="edit-organization-description"></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="locationList" class="col-md-4 control-label">Organization Location</label>
                                <div class="col-md-8">
                                    <!-- They will select from a list of locations-->
                                    <select name="locationList" id="locationList">

                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <button onclick="editOrganization()">Edit Organization</button>
                                </div>
                            </div>

                        </form>
                    </div>
                </div>
            </div>

        </div>


        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>  
        <script src="${pageContext.request.contextPath}/js/organization.js"
        type="text/javascript"></script>
    </body>
</html>
