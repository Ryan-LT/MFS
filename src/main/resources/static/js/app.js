var app = angular.module('myApp', ['ngRoute']);
app.config(function($routeProvider){
    $routeProvider
        .when("/usersdetail", {
            templateUrl: "/views/test.html",
            controller: "controller"
        })
        .otherwise(
            { redirectTo: '/'}
        );
});
