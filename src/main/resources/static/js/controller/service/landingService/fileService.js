var app = angular.module('landing');

app.factory('fileDataOp',function($http) {
	
	var deleteFile = function(id) {
		return $http({
			method: 'GET',
			url: "http://localhost:8080/file/delete/"+id
		}).then(function(response) {
			return response.data;
		});
	}
	
	return {
		deleteFile : deleteFile
	}
});