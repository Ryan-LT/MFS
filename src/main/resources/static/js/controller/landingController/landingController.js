var app = angular.module('landing',['ngRoute']);
app.controller("landingController", function($scope, $http, $window, $routeParams){
	
	$scope.types = ["All", "Category", "Name", "Uploader", "Size"];
	$scope.Selectedtype = $scope.types[0];
	$scope.filterFile ='';
	$scope.sortReverse = false; 
	  
	$scope.page = 0;
	$scope.pageSum;
	$scope.pageSize = 10;
	$scope.selectedIndex = 0;
	$scope.infoSearch = undefined;
	
	
	$scope.getNumber = function(num) {
		return new Array(num);
	}
	
	searchFile($scope.Selectedtype);
	
	$scope.searchFile = function(Selectedtype, infoSearch, page, pageSize) {
		searchFile(Selectedtype, infoSearch, page, $scope.pageSize);
		$scope.Selectedtype = Selectedtype;
		$scope.infoSearch = infoSearch;
		$scope.page = page;
	}
	
	function searchFile(Selectedtype, infoSearch, page, pageSize) {
		$http({
			method : 'get',
			url : "http://localhost:8080/file/find/" + Selectedtype + "/" + infoSearch + "/?page=" + parseInt(page)
			+ "&size=" + parseInt($scope.pageSize)
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
//
	};

	var Error = function(data, status, headers, config){
		alert("Error");
	};
});

app.directive('emptyToNull', function () {
    return {
        restrict: 'A',
        require: 'ngModel',
        link: function (scope, elem, attrs, ctrl) {
            ctrl.$parsers.push(function(viewValue) {
                if(viewValue === "") {
                    return undefined;
                }
                return viewValue;
            });
        }
    };
});