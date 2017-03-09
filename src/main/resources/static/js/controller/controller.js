var app = angular.module('myApp');
app.controller("controller", function($scope, $http, $routeParams){

	getData();
	//Get Data
	function getData() { 
		$http({
			method: 'get',
			url: "http://localhost:8080/user/all"
		}).success(function(data, status, headers, config){
			$scope.users = data;
		})
		.error(function(data, status, headers, config){});
	}
});