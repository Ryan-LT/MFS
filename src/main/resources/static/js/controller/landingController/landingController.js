var app = angular.module('landing', [ 'ngRoute' ]);
app.controller("landingController", function($http, $scope, $window, $location, $window) {

	$scope.page;
	$scope.pageSum;
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
			$scope.page = data.number;
			$scope.pageSum = data.totalPages;
		}).error(function(data, status, headers, config) {});
	}
	
	$scope.downloadFile = function(idFile){
		$http({
			method: 'get',
			url: "http://localhost:8080/download/check/" + idFile
		}).success(function(data, status, headers, config){
			if(data<=0){
				$window.location.href = 'http://localhost:8080/download/files/' + idFile;
			} else {
				alert("You have meet your download limit!");
			}
		})
		.error(function(data, status, headers, config){
			alert("fail");
		});		
}
	
	$scope.loginAlert = function() {
		alert("You must login in order to download this file !")
	};
	
	$scope.searchByCatelogy = function(type, input) {
		alert(input)
		alert(type)
		$http({
			method: 'get',
			url: "http://localhost:8080/file/searchOption/" + type + "/" + input
		}).success(function(data, status, headers, config){
			$scope.files = data;
			$scope.page = data.number;
			$scope.pageSum = data.totalPages;
		})
		.error(function(data, status, headers, config){
			alert("fail");
		});		
	};
	
	$scope.infoTemp = $scope.search;
	function searchFiles(page, pageSize) {
		$scope.search = $scope.infoSearch;
		alert($scope.infoSearch);
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