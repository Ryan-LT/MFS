var app = angular.module('admin', ['ngRoute']);
app.config(function($routeProvider){
    $routeProvider
        .when("/admin", {
            templateUrl: "/views/admin/index.html",
            controller: "mainController"
        })
        .when("/rank", {
            templateUrl: "/views/admin/rank.html",
            controller: "rankController"
        })
        .otherwise(
            { redirectTo: '/'}
        );
});