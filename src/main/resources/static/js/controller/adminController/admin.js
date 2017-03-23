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
        .when("/file", {
            templateUrl: "/views/admin/file.html",
            controller: "fileController"
        })
        .when("/user", {
            templateUrl: "/views/admin/userInfo.html",
            controller: "userInfo"
        })
        .when("/", {
            templateUrl: "/views/admin/dashBoard.html",
            controller: "dashBoardController"
        })
        .otherwise(
            { redirectTo: '/'}
        );
});