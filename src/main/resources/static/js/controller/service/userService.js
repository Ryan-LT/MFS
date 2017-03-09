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
//	var addProduct = function(product) {
//		return $http({
//			method: 'POST',
//			url: "http://localhost:9000/fresherangular/product/add",
//			data: product
//		}).then(function(response) {
//			return response.data;
//		});
//	}
//	
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
//		addProduct: addProduct,
		deleteUser : deleteUser
	}
});