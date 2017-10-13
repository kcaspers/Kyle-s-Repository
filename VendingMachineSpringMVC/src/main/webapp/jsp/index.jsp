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
            #line{
                display:block;
                border:none;
                color:white;
                height:1px;
                background:black;
                background: -webkit-gradient(radial, 50% 50%, 0, 50% 50%, 700, from(#000), to(#fff));
            }

            .line2{
                display:block;
                border:none;
                color:white;
                height:1px;
                background:black;
                background: -webkit-gradient(radial, 50% 50%, 0, 50% 50%, 175, from(#000), to(#fff));
            }
            .infoTextArea{
                height: 90px;
                border: #000 solid 1px;
                width: 100%;
            }
            #messages{
                margin-bottom:15px;
            }
            .button-container form,
            .button-container form div {
                display: inline;
            }

            .button-container button {
                display: inline;
                vertical-align: middle;
                width: 100px;
            }

            .moneyButton {
                width: 100px;
            }
            .itemButton{
                border: #000 solid 1px;
                height: 200px;
                width: 200px;
                margin: 10px;
                text-align: center;
            }
        </style>
    </head>
    <body>
        <header class="page-header row" style="text-align: center; border-bottom: none">
            <h1>Vending Machine</h1>
            <!--<a href="${pageContext.request.contextPath}/loadPage">Load</a>-->
            <div id="line"></div>    
        </header>
        <div class="container">
            <div class="row">
                <div class="col-md-9">
                    <div class="row" id="itemMenu">
                        <c:forEach var="currentItem" items="${items}" >
                            <form method="POST" action="addItem" class="itemMenu">   
                                <button class="itemButton col-md-3" type="submit" 
                                        value="${currentItem.name}" name="itemName">
                                    <strong><c:out value="${currentItem.name}"/></strong></br>
                                    $<c:out value="${currentItem.cost}"/></br>
                                    Quantity left: <c:out value="${currentItem.quantity}"/>
                                </button>
                            </form>
                        </c:forEach>
                    </div>
                </div>

                <div class="col-md-3">
                    <div style="text-align: center">
                        <h2>Total</h2>
                        <div class="form-group">
                            <input type="text" id="totalMoney" 
                                   value = "<c:out value='${moneyTotal}' />" readonly/>
                        </div>
                        <div class="button-container form-group">
                            <form class="form-horizontal" role="form" method="POST" action="addDollar">
                                <input type="submit" class="btn btn-default moneyButton" value="addDollar"/>
                            </form>
                            <form class="form-horizontal" role="form" method="POST" action="addQuarter">
                                <input type="submit" class="btn btn-default moneyButton" value="addQuarter"/>
                            </form>
                        </div>
                        <div class="button-container form-group">
                            <form class="form-horizontal" role="form" method="POST" action="addDime">
                                <input type="submit" class="btn btn-default moneyButton" value="addDime"/>
                            </form>
                            <form class="form-horizontal" role="form" method="POST" action="addNickel">
                                <input type="submit" class="btn btn-default moneyButton" value="addNickel"/>
                            </form>
                        </div>
                    </div>
                    <div class="line2"></div>    


                    <div style="text-align: center">
                        <h2>Messages</h2>
                        <form class="form-horizontal" role="form" method="POST" action="makePurchase">
                            <!--Remember to make a controller method called makePurchase -->
                                <div class="infoTextArea" id="messages">
                                    <c:out value="${vendingMessage}"/>
                                </div>
                            <div class="form-group" id="itemInput">
                                <label for="itemInput">Item</label>
                                <input type="text" name="selectedItem" 
                                       value = "<c:out value='${itemName}' />" readonly/>
                            </div>
                            <!--The controller method that runs when you click an item will supply
                                us with that specific itemId which should fill this field-->
                            <div class="form-group">
                                <input type="submit" class="btn btn-default" value="Make Purchase"/>
                            </div>
                        </form>
                    </div>     
                    <div class="line2"></div>        

                    <div style="text-align: center">
                        <h2>Change</h2>
                        <form class="form-horizonal" role="form" method="POST" action="changeReturn">
                            <div class="form-group infoTextArea">
                                ${changeAmount}
                            </div>
                            <div class="form-group">
                                <input type="submit" class="btn btn-default" value="Change Return"/>
                            </div>
                        </form>
                    </div>
                </div>

            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>





