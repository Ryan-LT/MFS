var app = angular.module('login',['ngRoute']);
app.controller("loginController", function($scope, $http){//, $routeParams
	$scope.checkVerify = true;
	$scope.forgetPassword = function(){
		$('#editModal').modal('show');
	}
	
	$scope.verify = function(){
		$http({
			method : 'get',
			url : "http://localhost:8080/user/getByEmail/" + $scope.yourEmail+"/"
		}).success(function(data, status, headers, config) {
			if(data!=""){
				$scope.checkVerify = false;				
			} else {
				$scope.msg="Email is not exist";
				$scope.checkVerify = true;
			}
		}).error(function(data, status, headers, config) {});
	}
});