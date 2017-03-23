var app = angular.module('admin');
app.controller("dashBoardController", function($scope, $http, $routeParams) {
	
	$scope.textNum;
	$scope.graphicNum;
	$scope.spreadsheettNum;
	$scope.ranks = null;
	getInfor();
	$scope.listUserRank=[];
	
	
	function getInfor() {
		countUser();
		countFile();
		filePerCategory()
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
	
	function filePerCategory() {
		$http({
			method : 'get',
			url : "http://localhost:8080/file/find/Category/Text"
		}).success(function(data, status, headers, config) {
			$scope.textNum = data.numberOfElements;
		}).error(function(data, status, headers, config) {
		});
		
		$http({
			method : 'get',
			url : "http://localhost:8080/file/find/Category/Graphic"
		}).success(function(data, status, headers, config) {
			$scope.graphicNum = data.numberOfElements;
		}).error(function(data, status, headers, config) {
		});
		
		$http({
			method : 'get',
			url : "http://localhost:8080/file/find/Category/Spreadsheet"
		}).success(function(data, status, headers, config) {
			$scope.spreadsheettNum = data.numberOfElements;
		}).error(function(data, status, headers, config) {
		});
		
		$http({
			method : 'get',
			url : "http://localhost:8080/file/find/Category/Presentation"
		}).success(function(data, status, headers, config) {
			$scope.presentationNum = data.numberOfElements;
		}).error(function(data, status, headers, config) {
		});
		$http({
			method : 'get',
			url : "http://localhost:8080/file/find/Category/Multimedia"
		}).success(function(data, status, headers, config) {
			$scope.multimediatNum = data.numberOfElements;
		}).error(function(data, status, headers, config) {
		});
		$http({
			method : 'get',
			url : "http://localhost:8080/file/find/Category/Compression"
		}).success(function(data, status, headers, config) {
			$scope.CompressionNum = data.numberOfElements;
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

