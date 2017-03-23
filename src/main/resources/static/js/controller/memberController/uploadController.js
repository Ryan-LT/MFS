var app = angular.module('member');
const FileSizeLimit = 1024*1024*100;
var list = [];
var uploadMessage = "";
app.controller('myCtrl', [
		'$scope',
		'fileUpload',
		function($scope, fileUpload) {
			$scope.uploadMessage = "";
			$scope.list = list;
			$scope.uploadFile = function() {
				
				/*var a = document.getElementById("m-toast-blue");
				a.className = "show";
				setTimeout(function() {
					a.className = a.className.replace("show", "");
				}, 3000);*/
				if(list.length==0){
					alert("Please add your file!");
				} else {
					console.log('file is ');
					console.dir(list);
					var uploadUrl = "/upload";
					$scope.uploadMessage = "";
					var success = [];
					var fail = [];
					for (var i = list.length-1; i >=0 ; i--) {
							fileUpload.uploadFileToUrl(list[i], $scope.description,
									uploadUrl).then(
									function(response) {
										if (response.state) {
											$scope.uploadMessage += response.msg
													+ ": Success ";
											success.push(response.msg);						
										} else {
											$scope.uploadMessage += response.msg
													+ ": Fail ";
											fail.push(response.msg);
										}
										var component="";
										if(success.length>0){
											component+="<strong>Successfully uploaded files:</strong><br><ul>";
											for(var i=0;i<success.length;i++){
												component +="<li>"+"- "+success[i]+"</li>";										
											}
											component +="</ul><br>";
										}
										if(fail.length>0){
											component+="<strong>These files have exceeded your rank limit:</strong><br><ul>";
											for(var i=0;i<fail.length;i++){
												component +="<li>"+"- "+fail[i]+"</li>";										
											}
											component +="</ul><br>";
										}
										
										var myHTML = angular.element(document.querySelector('#myNotice'));
										myHTML.html(component);
									});
					}
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
					var notEligibleFile = "";
					for (var i = 0; i < element[0].files.length; i++) {
						
						if(element[0].files[i].size<FileSizeLimit){
							list.push(element[0].files[i]);
						} else {
							listContainTooLargeFile = true;
							notEligibleFile+=element[0].files[i].name+" ";
						}
						
					}
					if(listContainTooLargeFile){
						alert("These files have exceeded the limitation: "+notEligibleFile);
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