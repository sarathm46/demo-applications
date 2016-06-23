(function() {

	angular.module('jwt-demo')//
	.controller('NavController', function($http, $scope, Auth, $state, $rootScope) {
		$scope.authedicated = function() {
			return Auth.isAuthendicated();
		};
		$scope.logout = function() {
			Auth.logout(function() {
				$state.go('login');
			});
		};
	});
})();