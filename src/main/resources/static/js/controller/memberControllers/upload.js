var app = angular.module("myWeb");

app.controller("uploadController", function($scope, $routeParams, $http){
	$scope.uploadFile = function(){
		$http.post("http://localhost:8080/upload/", $scope.file)
	.success(function(data, status, headers, config){
			alert("OK");
	})
	.error(function(data, status, headers, config){
		alert("fail");
	});	
	}
});