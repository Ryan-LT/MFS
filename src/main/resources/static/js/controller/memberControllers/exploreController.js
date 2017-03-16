var app = angular.module('myWeb');

app.controller("explore", function($http, $scope, $window, $location, $window){
	$scope.search="";
	getBestDownload("");
	function getBestDownload(info){
		$http({
			method: 'get',
			url: "http://localhost:8080/file/getBestDownload/"
		}).success(function(data, status, headers, config){
			$scope.files = data;
		})
		.error(function(data, status, headers, config){
			alert("fail");
		});	
	}
	$scope.getOwner = function(id){
		$http({
			method: 'get',
			url: "http://localhost:8080/user/get/"+id
		}).success(function(data, status, headers, config){
			return data.name;
		})
		.error(function(data, status, headers, config){
			alert("fail");
		});	
	}
	
	$scope.doSearch = function(page, pageSize){
		$scope.search = $scope.infoSearch;
		countSearch();
		searchFiles(page, pageSize);
		
	};//countSearch
	$scope.infoTemp = $scope.search;
	function searchFiles(page, pageSize){
		$scope.search = $scope.infoSearch;
		countSearch();
		$http({
			method: 'get',
			url: "http://localhost:8080/file/fSearch/" + $scope.infoSearch + "/"+ page +"/"+ pageSize
		}).success(function(data, status, headers, config){
			$scope.files = data;
		})
		.error(function(data, status, headers, config){});
	}
	
	$scope.getDownloadByPage = function(page, pageSize){
		searchFiles(page, pageSize);
    }
	
	$scope.getNumber = function(num) {
        return new Array(num);
    }
	
	function countSearch() { 
		$http({
			method: 'get',
			url: "http://localhost:8080/file/countSearch/" + $scope.infoSearch
		}).success(function(data, status, headers, config){
			$scope.count = Math.round(data/2);
		})
		.error(function(data, status, headers, config){
			alert("fail");
		});
	}
	
	//http://localhost:8080/download/files/10
	$scope.downloadFile = function(idFile){
			$http({
				method: 'get',
				url: "http://localhost:8080/download/check/" + idFile
			}).success(function(data, status, headers, config){
				alert(data);
				if(data<=0){
					$window.location.href = 'http://localhost:8080/download/files/10';
				} else {
					alert("bạn đã hết dung lượng download hôm nay!");
				}
			})
			.error(function(data, status, headers, config){
				alert("fail");
			});		
	}	
	
});



angular.module('anchorScrollOffsetExample', [])
.run(['$anchorScroll', function($anchorScroll) {
  $anchorScroll.yOffset = 50;   // always scroll by 50 extra pixels
}])
.controller('headerCtrl', ['$anchorScroll', '$location', '$scope',
  function($anchorScroll, $location, $scope) {
    $scope.gotoAnchor = function(x) {
      var newHash = 'anchor' + x;
      if ($location.hash() !== newHash) {
        // set the $location.hash to `newHash` and
        // $anchorScroll will automatically scroll to it
        $location.hash('anchor' + x);
      } else {
        // call $anchorScroll() explicitly,
        // since $location.hash hasn't changed
        $anchorScroll();
      }
    };
  }
]);