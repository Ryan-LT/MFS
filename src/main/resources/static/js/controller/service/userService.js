var app = angular.module('admin');

app.factory('userDataOp',function($http) {
	
//	var addAvailable = function(id) {
//		return $http({
//			method: 'GET',
//			url: "http://localhost:9000/fresherangular/product/increase/"+id
//		}).then(function(response) {
//			return response.data;
//		});
//	}
//	
//	var minusAvailable = function(id) {
//		return $http({
//			method: 'GET',
//			url: "http://localhost:9000/fresherangular/product/decrease/"+id
//		}).then(function(response) {
//			return response.data;
//		});
//	}
//	
	var addUser = function(user) {
		return $http({
			method: 'POST',
			url: "http://localhost:8080/user/add/",
			data: user
		}).then(function(response) {
			return response.data;
		});
	}
	
	var deleteUser = function(id) {
		return $http({
			method: 'GET',
			url: "http://localhost:8080/user/delete/"+id
		}).then(function(response) {
			return response.data;
		});
	}
	
	
	return {
//		addAvailable : addAvailable,
//		minusAvailable : minusAvailable,
		addUser: addUser,
		deleteUser : deleteUser
	}
});