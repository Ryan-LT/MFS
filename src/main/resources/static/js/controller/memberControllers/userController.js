var app = angular.module("myWeb", ["ngRoute"]);

app.config(function($routeProvider) {
  $routeProvider
  .when("/userInfo", {
    templateUrl: "/views/member/userInfo.html"
  })
  .when("/fileManage", {
    templateUrl: '/views/member/fileManage.html',
    controller: "PersonStorageController"
  })
  .when("/upload", {
    templateUrl: "upload.html"
  })
});

app.controller('PersonStorageController', function($scope, $http){
    //this.files = storage;
    getData();
    function getData() { 
		$http({
			method: 'get',
			url: "http://localhost:8080/file/getByUser/8"
		}).success(function(data, status, headers, config){
			$scope.files = data;
		})
		.error(function(data, status, headers, config){
			alert("fail");
		});
	}
    
    $scope.deleteFile = function(id){
    	$http({
			method: 'get',
			url: "http://localhost:8080/file/delete/"+id
		}).success(function(data, status, headers, config){
			getData();
		})
		.error(function(data, status, headers, config){
			alert("fail");
		});
    }
    
  });

































var storage = [
  {
    fileName: "untilyou.mp3",
    size: "1MB",
    updatedDate: "20/05/2026",
  },
  {
    fileName: "untilyou2.mp3",
    size: "1MB",
    updatedDate: "20/05/2026",
  },
  {
    fileName: "untilyou3.mp3",
    size: "1MB",
    updatedDate: "20/05/2026",
  },
  {
    fileName: "untilyou4.mp3",
    size: "1MB",
    updatedDate: "20/05/2026",
  },
  {
    fileName: "untilyou5.mp3",
    size: "1MB",
    updatedDate: "20/05/2026",
  },
  {
    fileName: "untilyou6.mp3",
    size: "1MB",
    updatedDate: "20/05/2026",
  },
];

app.controller('AllStorageController', function(){
    this.files = storage;
  });

var storage = [
  {
    fileName: "untilyou.mp3",
    size: "1MB",
    updatedDate: "20/05/2026",
    updatedBy: "abc",
  },
  {
    fileName: "untilyou2.mp3",
    size: "1MB",
    updatedDate: "20/05/2026",
    updatedBy: "abc",
  },
  {
    fileName: "untilyou3.mp3",
    size: "1MB",
    updatedDate: "20/05/2026",
    updatedBy: "abc",
  },
  {
    fileName: "untilyou4.mp3",
    size: "1MB",
    updatedDate: "20/05/2026",
    updatedBy: "abc",
  },
  {
    fileName: "untilyou5.mp3",
    size: "1MB",
    updatedDate: "20/05/2026",
    updatedBy: "abc",
  },
  {
    fileName: "untilyou6.mp3",
    size: "1MB",
    updatedDate: "20/05/2026",
    updatedBy: "abc",
  },
];