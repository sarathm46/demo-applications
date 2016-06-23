(function() {
	'use strict';

	angular.module('jwt-demo')//
	.factory('Auth', function($http, $rootScope, $state, $localStorage, $timeout) {
		return {
			login : function(credentials, success, failed) {
				$http.post('/authedicate', {
					username : credentials.username,
					password : credentials.password
				}).success(function(response) {
					$localStorage.currentUser = {
						username : credentials.username,
						token : response.token
					};
					$http.defaults.headers.common.Authorization = 'Bearer ' + response.token;
					if (success) {
						//$timeout(function() {
							success();
						//}, 2000);
					}
				}).error(function(response) {
					if (failed)
						failed();
				})
			},
			logout : function(callback) {
				delete $localStorage.currentUser;
				$http.defaults.headers.common.Authorization = '';
				if (callback)
					callback();

				$rootScope.isAuth = false;
			},
			isAuthendicated : function() {
				if ($localStorage.currentUser) {
					return true;
				} else {
					return false;
				}
			},
			authorize : function() {
				if (!$localStorage.currentUser) {
					if (!($rootScope.toState.url === '/login')) {
						$state.go('login');
					}
				} else {
					$http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.currentUser.token;
					if (($rootScope.toState.url === '/login')) {
						$state.go('home');
					}
				}
			}
		};
	});

})();