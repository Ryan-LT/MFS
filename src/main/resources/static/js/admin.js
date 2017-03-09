var app = angular.module('admin', ['ngRoute']);
app.config(function($routeProvider){
    $routeProvider
        .when("/admin", {
            templateUrl: "/views/admin/index.html",
            controller: "mainController"
        })
        .otherwise(
            { redirectTo: '/'}
        );
});