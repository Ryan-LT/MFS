var app = angular.module('admin');
app.controller("mainController", function($scope, $http, userDataOp, $routeParams){

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
	
	$scope.deleteUser = function(id){
		userDataOp.deleteUser(id).then(Success, Error);
	}
	
	// Exception Handling
	var Success = function(data, status, headers, config){
		getData();
	};

	var Error = function(data, status, headers, config){
		alert("Error");
	};
});