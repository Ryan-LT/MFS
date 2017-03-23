var app = angular.module('landing',['ngRoute']);

app.filter('sizeFormat', function() {
    return function(size) {
        if(size>=925){
        	return (size/1024).toFixed(2)+" Mb";
        } else {
        	return size.toFixed(2) +" Kb";
        }
    };
});

app.controller("landingController", function($scope, $http, $window, $routeParams){
	
	$scope.types = ["All", "Category", "Name", "Uploader", "Size"];
	$scope.Selectedtype = $scope.types[0];
	$scope.filterFile ='';
	$scope.sortReverse = false; 
	  
	$scope.description_ = "";
	$scope.content = "";
	
	$scope.page = 0;
	$scope.pageSum;
	$scope.pageSize = 8;
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
	
    $scope.getInfoFile = function(id){
    	$('#detailModal').modal('show');
    	$http({
			method: 'get',
			url: "http://localhost:8080/file/get/"+id
		}).success(function(data, status, headers, config){
			$scope.fileDetail = data;
			console.log($scope.fileDetail.description);
		})
		.error(function(data, status, headers, config){
			alert("fail__")
		});
    	$http({
			method: 'get',
			url: "http://localhost:8080/download/total/"+id
		}).success(function(data, status, headers, config){
			$scope.total = data;
			
		})
		.error(function(data, status, headers, config){
			alert("fail__")
		});
    	getComment(id);
    }
    
    function getComment(id){
		$http({
			method: 'get',
			url: "http://localhost:8080/comment/getByFile/" + id
		}).success(function(data, status, headers, config){
			$scope.comments = data;
		})
		.error(function(data, status, headers, config){});
	}
    
    $scope.saveComment = function(id){
		$scope.contentComment = {
				content: $scope.content,
		    	idFile: id
		}
		$http.post("http://localhost:8080/comment/saveComment", $scope.contentComment)
		.success(function(data, status, headers, config){
			getComment(id);
			$scope.content = "";
		})
		.error(function(data, status, headers, config){
		});
	}
    
    $scope.saveChange = function(id){
    	if($scope.description_==undefined || $scope.description_ =="" || $scope.description_==null){
    		$scope.description_="No Description";
    	}
    	$scope.file = {
    			id: id,
    			description: $scope.description_
    	}
    	$http({
			method: 'PUT',
			url: "http://localhost:8080/file/updateDescription/",
			data: $scope.file
		}).success(function(data, status, headers, config){
			$scope.msgSave ="Update successful";
			$scope.description_ = "";
		})
		.error(function(data, status, headers, config){
			$scope.msgSave ="Update fail";
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