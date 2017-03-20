var app = angular.module('myWeb');

app.controller('fileManage', function($scope, $http, $window){
    //this.files = storage;
	
	$scope.count = 10;
    getFiles(0, 10);
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
    
    function getFiles(page, pageSize) { 
		$http({
			method: 'get',
			url: "http://localhost:8080/file/getByUser/"+$scope.userId+"?page="+ parseInt(page)+
												"&size="+ parseInt(pageSize)
		}).success(function(data, status, headers, config){
			$scope.files = data;
		})
		.error(function(data, status, headers, config){});
	}
    
    $scope.getFileByPage = function(page, pageSize){
    	getFiles(parseInt(page), parseInt(pageSize));
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
		})
		.error(function(data, status, headers, config){
			$scope.msgSave ="Update fail";
		});
    }
	
});