var app = angular.module("addressBook", [ "ui.router" ]);

app.config(function($stateProvider, $urlRouterProvider) {
	$urlRouterProvider.otherwise("/addressBookList");
	$stateProvider.state("home", {
		url : "/addressBookList",
		templateUrl : "/address-book/app/addressBookList",
		controller : "listAddressController"
	}).state("addAddress", {
		url : "/addAddress",
		templateUrl : "/address-book/app/addAddress",
		controller : "addAdressController"
	}).state("settings", {
		url : "/settings",
		templateUrl : "/address-book/app/settings",
		controller : "settingsController"
	}).state("addUser", {
		url : "/addUser",
		templateUrl : "/address-book/app/addUser",
		controller : "addUserController"
	}).state("listUser", {
		url : "/listUser",
		templateUrl : "/address-book/app/listUser",
		controller : "listUserController"
	}).state("editUser", {
		url : "/editUser/:id",
		templateUrl : "/address-book/app/editUser",
		controller : "editUserController"
	}).state("editAddress", {
		url : "/editAddress/:id",
		templateUrl : "/address-book/app/editAddress",
		controller : "editAddressController"
	});
});
app.controller("settingsController", function($scope) {
});
app.controller("editUserController",
		function($scope, $stateParams, $http, $log) {

			$http.get("/address-book/app/rest/user/" + $stateParams.id).then(
					function(response) {
						$scope.newName = response.data.name;
						$scope.newUsername = response.data.username;
						$scope.newPassword = response.data.password;
					}, function(response) {
					});
			$scope.submitUser = function() {
				var user = {
					id : $stateParams.id,
					name : $scope.newName,
					username : $scope.newUsername,
					password : $scope.newPassword
				};
				$http.post("/address-book/app/rest/user", user).then(
						function(response) {
							console.log(response);
							if (response.status == 200) {
								showMessage("Success",
										"Successfully updated the User.");
							}
						}, function(response) {
							console.log(response);
						});
			}
		});

app.controller("editAddressController", function($scope, $stateParams, $http,
		$log) {

	$http.get("/address-book/app/rest/address/" + $stateParams.id).then(function(response) {
		$scope.newName = response.data.name;
		$scope.newPhoneNumber = response.data.phoneNumber;
		$scope.newAddress = response.data.address;

	}, function(response) {
	});
	$scope.submitUser = function() {
		var address = {
			id : $stateParams.id,
			name : $scope.newName,
			phoneNumber : $scope.newPhoneNumber,
			address : $scope.newAddress
		};
		$http.post("/address-book/app/rest/user", address).then(function(response) {
			console.log(response);
			if (response.status == 200) {
				showMessage("Success", "Successfully updated the Address.");
			}
		}, function(response) {
			console.log(response);
		});
	}
});

app
		.controller(
				"addUserController",
				function($scope, $http) {
					$scope.newName = "";
					$scope.newUsername = "";
					$scope.newPassword = "";
					$scope.submitUser = function() {
						var user = {
							name : $scope.newName,
							username : $scope.newUsername,
							password : $scope.newPassword
						};

						$http
								.post("/address-book/app/rest/duplicatecheck", user)
								.then(
										function(response) {
											console.log(response);
											if (!response.data.duplicate) {
												$http
														.post("/address-book/app/rest/user",
																user)
														.then(
																function(
																		response) {
																	console
																			.log(response);
																	if (response.status == 200) {
																		showMessage(
																				"Success",
																				"Successfully added the Address.");
																		$scope.newName = "";
																		$scope.newUsername = "";
																		$scope.newPassword = "";
																	}
																},
																function(
																		response) {
																	console
																			.log(response);
																});
											} else {
												showMessage(
														"User Creation",
														"The the user name '"
																+ $scope.newUsername
																+ "' is already exist.");
											}
										}, function(response) {
											console.log(response);
										});

					}
				});
app.controller("listAddressController", function($scope, $http) {
	$http.get("/address-book/app/rest/address").then(function(response) {
		console.log(response);
		$scope.addressBookList = response.data;
	}, function(response) {
		console.log(response);
	});

	$scope.deleteAddress = function(id) {
		confirm("Confirm", "Are you sure to delete ?", function() {
			$http.post("/address-book/app/rest/address/" + id).then(function(response) {
				$http.get("/address-book/app/rest/address").then(function(response) {
					console.log(response);
					$scope.addressBookList = response.data;
				}, function(response) {
					console.log(response);
				});
			}, function(response) {
			});
		}, function() {
		});
	}
});

app.controller("listUserController", function($scope, $http) {
	$http.get("/address-book/app/rest/users").then(function(response) {
		console.log(response);
		$scope.users = response.data;
	}, function(response) {
		console.log(response);
	});

	$scope.deleteAppUser = function(id) {
		confirm("Confirm", "Are you sure to delete ?", function() {
			$http.post("/address-book/app/rest/user/" + id).then(function(response) {
				$http.get("/address-book/app/rest/users").then(function(response) {
					console.log(response);
					$scope.users = response.data;
				}, function(response) {
					console.log(response);
				});
			}, function(response) {
			});
		}, function() {
		});
	}
});
app.controller("addAdressController", function($scope, $http) {
	$scope.newName = "";
	$scope.newPhoneNumber = "";
	$scope.newAddress = "";
	$scope.submitAddress = function() {
		var address = {
			email : $scope.newEmail,
			name : $scope.newName,
			phoneNumber : $scope.newPhoneNumber,
			address : $scope.newAddress
		};
		$http.post("/address-book/app/rest/address", address).then(function(response) {
			console.log(response);
			if (response.status == 200) {

				showMessage("Success", "Successfully added the User.");
				$scope.newName = "";
				$scope.newEmail = "";
				$scope.newPhoneNumber = "";
				$scope.newAddress = "";
			}

		}, function(response) {
			console.log(response);
		});
	};

});

function confirm(title, message, yes, no) {
	$('#modal-title').text(title);
	$('#modal-m_message_confirm').text(title);
	$('#confirm').modal({
		backdrop : 'static',
		keyboard : false
	}).one('click', '#_yes', function() {
		if (yes)
			yes();
	}).one('click', '#_no', function() {
		if (no)
			no();
	});
}
function showMessage(title, message) {
	$("#m_title").text(title);
	$("#m_message").text(message);
	$("#messageModel").modal()
}