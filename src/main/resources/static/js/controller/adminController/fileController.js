var app = angular.module('admin');
app.controller("fileController", function($scope, $http, fileDataOp,
		$routeParams) {
	$scope.selectedIndex = 0;
	$scope.pageSum;
	$scope.page = 0;
	$scope.pageSize = 8;
	getData($scope.page);
	$scope.getNumber = function(num) {
		return new Array(num);
	}

	$scope.getFileByPage = function(page) {
		getData(parseInt(page), parseInt($scope.pageSize));
		$scope.selectedIndex = page;
	}

	function getData(page) {
		$http(
				{
					method : 'get',
					url : "http://localhost:8080/file/all/?page="
							+ parseInt(page) + "&size="
							+ parseInt($scope.pageSize)
				}).success(function(data, status, headers, config) {
			$scope.page = page;
			$scope.files = data.content;
			$scope.pageSum = data.totalPages;
		}).error(function(data, status, headers, config) {
		});
	}
	$scope.deleteFile = function(id) {
		fileDataOp.deleteFile(id).then(Success, Error);
	};

	// Exception Handling
	var Success = function(data, status, headers, config) {
		getData($scope.page);
	};

	var Error = function(data, status, headers, config) {
		alert("Error");
	};
});