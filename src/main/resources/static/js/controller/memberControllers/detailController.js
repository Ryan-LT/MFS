var app = angular.module('myWeb');

app.controller("detail", function($scope, $routeParams, $http){
	$scope.file="";
	$scope.total=0;
	$http({
		method: 'get',
		url: "http://localhost:8080/file/get/"+$routeParams.id
	}).success(function(data, status, headers, config){
		$scope.file = data;
	})
	.error(function(data, status, headers, config){
		alert("fail");
	});
	
	$http({
		method: 'get',
		url: "http://localhost:8080/download/countDownloadFile/"+$routeParams.id
	}).success(function(data, status, headers, config){
		$scope.total = data;
	})
	.error(function(data, status, headers, config){
		$scope.total = 0;
	});
});