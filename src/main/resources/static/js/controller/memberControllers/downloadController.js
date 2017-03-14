var app = angular.module('myWeb');

app.controller("download", function($scope, $http){
	$scope.count = 0;
	getHistory(0,2);
	
	countDownloadHistory();
	function getHistory(page, pageSize){
		$http({
			method: 'get',
			url: "http://localhost:8080/download/history/" + $scope.userId + "/" + page + "/" + pageSize
		}).success(function(data, status, headers, config){
			$scope.files = data;
		})
		.error(function(data, status, headers, config){});	
	}
	
	$scope.getNumber = function(num) {
        return new Array(num);
    }
	
    function countDownloadHistory() { 
		$http({
			method: 'get',
			url: "http://localhost:8080/download/countHistory/" + $scope.userId
		}).success(function(data, status, headers, config){
			$scope.count = Math.round(data/2);
		})
		.error(function(data, status, headers, config){
			alert("fail");
		});
	}
    $scope.getDownloadByPage = function(page, pageSize){
    	getHistory(parseInt(page), parseInt(pageSize));
    }
	
});