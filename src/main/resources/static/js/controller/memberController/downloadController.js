var app = angular.module('myWeb');

app.controller("download", function($scope, $http){
	$scope.count = 0;
	getHistory(0,10);
	function getHistory(page, pageSize){
		$http({
			method: 'get',
			url: "http://localhost:8080/download/getByUser/" + $scope.userId + "?page=" + parseInt(page)
																		+ "&size=" + parseInt(pageSize)
		}).success(function(data, status, headers, config){
			$scope.files = data;
		})
		.error(function(data, status, headers, config){});	
	}
	
	$scope.getNumber = function(num) {
        return new Array(num);
    }
	
    $scope.getDownloadByPage = function(page, pageSize){
    	getHistory(parseInt(page), parseInt(pageSize));
    }
    
    $scope.getInfoFile = function(id){
    	$('.modal').modal('show');
    	alert(id);
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
			$scope.content="";
		})
		.error(function(data, status, headers, config){
			alert("fail");
		});
	}
});