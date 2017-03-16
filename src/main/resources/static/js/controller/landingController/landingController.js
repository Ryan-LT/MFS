var app = angular.module('landing', [ 'ngRoute' ]);
app.controller("landingController", function($http, $scope, $window, $location, $window) {

	getFile();
	
	function getFile() {
		getFileByCategoryFunction('all', 0, 8);
	}
	
	$scope.getFileByCategory = function(category, page, pageSize){
		getFileByCategoryFunction(category, page, pageSize);
	}
	
	function getFileByCategoryFunction(category, page, pageSize){
		$scope.catelogyName = category;
		$http({
			method : 'get',
			url : "http://localhost:8080/file/getFileByCategory/" +category +"?size="+ parseInt(pageSize)
										+ "&page=" + parseInt(page)
		}).success(function(data, status, headers, config) {
			$scope.files = data;
		}).error(function(data, status, headers, config) {});
	}
	

	$scope.doSearch = function(page, pageSize) {
		$scope.search = $scope.infoSearch;
		countSearch();
		searchFiles(page, pageSize);

	};// countSearch
	$scope.infoTemp = $scope.search;
	function searchFiles(page, pageSize) {
		$scope.search = $scope.infoSearch;
		countSearch();
		$http(
				{
					method : 'get',
					url : "http://localhost:8080/file/fSearch/"
							+ $scope.infoSearch + "/" + page + "/" + pageSize
				}).success(function(data, status, headers, config) {
			$scope.files = data;
		}).error(function(data, status, headers, config) {
		});
	}
	
	$scope.getNumber = function(num) {
		return new Array(num);
	}

	function countSearch() {
		$http({
			method : 'get',
			url : "http://localhost:8080/file/countSearch/" + $scope.infoSearch
		}).success(function(data, status, headers, config) {
			$scope.count = Math.ceil(data / 2);
		}).error(function(data, status, headers, config) {
			alert("fail");
		});
	}
});