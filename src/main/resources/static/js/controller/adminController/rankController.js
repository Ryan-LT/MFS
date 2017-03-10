var app = angular.module('admin');
app.controller("rankController", function($scope, $http, rankDataOp, $routeParams){

	getData();
	function getData() { 
		$http({
			method: 'get',
			url: "http://localhost:8080/rank/all/"
		}).success(function(data, status, headers, config){
			$scope.ranks = data;
		})
		.error(function(data, status, headers, config){});
	}

	$scope.getRank = function(id) {
		$scope.editBlock = true;
		$scope.addBlock = false;
		$http({
			method: 'get',
			url: "http://localhost:8080/rank/get/" + id
		}).success(function(data, status, headers, config){
			$scope.rank = data;
		})
		.error(function(data, status, headers, config){});
	};

	$scope.addRank = function() {
		var rank = {
				name : $scope.name,
				sizeupload : $scope.sizeupload,
				sizedownload : $scope.sizedownload,
				sizerank : $scope.sizerank
		};
		rankDataOp.addRank(rank).then(Success, Error);
		$scope.name = "",
		$scope.sizeupload = "",
		$scope.sizedownload = "",
		$scope.sizerank = ""
	};

	$scope.editRank = function(rank) {
		rankDataOp.editRank(rank).then(Success, Error);
	};

	$scope.deleteRank = function(id){
		rankDataOp.deleteRank(id).then(Success, Error);
	};

	// Exception Handling
	var Success = function(data, status, headers, config){
		getData();
	};

	var Error = function(data, status, headers, config){
		alert("Error");
	};
});