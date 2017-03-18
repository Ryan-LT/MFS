var app = angular.module('admin');
app.controller("fileController", function($scope, $http, fileDataOp, $routeParams){
	$scope.selectedIndex = 0;
	$scope.pageSum;
	$scope.page;
	getData('0', '5');
	$scope.getNumber = function(num) {
		return new Array(num);
	}
	
	$scope.getFileByPage = function(page, pageSize) {
		getData(parseInt(page), parseInt(pageSize));
		$scope.page = (parseInt(page));
		$scope.selectedIndex = page;
}
	
	function getData(page, pageSize) {
		$http({
			method : 'get',
			url : "http://localhost:8080/file/all/?page=" + parseInt(page)
			+ "&size=" + parseInt(pageSize)
		}).success(function(data, status, headers, config) {
			$scope.files = data.content;
			$scope.pageSum = data.totalPages;
		}).error(function(data, status, headers, config) {});
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