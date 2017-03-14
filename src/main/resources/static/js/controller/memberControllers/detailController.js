var app = angular.module('myWeb');

app.controller("detail", function($scope, $routeParams, $http){
	$scope.file="";
	$scope.total=0;
	countDonwload($routeParams.id);
	getFileInfo($routeParams.id);
	
	function getFileInfo(id){
		$http({
			method: 'get',
			url: "http://localhost:8080/file/get/"+id
		}).success(function(data, status, headers, config){
			$scope.file = data;
			getComment(data.id);
		})
		.error(function(data, status, headers, config){
		});
	}
	
	function getComment(id){
		$http({
			method: 'get',
			url: "http://localhost:8080/comment/getCommnetOfFile/" + id
		}).success(function(data, status, headers, config){
			$scope.comments = data;
		})
		.error(function(data, status, headers, config){});
	}
	
	
	function countDonwload(id){
		$http({
			method: 'get',
			url: "http://localhost:8080/download/countDownloadFile/"+id
		}).success(function(data, status, headers, config){
			$scope.total = data;
		})
		.error(function(data, status, headers, config){
			$scope.total = 0;
		});
	}
	
	$scope.saveComment = function(id){
		$scope.contentComment = {
				content: $scope.content,
		    	idFile: id
		}
		$http.post("http://localhost:8080/comment/saveComment", $scope.contentComment)
		.success(function(data, status, headers, config){
			getComment($routeParams.id);
		})
		.error(function(data, status, headers, config){
		});
	}
});