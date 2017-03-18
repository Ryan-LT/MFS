var app = angular.module('admin');

app.factory('rankDataOp',function($http) {
	
	var addRank = function(rank) {
		return $http({
			method: 'POST',
			url: "http://localhost:8080/rank/insert/",
			data: rank
		}).then(function(response) {
			return response.data;
		});
	}
	
	var editRank = function(rank) {
		return $http({
			method: 'POST',
			url: "http://localhost:8080/rank/update/",
			data: rank
		}).then(function(response) {
			return response.data;
		});
	}
	
	var deleteRank = function(id) {
		return $http({
			method: 'GET',
			url: "http://localhost:8080/rank/delete/"+id
		}).then(function(response) {
			return response.data;
		});
	}
	
	return {
		editRank : editRank,
		addRank: addRank,
		deleteRank : deleteRank
	}
});