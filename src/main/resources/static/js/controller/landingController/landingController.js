var app = angular.module('landing', [ 'ngRoute' ]);
app.controller("landingController", function($http, $scope, $window, $location, $window) {

	getFile();
	
	function getFile() {
		$http({
			method : 'get',
			url : "http://localhost:8080/file/all/"
		}).success(function(data, status, headers, config) {
			$scope.files = data;
		}).error(function(data, status, headers, config) {
			alert("fail");
		});
	}
	
	$scope.getFileByCategory = function(category, page, pageSize){
		$http({
			method : 'get',
			url : "http://localhost:8080/file/getFileByCategory/" +category +"?size="+ parseInt(pageSize)
										+ "&page=" + parseInt(page)
		}).success(function(data, status, headers, config) {
			$scope.files = data;
			alert(data)
		}).error(function(data, status, headers, config) {});
	}
	
	$scope.getOwner = function(id) {
		$http({
			method : 'get',
			url : "http://localhost:8080/user/get/" + id
		}).success(function(data, status, headers, config) {
			return data.name;
		}).error(function(data, status, headers, config) {
			alert("fail");
		});
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

	$scope.getDownloadByPage = function(page, pageSize) {
		searchFiles(page, pageSize);
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