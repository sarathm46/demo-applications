(function() {
	'use strict';

	angular.module('jwt-demo', [ 'ngStorage', 'ui.router' ]).run(function($state, Auth, $rootScope) {
		$state.go('login');
		$rootScope.$on('$stateChangeSuccess', function(event, toState, toStateParams) {
			$rootScope.toState = toState;
			$rootScope.toStateParams = toStateParams;
			Auth.authorize();
		});
	});

})();