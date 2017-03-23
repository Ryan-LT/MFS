var app = angular.module('member');

app.controller('fileManage', function($scope, $http, $window){
    //this.files = storage;
	
	$scope.description_ = "";
	
	$scope.content = "";
	$scope.pageSize = 8;
	$scope.page = 0;
    getFiles(0, 8);
    $scope.getNumber = function(num) {
        return new Array(num);
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
	
	$scope.getFileByPage = function(page) {
		getFiles(parseInt(page), parseInt($scope.pageSize));
		$scope.page = (parseInt(page));
	}
    
    function getFiles(page) { 
		$http({
			method: 'get',
			url: "http://localhost:8080/file/getByUser/"+$scope.userId+"?page="+ parseInt(page)+
												"&size="+ parseInt($scope.pageSize)
		}).success(function(data, status, headers, config){
			$scope.files = data;
			$scope.page = page;
		})
		.error(function(data, status, headers, config){});
	}
    
    $scope.getFileByPage = function(page){
    	getFiles(parseInt(page), parseInt($scope.pageSize));
    }
    
    $scope.deleteFile = function(id){
    	if(confirm("Are you sure ?")){
	    	$http({
				method: 'get',
				url: "http://localhost:8080/file/delete/"+id
			}).success(function(data, status, headers, config){
				getFiles(0, 10);
			})
			.error(function(data, status, headers, config){
				alert("fail");
			});
    	}
    }
    $scope.changeShare = function(idFile){
    	$http({
			method: 'get',
			url: "http://localhost:8080/file/updateSharing/"+idFile
		}).success(function(data, status, headers, config){
		})
		.error(function(data, status, headers, config){
		});
    }
    
    
    $scope.getInfoFile = function(id){
    	$('.modal').modal('show');
    	$http({
			method: 'get',
			url: "http://localhost:8080/file/get/"+id
		}).success(function(data, status, headers, config){
			$scope.fileDetail = data;
			$scope.description_ = data.description;
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
	
});


app.filter('sizeFormat', function() {
    return function(size) {
        if(size>=925){
        	return (size/1024).toFixed(2)+" Mb";
        } else {
        	return size.toFixed(2) +" Kb";
        }
    };
});