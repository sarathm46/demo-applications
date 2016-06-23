(function() {
	'use strict';
	angular.module('jwt-demo').config(function($stateProvider) {
		$stateProvider.state('login', {
			parent : 'app',
			url : '/login',
			views : {
				'content@' : {
					templateUrl : 'app/login/login.html',
					controller : 'LoginController',
					controllerAs : 'vm'
				}
			}
		});
	});
})();