var app = angular.module('admin');
app.controller("fileController", function($scope, $http, fileDataOp, $routeParams){

	$scope.pageSum;
	$scope.page;
	getData('0', '5');
	countFile();
	$scope.getNumber = function(num) {
		return new Array(num);
	}
	
	$scope.getFileByPage = function(page, pageSize) {
		getData(parseInt(page), parseInt(pageSize));
		$scope.page = (parseInt(page));
}
	
	function getData(page, pageSize) {
		$http(
				{
					method : 'get',
					url : "http://localhost:8080/file/allPagination/" + parseInt(page)
							+ "/" + parseInt(pageSize)
				}).success(function(data, status, headers, config) {
			$scope.files = data;
		}).error(function(data, status, headers, config) {
		});
	}
	
	function countFile() {
		$http({
			method : 'get',
			url : "http://localhost:8080/file/countFile/"
		}).success(function(data, status, headers, config) {
			$scope.pageSum = Math.ceil(data / 5);
		}).error(function(data, status, headers, config) {
		});
	}
	
	$scope.deleteFile = function(id){
		fileDataOp.deleteFile(id).then(Success, Error);
	};

	// Exception Handling
	var Success = function(data, status, headers, config){
		getData('0','5');
	};

	var Error = function(data, status, headers, config){
		alert("Error");
	};
});