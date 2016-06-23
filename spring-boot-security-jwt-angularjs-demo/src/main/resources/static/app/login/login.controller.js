(function() {
	angular.module('jwt-demo').controller('LoginController', function($http, $state, Auth, $rootScope, $scope) {

		$scope.login = function() {
			$scope.dataLoading = true;
			Auth.login({
				username : $scope.username,
				password : $scope.password
			}, function() {
				$state.go('home');
				$scope.dataLoading = false;
			}, function() {
				$scope.error = 'Username and Password are not matching!';
				$scope.dataLoading = false;
			});
		};
	});

})();