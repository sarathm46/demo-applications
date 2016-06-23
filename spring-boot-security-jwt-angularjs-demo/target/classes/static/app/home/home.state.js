(function() {
	'use strict';
	angular.module('jwt-demo').config(function($stateProvider) {
		$stateProvider.state('home', {
			parent : 'app',
			url : '/',
			data : {
				authorities : [ 'ADMIN', 'USER' ]
			},
			views : {
				'content@' : {
					templateUrl : 'app/home/home.html',
					controller : 'HomeController',
					controllerAs : 'vm'
				}
			}
		});
	});
})();