var app = angular.module('member');
app.controller('myCtrl', [
		'$scope',
		'fileUpload',
		function($scope, fileUpload) {
			$scope.uploadMessage = "";
			$scope.list = list;
			$scope.uploadFile = function() {
				console.log('file is ');
				console.dir(list);
				var uploadUrl = "/upload";
				$scope.uploadMessage = "";
				for (var i = 0; i < list.length; i++) {
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
			};
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
					for (var i = 0; i < element[0].files.length; i++) {
						list.push(element[0].files[i]);
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