var myApp = angular.module("myApp", []);
var urlPrefix = "/google-contacts-app";
//var urlPrefix = "";

myApp
		.controller(
				"appController",
				function($scope, $http, $filter) {
					$scope.searchText = "";
					$scope.searchEmail = "";
					$scope.searchPhoneNumber = "";

					$scope.gap = 5;
					$scope.contacts = [];
					$scope.contactsSearchResult = [];
					$scope.filteredItems = [];
					$scope.groupedItems = [];
					$scope.itemsPerPage = 5;
					$scope.pagedItems = [];
					$scope.currentPage = 0;
					$scope.pagevalues = [];

					var i = 0;
					for (i = 5; i <= 100; i++) {
						$scope.pagevalues.push(i);
					}

					function ifEmpty(obj) {
						if (!obj)
							return "";
						return obj;
					}

					$scope.clear = function() {
						$scope.searchText = "";
						$scope.searchEmail = "";
						$scope.searchPhoneNumber = "";
					};

					$scope.searchSearchText = function(value, searchText) {
						$scope.contactsSearchResult = $filter('filter')
								(
										$scope.contacts,
										function(item) {

											if (item[value] == null) {
												item[value] = "";
											}
											var name = item[value]
													.toLowerCase();
											if (name.search(searchText
													.toLowerCase()) != -1)
												return true;
											return false;
										});

						$scope.search();
					};

					$scope.searchSearchAll = function() {
						$scope.contactsSearchResult = $filter('filter')
								(
										$scope.contacts,
										function(item) {

											var name = ifEmpty(
													item.fullNameToDisplay)
													.toLowerCase();

											var email = "";
											if (item.googleEmails.length > 0) {
												email = item.googleEmails[0].address;
												email = email.toLowerCase();
											}

											var phoneNumber = "";
											if (item.phoneNubers.length > 0) {
												phoneNumber = item.phoneNubers[0].phoneNumber;
												phoneNumber = phoneNumber
														.toLowerCase();
											}

											if (name.search(($scope.searchText
													.toLowerCase())) != -1
													&& email
															.search(ifEmpty($scope.searchEmail
																	.toLowerCase())) != -1
													&& phoneNumber
															.search(ifEmpty($scope.searchPhoneNumber
																	.toLowerCase())) != -1)
												return true;

											return false;
										});

						$scope.search();
					};

					$scope.$watch('itemsPerPage', function() {
						$scope.searchSearchAll();
					});

					$scope.$watch('searchText', function() {
						$scope.searchSearchAll();
					});

					$scope.$watch('searchEmail', function() {
						console.log($scope.searchEmail);
						$scope.searchSearchAll();
					});
					$scope.$watch('searchPhoneNumber', function() {
						console.log($scope.searchPhoneNumber);
						$scope.searchSearchAll();
					});

					$scope.loadContacts = function() {
						$http.get(urlPrefix + "/app/rest/contacts").success(
								function(data) {
									console.log(data);
									$scope.contacts = data;

									// $scope.search();
									// console.log(JSON.stringify(data));
									// $scope.search();
									$scope.searchSearchAll();
									toastr.success("Successfully imported "+data.length+" contacts from google !");
								});
					};

					$scope.loadUser = function() {
						$http.get(urlPrefix + "/app/rest/user").success(
								function(data) {
									console.log(data);
								});
					};

					$scope.sort = {
						sortingOrder : 'id',
						reverse : false
					};

					// init the filtered contacts
					$scope.search = function() {
						$scope.filteredItems = $filter('filter')(
								$scope.contactsSearchResult,
								function(item) {
									for ( var attr in item) {
										if (searchMatch(item[attr],
												$scope.query))
											return true;
									}
									return false;
								});
						// console.log($scope.filteredItems);
						// take care of the sorting order
						if ($scope.sort.sortingOrder !== '') {
							$scope.filteredItems = $filter('orderBy')(
									$scope.filteredItems,
									$scope.sort.sortingOrder,
									$scope.sort.reverse);
						}
						$scope.currentPage = 0;
						// now group by pages
						$scope.groupToPages();
					};

					var searchMatch = function(haystack, needle) {
						if (!needle) {
							return true;
						}
						return haystack.toLowerCase().indexOf(
								needle.toLowerCase()) !== -1;
					};

					// init the filtered items
					$scope.search = function() {
						$scope.filteredItems = $filter('filter')(
								$scope.contactsSearchResult,
								function(item) {
									for ( var attr in item) {
										if (searchMatch(item[attr],
												$scope.query))
											return true;
									}
									return false;
								});
						// take care of the sorting order
						if ($scope.sort.sortingOrder !== '') {
							$scope.filteredItems = $filter('orderBy')(
									$scope.filteredItems,
									$scope.sort.sortingOrder,
									$scope.sort.reverse);
						}
						$scope.currentPage = 0;
						// now group by pages
						$scope.groupToPages();
					};

					// calculate page in place
					$scope.groupToPages = function() {
						$scope.pagedItems = [];
						// console.log($scope.filteredItems);
						for (var i = 0; i < $scope.filteredItems.length; i++) {
							if (i % $scope.itemsPerPage === 0) {
								$scope.pagedItems[Math.floor(i
										/ $scope.itemsPerPage)] = [ $scope.filteredItems[i] ];
							} else {
								$scope.pagedItems[Math.floor(i
										/ $scope.itemsPerPage)]
										.push($scope.filteredItems[i]);
							}
						}
					};

					$scope.range = function(size, start, end) {
						var ret = [];
						// console.log(size, start, end);

						if (size < end) {
							end = size;
							start = size - $scope.gap;
						}
						for (var i = start; i < end; i++) {
							if (i >= 0)
								ret.push(i);
						}
						// console.log(ret);
						return ret;
					};

					$scope.prevPage = function() {
						if ($scope.currentPage > 0) {
							$scope.currentPage--;
						}
					};

					$scope.nextPage = function() {
						if ($scope.currentPage < $scope.pagedItems.length - 1) {
							$scope.currentPage++;
						}
					};

					$scope.setPage = function() {
						$scope.currentPage = this.n;
					};

					// functions have been describe process the data for display
					// $scope.search();
				});

myApp.directive('errSrc', function() {
	return {
		link : function(scope, element, attrs) {
			element.bind('error', function() {
				if (attrs.src != attrs.errSrc) {
					attrs.$set('src', attrs.errSrc);
				}
			});
		}
	}
});

myApp.$inject = [ '$scope', '$filter' ];

myApp.directive("customSort", function() {
	return {
		restrict : 'A',
		transclude : true,
		scope : {
			order : '=',
			sort : '='
		},
		template : ' <a ng-click="sort_by(order)" style="color: #555555;">'
				+ '    <span ng-transclude></span>'
				+ '    <i ng-class="selectedCls(order)"></i>' + '</a>',
		link : function(scope) {

			// change sorting order
			scope.sort_by = function(newSortingOrder) {
				var sort = scope.sort;

				if (sort.sortingOrder == newSortingOrder) {
					sort.reverse = !sort.reverse;
				}

				sort.sortingOrder = newSortingOrder;
			};

			scope.selectedCls = function(column) {
				if (column == scope.sort.sortingOrder) {
					return ('icon-chevron-' + ((scope.sort.reverse) ? 'down'
							: 'up'));
				} else {
					return 'icon-sort'
				}
			};
		}// end link
	}
});