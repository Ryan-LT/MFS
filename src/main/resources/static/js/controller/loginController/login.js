var app = angular.module('login',['ngRoute']);
app.controller("loginController", function($scope, $http){//, $routeParams
	$scope.checkVerify = true;
	$scope.forgetPassword = function(){
		$('#editModal').modal('show');
	}
	
	$scope.verify = function(){
		$scope.dataEmail = {
				email: $scope.yourEmail
		}
		$http.post("http://localhost:8080/resetpassword/checkEmail", $scope.yourEmail)
		.success(function(data, status, headers, config) {
			console.log(data);
			console.log(status);
			if(data){
				$scope.checkVerify = false;		
			} else {
				$scope.msg="Email is not exist";
				$scope.checkVerify = true;
			}
		}).error(function(data, status, headers, config) {});
	}
});