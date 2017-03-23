var app = angular.module('member');

app.controller("download", function($scope, $http){
	$scope.count = 0;
	$scope.pageSize = 8;
	$scope.page = 0;
	$scope.getNumber = function(num) {
        return new Array(num);
    }
	getHistory(0);
	
	function getHistory(page){
		$http({
			method: 'get',
			url: "http://localhost:8080/download/getByUser/" + $scope.userId + "?page=" + parseInt(page)
																		+ "&size=" + parseInt($scope.pageSize)
		}).success(function(data, status, headers, config){
			$scope.files = data;
			$scope.page = page;
		})
		.error(function(data, status, headers, config){});	
	}
	
    $scope.getDownloadByPage = function(page){
    	getHistory(parseInt(page), parseInt($scope.pageSize));
    }
    
    $scope.getInfoFile = function(id){
    	$('.modal').modal('show');
    	$http({
			method: 'get',
			url: "http://localhost:8080/file/get/"+id
		}).success(function(data, status, headers, config){
			$scope.fileDetail = data;
		})
		.error(function(data, status, headers, config){
			alert("fail__")
		});
    	$http({
			method: 'get',
			url: "http://localhost:8080/download/total/"+id
		}).success(function(data, status, headers, config){
			$scope.total = data;
			
		})
		.error(function(data, status, headers, config){
			alert("fail__")
		});
    	getComment(id);
    }
});