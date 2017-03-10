var app = angular.module('admin');

app.factory('userDataOp',function($http) {
	
	var addUser = function(user) {
		return $http({
			method: 'POST',
			url: "http://localhost:8080/user/add/",
			data: user
		}).then(function(response) {
			return response.data;
		});
	}
	
	var editUser = function(user) {
		return $http({
			method: 'POST',
			url: "http://localhost:8080/user/update/",
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
		editUser : editUser,
		addUser: addUser,
		deleteUser : deleteUser
	}
});