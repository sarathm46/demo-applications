(function() {
	'use strict';
	angular.module('jwt-demo').config(function($stateProvider) {
		$stateProvider.state('app', {
			abstract : true,
			views : {
				'nav@' : {
					templateUrl : 'app/nav/nav.html',
					controller : 'NavController',
					controllerAs : 'vm'
				}
			}
		});
	});
})();