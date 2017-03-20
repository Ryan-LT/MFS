var app = angular.module('landing',['ngRoute']);
app.controller("landingController", function($scope, $http, $window, fileDataOp, $routeParams){
	
	$scope.filterFile ='';
	$scope.sortReverse = false; 
	  
	$scope.page = 0;
	$scope.pageSum;
	$scope.pageSize = 8;
	$scope.selectedIndex = 0;
	
	$scope.types = ["All", "Category", "Name", "Uploader", "Size"];
	$scope.Selectedtype = $scope.types[0];
	
	getData('0', '8');
	
	$scope.getNumber = function(num) {
		return new Array(num);
	}
	
	$scope.getFileByPage = function(page, pageSize) {
		getData(parseInt(page), parseInt(pageSize));
		$scope.page = (parseInt(page));
		$scope.selectedIndex = page;
	}
	
	$scope.searchFile = function(Selectedtype, infoSearch, page, pageSize) {
		$http({
			method : 'get',
			url : "http://localhost:8080/file/find/" + Selectedtype + "/" + infoSearch + "/?page=" + parseInt(page)
			+ "&size=" + parseInt(pageSize)
		}).success(function(data, status, headers, config) {
			$scope.files = data;
			$scope.pageSum = data.totalPages;
		}).error(function(data, status, headers, config) {});
	}
	
	function getData(page, pageSize) {
		$http({
			method : 'get',
			url : "http://localhost:8080/file/all/?page=" + parseInt(page)
			+ "&size=" + parseInt(pageSize)
		}).success(function(data, status, headers, config) {
			$scope.files = data;
			$scope.pageSum = data.totalPages;
		}).error(function(data, status, headers, config) {});
	}
	
	$scope.downloadFile = function(idFile){
		$http({
			method: 'get',
			url: "http://localhost:8080/download/check/" + idFile
		}).success(function(data, status, headers, config){
			if(data<=0){
				$window.location.href = 'http://localhost:8080/download/files/' + idFile;
			} else {
				alert("You have meet your download limit!");
			}
		})
		.error(function(data, status, headers, config){
			alert("fail");
		});		
	}
	
	$scope.loginAlert = function() {
		alert("You need to login in order to download!");
	}

	// Exception Handling
	var Success = function(data, status, headers, config){
		getData('0','5');
	};

	var Error = function(data, status, headers, config){
		alert("Error");
	};
});