var app = angular.module('admin');
app.controller("mainController", function($scope, $http, userDataOp, $routeParams) {
	$scope.selectedIndex = 0;
	$scope.pageSum;
	$scope.page = 0;
	$scope.pageSize = 8;
	getData($scope.page);
	$scope.getNumber = function(num) {
		return new Array(num);
	}
	// Get Data
	
	function getData(page) {
		$http({
					method : 'get',
					url : "http://localhost:8080/user/all/?page=" + parseInt(page)
							+ "&size=" + parseInt($scope.pageSize)
			}).success(function(data, status, headers, config) {
			$scope.page = page;
			$scope.users = data.content;
			$scope.pageSum = data.totalPages;
		}).error(function(data, status, headers, config) {
		});
	}

	$scope.getUserByPage = function(page) {
			getData(parseInt(page), parseInt($scope.pageSize));
			$scope.selectedIndex = page;
	}

	$scope.getUser = function(id) {
		$scope.editBlock = true;
		$scope.addBlock = false;
		$scope.rank_default = "2";
		var id_Rank=null;
		$http({
			method : 'get',
			url : "http://localhost:8080/user/get/" + id
		}).success(function(data, status, headers, config) {
			$scope.user = data;
			id_Rank = data.rank_Id;
			$http({
				method: 'get',
				url: "http://localhost:8080/rank/all"
			}).success(function(data, status, headers, config){
				//alert(data[id_Rank-1].name);
				$scope.rank = { current: ($scope.user.rankId.id-1)+"", names: []};
				for(var i=0; i < data.length;i++){
						$scope.rank.names.push({id: parseInt(i), name: data[i].name});
				}

				
			})
			.error(function(data, status, headers, config){});
		}).error(function(data, status, headers, config) {
		});
	};

	$scope.addUser = function() {
		var user = {
			email : $scope.email,
			password : $scope.password,
			name : $scope.name,
			lastName : $scope.lastName
		};
		userDataOp.addUser(user).then(Success, Error);
		$scope.email = "", $scope.password = "", $scope.name = "",
				$scope.lastName = ""
	};

	$scope.editUser = function(user) {
		//alert($scope.rank.current);
		$scope.rankId = {
		"id": parseInt($scope.rank.current)+1,
		"name": "",
		"sizeupload": 20,
		"sizedownload": 1024,
		"sizerank": 100
		}
		user.rankId = $scope.rankId;//parseInt($scope.rank.current)+1;
		console.log(parseInt($scope.rank.current)+1);
		userDataOp.editUser(user).then(Success, Error);
	};

	$scope.deleteUser = function(id) {
		userDataOp.deleteUser(id).then(Success, Error);
	};

	// Exception Handling
	var Success = function(data, status, headers, config) {
		getData($scope.page);
	};

	var Error = function(data, status, headers, config) {
		alert("Error");
	};
});