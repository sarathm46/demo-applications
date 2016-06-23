(function() {
	'use strict';
	angular.module('jwt-demo').config(function($stateProvider, $urlRouterProvider) {
		//$urlRouterProvider.otherwise('404');
		$stateProvider.state('404', {
			parent : 'app',
			url : '/404',
			views : {
				'content@' : {
					templateUrl : 'app/404/404.html',
					controller : 'NavController',
					controllerAs : 'vm'
				}
			}
		});
	});
})();