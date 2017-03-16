var app = angular.module("myWeb", ["ngRoute"]);

app.config(function($routeProvider) {
  $routeProvider
  .when("/userInfo", {
    templateUrl: "/views/member/userInfo.html",
    controller: "userInfo"
  })
  .when("/fileManage", {
    templateUrl: '/views/member/fileManage.html',
    controller: "fileManage"
  })
  .when("/detail/:id", {
    templateUrl: '/views/member/detail.html',
    controller: "detail"
  })
  .when("/download", {
    templateUrl: "/views/member/download.html",
    controller: "download"
  })
  .when("/", {
    controller: "mainControl"
  })
});