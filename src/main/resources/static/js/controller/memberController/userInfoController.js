var app = angular.module('member');

app.controller("userInfo", function($scope, $http){
	$scope.rankName="";
	getInfo();
	function getInfo() {
		$http({
			method: 'get',
			url: "http://localhost:8080/user/get/"+$scope.userId
		}).success(function(data, status, headers, config){
			$scope.userInfo = data;
		})
		.error(function(data, status, headers, config){
		});
	}
	
	$scope.changePass = function(){
		if($scope.confirmpassword == $scope.newPassword){
			$scope.d = {
					id: $scope.userId,
					oldPass:$scope.currentPassword,
					newPass:$scope.newPassword
				}
				$http({
		            method: 'POST',
		            url: "http://localhost:8080/user/changePass/",
		            data:{
		    			id: $scope.userId,
		    			oldPass:$scope.currentPassword,
		    			newPass:$scope.newPassword
		    		}
				})
				.success(function(data, status, headers, config){
					//alert(data);
						if(data.state){
							$scope.msg="Change password seccussful!";
						} else {
							$scope.msg=data.msg;
						}
				})
				.error(function(data, status, headers, config){
					$scope.msg="Something was wrong!";
			});
		} else {
			$scope.msg="Password confirm not correct!";
		}
		
	}
});