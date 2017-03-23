var app = angular.module('admin');
app.controller("dashBoardController", function($scope, $http, $routeParams) {

	
	$scope.ranks = null;
	getInfor();
	$scope.listUserRank=[];
	
	$scope.labels = ["Download Sales", "In-Store Sales", "Mail-Order Sales"];
	$scope.data = [300, 500, 100];

	function getInfor() {
		countUser();
		countFile();
		Rankdata();
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
	
	function countFile() {
		$http({
			method : 'get',
			url : "http://localhost:8080/file/all/"
		}).success(function(data, status, headers, config) {
			$scope.totalFile = data.totalElements;
		}).error(function(data, status, headers, config) {
		});
	}

	function Rankdata() {
		$http({
			method : 'get',
			url : "http://localhost:8080/rank/all/"
		}).success(function(data, status, headers, config) {
			$scope.ranks = data;
			for(var i=0;i<data.length;i++){
				userPerRank(data[i].id, data[i].name);
			}
		}).error(function(data, status, headers, config) {
		});
	}

	function userPerRank(rankId, rankName) {
			$http({
				method : 'get',
				url : "http://localhost:8080/user/get/rank/" + rankId
			}).success(function(data, status, headers, config) {
				$scope.listUserRank.push({rankName,data});
			}).error(function(data, status, headers, config) {
			});
	}
});