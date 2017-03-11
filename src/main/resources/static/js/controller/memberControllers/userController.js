var app = angular.module("myWeb", ["ngRoute"]);

app.config(function($routeProvider) {
  $routeProvider
  .when("/userInfo", {
    templateUrl: "/views/member/userInfo.html",
    controller: "userInfo"
  })
  .when("/fileManage", {
    templateUrl: '/views/member/fileManage.html',
    controller: "PersonStorageController"
  })
  .when("/detail/:id", {
    templateUrl: '/views/member/detail.html',
    controller: "Detail"
  })
  .when("/upload", {
    templateUrl: "upload.html"
  })
});


app.controller('mainControl', function($scope, $http){
	$scope.a =1;
});

app.controller('PersonStorageController', function($scope, $http){
    //this.files = storage;
	$scope.count = 0;
    getFiles('0', '2');
    countAllFile();
    $scope.getNumber = function(num) {
        return new Array(num);
    }
    function countAllFile() { 
		$http({
			method: 'get',
			url: "http://localhost:8080/file/countFileOfUser/"+$scope.userId
		}).success(function(data, status, headers, config){
			$scope.count = Math.round(data/2);
		})
		.error(function(data, status, headers, config){
			alert("fail");
		});
	}
    
    function getFiles(page, pageSize) { 
		$http({
			method: 'get',
			url: "http://localhost:8080/file/getByUser/"+$scope.userId+"/"+ parseInt(page)+"/"+ parseInt(pageSize)
		}).success(function(data, status, headers, config){
			$scope.files = data;
		})
		.error(function(data, status, headers, config){
			alert("fail");
		});
	}
    
    $scope.getFileByPage = function(page, pageSize){
    	getFiles(parseInt(page), parseInt(pageSize));
    }
    
    $scope.deleteFile = function(id){
    	$http({
			method: 'get',
			url: "http://localhost:8080/file/delete/"+id
		}).success(function(data, status, headers, config){
			getFiles();
		})
		.error(function(data, status, headers, config){
			alert("fail");
		});
    }
    
});

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

app.controller("Detail", function($scope, $routeParams, $http){
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





