<%-- 
    Document   : contact
    Created on : Jan 3, 2018, 7:07:43 PM
    Author     : kylecaaspers
--%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Contact Great Health. Find a Great Health location near you or send us a message.">
        <title>Contact Great Health</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"> 
        <link href="../css/contact.css" rel="stylesheet" type="text/css"/>
        <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
    </head>
    <body>
        <div class="container-fluid">
            <%@include file="pageHeader.jsp"%>
            <div>
                <h1>Contact Us</h1>
                <!--Have a form for leaving messages -->
                <c:if test = "${messageSuccess}">
                    <div id="messageSuccess">
                        Message sent.
                    </div>
                </c:if>
                <div>
                    <form id="contact-form" method="POST" action="${pageContext.request.contextPath}/sendMail" role="form">

                        <div class="messages"></div>

                        <div class="controls">

                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="form_name">Firstname *</label>
                                        <input id="form_name" type="text" name="name" class="form-control" placeholder="Please enter your firstname *" required="required" data-error="Firstname is required.">
                                        <div class="help-block with-errors"></div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="form_lastname">Lastname *</label>
                                        <input id="form_lastname" type="text" name="surname" class="form-control" placeholder="Please enter your lastname *" required="required" data-error="Lastname is required.">
                                        <div class="help-block with-errors"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="form_email">Email *</label>
                                        <input id="form_email" type="email" name="email" class="form-control" placeholder="Please enter your email *" required="required" data-error="Valid email is required.">
                                        <div class="help-block with-errors"></div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="form_phone">Phone</label>
                                        <input id="form_phone" type="tel" name="phone" class="form-control" placeholder="Please enter your phone">
                                        <div class="help-block with-errors"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label for="form_message">Message *</label>
                                        <textarea id="form_message" name="message" class="form-control" placeholder="Message for me *" rows="4" required="required" data-error="Please,leave us a message."></textarea>
                                        <div class="help-block with-errors"></div>
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <input type="submit" class="btn btn-success btn-send" value="Send message">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <p class="text-muted"><strong>*</strong> These fields are required.</p>
                                </div>
                            </div>
                        </div>

                    </form>
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-md-12">
                    <div id="mapCol" class="col-md-6 col-xs-12">
                        <div id="mapDiv">

                        </div>
                    </div>
                    <div id="locationsDiv" class="col-md-6 col-xs-12">
                        <h4>We have three convenient <span class="avoidwrap"> Twin Cities locations, including:</span></h4>
                        <ul>
                            <div class="col-md-12 col-sm-4 col-xs-12">
                                <li>
                                    <a href="./westsaintpaul.jsp">West Saint Paul</a> <span class="avoidwrap">- (651) 453-9150</span>
                                </li>
                            </div>
                            <div class="col-md-12 col-sm-4 col-xs-12">
                                <li>
                                    <a href="./columbia.jsp">Columbia Heights</a> <span class="avoidwrap">- (763) 571-5544</span>
                                </li>
                            </div>
                            <div class="col-md-12 col-sm-4 col-xs-12">
                                <li>
                                    <a href="./midway.jsp">Saint Paul - Midway</a> <span class="avoidwrap">- (651) 645-2315</span>
                                </li>
                            </div>
                        </ul>
                    </div>

                </div>
            </div>
            <%@include file="pageFooter.jsp"%>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="../js/contact.js" type="text/javascript"></script>
        <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCknG2-6iBZT-rSHPaGIm4-A1-U-AeXZB0&callback=initMap">
        </script>
    </body>
</html>
