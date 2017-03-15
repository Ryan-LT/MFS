var app = angular.module('admin');
app.controller("fileController", function($scope, $http, fileDataOp, $routeParams){

	getData();
	function getData() { 
		$http({
			method: 'get',
			url: "http://localhost:8080/file/allPagination/0/10"
		}).success(function(data, status, headers, config){
			$scope.files = data;
		})
		.error(function(data, status, headers, config){});
	}
	$scope.deleteFile = function(id){
		fileDataOp.deleteFile(id).then(Success, Error);
	};

	// Exception Handling
	var Success = function(data, status, headers, config){
		getData();
	};

	var Error = function(data, status, headers, config){
		alert("Error");
	};
});