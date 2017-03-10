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
	
	$scope.getUser = function(id) {
		$scope.editBlock = true;
		$scope.addBlock = false;
		$http({
			method: 'get',
			url: "http://localhost:8080/user/get/" + id
		}).success(function(data, status, headers, config){
			$scope.user = data;
		})
		.error(function(data, status, headers, config){});
	};
	
	$scope.addUser = function() {
		var user = {
				email : $scope.email,
				password : $scope.password,
				name : $scope.name,
				lastName : $scope.lastName
		};
		userDataOp.addUser(user).then(Success, Error);
		$scope.email = "",
		$scope.password = "",
		$scope.name = "",
		$scope.lastName = ""
	};
	
	$scope.editUser = function(user) {
		userDataOp.editUser(user).then(Success, Error);
	};
	
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