var app = angular.module('myWeb');

app.controller("userInfo", function($scope, $http){
	$scope.rankName="";
	getInfo();
	function getNameRank(id){
		$http({
			method: 'get',
			url: "http://localhost:8080/rank/get/"+id
		}).success(function(data, status, headers, config){
			$scope.rankName = data.name;
		})
		.error(function(data, status, headers, config){
			$scope.rankName = "";
		});
	}
	function getInfo() {
		$http({
			method: 'get',
			url: "http://localhost:8080/user/get/"+$scope.userId
		}).success(function(data, status, headers, config){
			$scope.userInfo = data;
			getNameRank($scope.userInfo.rank_Id);
		})
		.error(function(data, status, headers, config){
			alert("fail");
		});
	}
	
	$scope.changePass = function(){//changePass
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
			$scope.msg="TOO FAIL";
		});
	}
});