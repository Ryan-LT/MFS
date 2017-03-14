var app = angular.module('admin');
app.controller("mainController", function($scope, $http, userDataOp,
		$routeParams) {

	$scope.pageSum;
	$scope.page;
	getData('0', '5');
	countUser();
	$scope.getNumber = function(num) {
		return new Array(num);
	}
	// Get Data
	
	$scope.getRankByID = function(rankId) {
		$http({
			method : 'get',
			url : "http://localhost:8080/rank/get/" + rankId
		}).success(function(data, status, headers, config) {
			$scope.rankInfor = data;
			alert(rankInfor.name)
		}).error(function(data, status, headers, config) {
		});
	}
	
	function getData(page, pageSize) {
		$http(
				{
					method : 'get',
					url : "http://localhost:8080/user/all/" + parseInt(page)
							+ "/" + parseInt(pageSize)
				}).success(function(data, status, headers, config) {
			$scope.users = data.content;
		}).error(function(data, status, headers, config) {
		});
	}

	function countUser() {
		$http({
			method : 'get',
			url : "http://localhost:8080/user/countUser/"
		}).success(function(data, status, headers, config) {
			$scope.pageSum = Math.ceil(data / 5);
		}).error(function(data, status, headers, config) {
		});
	}

	$scope.getUserByPage = function(page, pageSize) {
			getData(parseInt(page), parseInt(pageSize));
			$scope.page = (parseInt(page));
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
				$scope.rank = { current: (id_Rank-1)+"", names: []};
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
		user.rank_Id = parseInt($scope.rank.current)+1;
		userDataOp.editUser(user).then(Success, Error);
	};

	$scope.deleteUser = function(id) {
		userDataOp.deleteUser(id).then(Success, Error);
	};

	// Exception Handling
	var Success = function(data, status, headers, config) {
		getData('0', '5');
	};

	var Error = function(data, status, headers, config) {
		alert("Error");
	};
});