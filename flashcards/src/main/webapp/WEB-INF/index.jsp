<%-- 
    Document   : index
    Created on : Nov 18, 2017, 7:26:04 PM
    Author     : kylecaaspers
--%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>JSP Page</title>
        <link href="bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <div ng-view></div>
    <script src="/webjars/angularjs/1.4.9/angular.js"></script>
    <script src="/webjars/angularjs/1.4.9/angular-resource.js"></script>
    <script src="/webjars/angularjs/1.4.9/angular-route.js"></script>
    <script src="app.js"></script>
    <script src="controller.js"></script>
</head>
<body>
    <h1>{{headingTitle}}</h1>
</body>
</html>

