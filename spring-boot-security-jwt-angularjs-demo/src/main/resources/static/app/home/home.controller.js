(function() {
	angular.module('jwt-demo').controller('HomeController', function($http, $state, Auth, $scope) {
		$scope.dataLoading = true;
		$http.get('api/user').success(function(rest) {
			$scope.user = rest;
			$scope.dataLoading = false;
		}).error(function(rest) {
		});
	});
})();