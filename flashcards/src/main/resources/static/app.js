/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var app = angular.module('app', ['ngRoute','ngResource']);

app.config(function($routeProvider){
    $routeProvider
        .when('/',{
            templateUrl: 'index.jsp',
            controller: 'MainController'
        });
});

app.controller('MainController', function($scope) {
    $scope.headingTitle = "Text from Angular function";
});