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
            #cabinetModal{
                padding: 30px;
                margin: 30px;
            }
            #calculatedImpedance{
                visibility: hidden;
            }
            .speakerCabinet{
                border: #000 solid 1px;
                height: 200px;
                width: 40%;
                margin: 10px;
                text-align: center;
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
                                       onclick="selectAmpOhm()"> 4ohm<br>
                                <input type="radio" name="ampOhm" value="8" 
                                       onclick="selectAmpOhm()"> 8ohm<br> 
                                <input type="radio" name="ampOhm" value="16" 
                                       onclick="selectAmpOhm()"> 16ohm<br> 
                            </form>
                            <!--                            <p>
                                                            The amp is set to work with X<c:out value="${ampOhm}"/> ohms.
                                                        </p>-->
                        </div>
                    </div>
                </div>
                <div class="col-md-6"> 
                    <!-- section for info -->
                    <h2>Info</h2>
                    <div id="calculatedImpedance">
                        <p>Total speaker impedance is: 
                            <c:out value="${calculatedImpedance}"/> &#937 </p>
                    </div>
                </div>
            </div>

            <button type="button" id="cabinetButton"
                    data-toggle="modal" data-target="#cabinetModal">
                Add Speaker Cabinet
            </button>
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

                                <input id="4ohmCabinet" type="radio" name="speakerSelection" value="4">
                                <label for="4ohmCabinet">4 Ohm</label>

                                <input id="8ohmCabinet" type="radio" name="speakerSelection" value="8">
                                <label for="8ohmCabinet">8 Ohm</label>

                                <input id="16ohmcabinet" type="radio" name="speakerSelection" value="16">
                                <label for="16ohmCabinet">16 Ohm</label>
                            </div>
                            <div class="modal-footer">
                                <button type="submit">Add</button>
                                <button type="close" data-dismiss="modal">Cancel</button>
                            </div>
                        </form>
                    </div>   
                </div>
            </div>
            <div class="row">
                <div class="col-md-10">
                    <!-- section for speaker cabs, should grow as we add cabs -->
                    <c:forEach var = "currentCabinet" items="${cabinets}">
                        <div class="speakerCabinet col-md-5">
                            <div>
                                <!-- put the cab number and a remove button -->
                                <c:out value="cabinet: ${currentCabinet.cabNumber}"/>
                                <form method="POST" action="deleteCabinet">
                                    <button name="speakerToDelete" value="${currentCabinet.cabNumber}">Delete</button>
                                </form>
                            </div>
                            <c:out value="${currentCabinet.impedance}  ohm"/>
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
                    
            <!-- Placed at the end of the document so the pages load faster -->
            <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
            <!--<script src="${pageContext.request.contextPath}/js/ohm.js"></script>-->
            <script type="text/javascript">
            var cabinetsPresent = ${cabinetsPresent}
            console.log(cabinetsPresent);
            if(cabinetsPresent === true){
                console.log("WE GOT CABINETS");
                $('#calculatedImpedance').css({'visibility': 'visible'}); 
                /*$('#calculatedImpedance').css({'background-color': 'green'});*/
            }
            function selectAmpOhm() {
                //var calculatedImpedance = $('#calculatedImpedance');
                var ampOhm = $("input[name = 'ampOhm']:checked").val();
                console.log(ampOhm);
            }
            </script>


    </body>
</html>

