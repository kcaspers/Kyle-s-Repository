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
        <link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet">

    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <h1>Speaker Impedance Calculator</h1>
                </div>
                <div id="saveLoad" class="col-md-3 col-md-offset-3">
                    <button type="button" data-toggle="modal" data-target="#saveLoadModal">
                        Save/Load Configuration
                    </button>
                </div>    
            </div>
            <div class="modal fade" id="saveLoadModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h2>Save/Load Configuration</h2>
                        </div>
                        <div class="modal-body">
                            <!--cycle through existing cabinets here -->
                            <ul id="savedRigs"></ul>
                            <button id="saveConfiguration" data-toggle="modal" 
                                    data-target="#saveModal" onclick="closeModal()">
                                Save Current
                            </button>
                        </div>
                    </div>
                </div>

            </div>
            <div class="modal fade" id="saveModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h2>Save Current Configuration</h2>
                            <form method="POST" action="saveRig">
                                <!--<input type="hidden" name="cabinets" value="${cabinets}"-->
                                <!--<input type="hidden" name="saveAmpOhm">-->
                                <div class="form-group">
                                    <label for="saveTitle">Title:</label>
                                    <input id="saveTitle" type="text" name="title">
                                </div>
                                <div class="form-group">
                                    <button type="submit">Save</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <hr/>
            <div class="row" style="height:250px">

                <div class="col-md-6" > 
                    <!-- section for amp -->
                    <h2>Head</h2>
                    <div id="amp">
                        <!-- I don't think these were needed, all speakers are in parallel regardless of the order
                        <div >
                            <label for="ampSpeakerOut1">Speaker Out 1</label>
                            <input type="button" id="ampSpeakerOut1">
                            
                        </div>
                        <div>
                            <label for="ampSpeakerOut2">Speaker Out 2</label>
                            <input type="button" id="ampSpeakerOut2">
                        </div>
                        -->
                        <div id="ohmSetting">
                            <form>
                                <input type="radio" name="ampOhm" value="4" 
                                       onclick="selectAmpOhm()"
                                       <c:if test="${ampOhm == 4}">checked</c:if>> 4ohm<br>
                                <input type="radio" name="ampOhm" value="8" 
                                       onclick="selectAmpOhm()"
                                       <c:if test="${ampOhm == 8}">checked</c:if>> 8ohm<br> 
                                <input type="radio" name="ampOhm" value="16" 
                                       onclick="selectAmpOhm()"
                                       <c:if test="${ampOhm == 16}">checked</c:if>> 16ohm<br> 
                            </form>
                        </div>
                        <div class="displayWhenSpeakers">
                            <p>
                                The ideal amp ohm setting is: <c:out value="${desiredAmpSetting}"/> ohms.
                            </p>
                        </div>
                    </div>
                </div>
                <div class="col-md-6"> 
                    <!-- section for info -->
                    <h2>Info</h2>
                    <div id="calculatedImpedance" class="displayWhenSpeakers">
                        <p>Resulting impedance is: 
                            <c:out value="${calculatedImpedance}"/> &#937 </p>
                        <!-- have a warning if they go below 2 ohm -->
                        <c:choose>
                            <c:when test="${calculatedImpedance <= 2}">
                                <p id="lowImpedanceWarning">
                                    Your resistance is very low. This can damage your amplifier.
                                </p>
                            </c:when>
                        </c:choose>
                    </div>

                </div>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <button type="button" id="cabinetButton"
                            data-toggle="modal" data-target="#cabinetModal">
                        Add Speaker Cabinet
                    </button>
                </div>   
                <div class="modal fade" id="cabinetModal">
                    <!--This modal will contain the add speaker cab form -->
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h2>Add cabinet</h2>
                            </div>
                            <form method="POST" action="addCabinet">
                                <div class="modal-body">
                                    <p>Select speaker impedance.</p>

                                    <input id="4ohmCabinet" type="radio" name="speakerSelection" value="4" checked>
                                    <label for="4ohmCabinet">4 Ohm</label>

                                    <input id="8ohmCabinet" type="radio" name="speakerSelection" value="8">
                                    <label for="8ohmCabinet">8 Ohm</label>

                                    <input id="16ohmcabinet" type="radio" name="speakerSelection" value="16">
                                    <label for="16ohmCabinet">16 Ohm</label>
                                </div>
                                <div class="modal-footer">
                                    <button id="addCabinetSubmit" type="submit">Add</button>
                                    <button type="close" data-dismiss="modal">Cancel</button>
                                </div>
                            </form>
                        </div>   
                    </div>
                </div>
            </div>  
            <div class="row">
                <div class="col-md-10">
                    <!-- section for speaker cabs, should grow as we add cabs -->
                    <input id="cabPresent" type="hidden" value="${cabinetsPresent}"/>
                    <c:forEach var = "currentCabinet" items="${cabinets}">
                        <div class="speakerCabinet col-md-5" id="speakerCabinet${currentCabinet.cabNumber}">
                            <div>
                                <!-- put the cab number and a remove button -->
                                <c:out value="cabinet ${currentCabinet.cabNumber} 
                                       delivers ${currentCabinet.outputPercentage}% of amp output"/>
                                <form method="POST" action="deleteCabinet">
                                    <button name="speakerToDelete" class="speakerToDelete"
                                            value="${currentCabinet.cabNumber}">Delete</button>
                                </form>
                            </div>
                            <!-- I should display which speaker is getting what percentage of output-->
                            <div id="cab${currentCabinet.cabNumber}impedance">
                                <c:out value="${currentCabinet.impedance}  ohm"/>
                            </div>

                            <!--
                            <div>
                                <input type="button" id="speakerInput">Input</input>
                                <input type="button" id="speakerOutput">Output</input>
                            </div>
                            -->
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div>
                <hr>
                <p id="parallelInfo" class="displayWhenSpeakers">
                    Note: all cabinets connected in parallel. Linking speakers from the ports on the reverse
                    of a cabinet and connecting multiple speakers to the 'speaker out' of an amp are equal.
                </p>
            </div>

            <!-- Placed at the end of the document so the pages load faster -->
            <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/ohm.js"></script>

        </div>
    </body>
</html>

