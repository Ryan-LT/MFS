var app = angular.module('member');
app.controller('myCtrl', [
		'$scope',
		'fileUpload',
		function($scope, fileUpload) {
			$scope.uploadMessage = "";
			$scope.list = list;
			console.log("Runned");
			$scope.uploadFile = function() {
				
				var a = document.getElementById("m-toast-blue");
				a.className = "show";
				setTimeout(function() {
					a.className = a.className.replace("show", "");
				}, 3000);
				
				console.log('file is ');
				console.dir(list);
				var uploadUrl = "/upload";
				$scope.uploadMessage = "";
				for (var i = list.length-1; i >=0 ; i--) {
						fileUpload.uploadFileToUrl(list[i], $scope.description,
								uploadUrl).then(
								function(response) {
									if (response.state) {
										console.log(response.msg);
										$scope.uploadMessage += response.msg
												+ ": Success ";
									} else {
										$scope.uploadMessage += response.msg
												+ ": Fail ";
									}
								});
				}
				
			} 			
			$scope.removeFile = function(index) {
				$scope.list.splice(index, 1);
				console.log(list);
			};
		} ]);


app.directive('fileModel', [ '$parse', function($parse) {
	return {
		restrict : 'A',
		link : function(scope, element, attrs) {
			var model = $parse(attrs.fileModel);
			var modelSetter = model.assign;
			element.bind('change', function() {
				scope.$apply(function() {
					// modelSetter(scope, element[0]);
					list.length = 0;
					var listContainTooLargeFile = false;
					for (var i = 0; i < element[0].files.length; i++) {
						var notEligibleFile = "";
						if(element[0].files[i].size<1024*1024*100){
							list.push(element[0].files[i]);
						} else {
							listContainTooLargeFile = true;
							notEligibleFile+=element[0].files[i].name+" ";
						}
						if(listContainTooLargeFile){
							alert("These files have exceeded the limitation: "+notEligibleFile);
						}											
					}
				});
			});
		}
	};
}]);

app.factory('fileUpload', [ '$http', function($https, $p) {
	return {
		uploadFileToUrl : function(file, description, uploadUrl) {
			var fd = new FormData();
			fd.append('file', file);
			fd.append('description', description);
			return $https.post(uploadUrl, fd, {
				transformRequest : angular.identity,
				headers : {
					'Content-Type' : undefined
				}

			}).then(function(response) {
				return response.data;
			});
		}
	};
}]);
var list = [];
var uploadMessage = "";