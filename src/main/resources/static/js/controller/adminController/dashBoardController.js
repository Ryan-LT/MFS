var app = angular.module('admin');
app.controller("dashBoardController", function($scope, $http, $routeParams) {

	getInfor();
	
	function getInfor() {
		countUser();
		countRank();
	}
	function countUser() {
		$http({
			method : 'get',
			url : "http://localhost:8080/user/all/"
		}).success(function(data, status, headers, config) {
			$scope.totalUser = data.totalElements;
		}).error(function(data, status, headers, config) {
		});
	}
	
	function countRank() {
		$http({
			method : 'get',
			url : "http://localhost:8080/rank/all/"
		}).success(function(data, status, headers, config) {
			$scope.totalRank = data.length;
		}).error(function(data, status, headers, config) {
		});
	}
	
	function userPerRank() {
		$http({
			method : 'get',
			url : "http://localhost:8080/user/all/Blahhh"
		}).success(function(data, status, headers, config) {
			$scope.User = data.totalElements;
		}).error(function(data, status, headers, config) {
		});
	}
});