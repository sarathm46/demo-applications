<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Home</title>
<meta name="description" content="" />
<meta name="viewport" content="width=device-width" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/webjars/bootstrap/css/bootstrap.min.css" />" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/webjars/font-awesome/4.5.0/css/font-awesome.css"/>" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/commons.css" />">

<link rel="stylesheet"
	href="<c:url value="/resources/lib/toastr/css/toastr.min.css" />">

<script type="text/javascript"
	src="<c:url value="/webjars/jquery/jquery.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/webjars/bootstrap/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/lib/pace/pace.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/webjars/angularjs/angular.min.js"/>"></script>
<script type="text/javascript">
	window.paceOptions = {
		document : true,
		eventLag : true,
		restartOnPushState : true,
		restartOnRequestAfter : true,
		ajax : {
			trackMethods : [ "POST", "GET" ]
		}

	};
</script>

<script src="<c:url value="/resources/lib/toastr/js/toastr.js"/>"></script>
<script src="<c:url value="/resources/js/myApp.js"/>"></script>
</head>
<body ng-app="myApp" ng-controller="appController">

	<nav class="navbar navbar-default navbar-static-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Google Contacts</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<c:url value="/app/logout" var="logout" />

					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false"><img
							alt="${appUser.name }" src="${appUser.imageUrl }"
							style="width: 30px; height: auto; margin: 0px; padding: 0px;"
							class="profile-image img-circle" />${appUser.username }<span
							class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="${logout }">Logout</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<h4>
					Welcome <b>${appUser.name }</b>
				</h4>
				<div ng-show="!contacts.length">
					<a class="btn btn-info" ng-click="loadContacts()"> Import
						Contacts from Google</a>
					<div style="height: 50px;"></div>
				</div>

				<div class="row" ng-show="contacts.length">
					<div class="col-md-6"></div>
					<div class="col-md-6">
						<div class="input-group" id="adv-search"
							style="margin-right: 0px;">
							<input type="text" class="form-control" ng-model="searchText"
								placeholder="Search for Names" />
							<div class="input-group-btn">
								<div class="btn-group" role="group">
									<div class="dropdown dropdown-lg">
										<div class="dropdown-menu dropdown-menu-right" role="menu">
											<form class="form-horizontal" role="form">
												<div class="form-group">
													<label for="contain">Name</label> <input
														ng-model="searchText" class="form-control" type="text" />
												</div>
												<div class="form-group">
													<label for="contain">Email</label> <input
														class="form-control" type="text" ng-model="searchEmail" />
												</div>
												<div class="form-group">
													<label for="contain">Phone Number</label> <input
														class="form-control" type="text"
														ng-model="searchPhoneNumber" />
												</div>
												<div class="form-group">
													<a class="btn btn-info" ng-click="clear()"
														style="border-radius: 4px">Clear</a> <a
														class="btn btn-success" data-toggle="dropdown"
														style="border-radius: 4px">Close</a>
												</div>
											</form>
										</div>

										<button type="button" class="btn btn-primary dropdown-toggle"
											data-toggle="dropdown" aria-expanded="false">
											<span class="glyphicon glyphicon-search" aria-hidden="true">
										</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div style="height: 20px;"></div>
		<div class="row" ng-show="contacts.length">
			<div class="col-md-12">
				<div class="panel panel-default table-responsive">
					<table class="table table-bordered">
						<thead>

							<td colspan="4">
								<div>
									<div class="form-group pull-left">
										<label for="contain">Item Count</label> <select
											class="form-control" ng-model="itemsPerPage"
											ng-options="item for item in pagevalues"></select>
									</div>
									<ul class="pagination pull-right">
										<li ng-class="{disabled: currentPage == 0}"><a href
											ng-click="prevPage()">« Prev</a></li>

										<li
											ng-repeat="n in range(pagedItems.length, currentPage, currentPage + gap) "
											ng-class="{active: n == currentPage}" ng-click="setPage()">
											<a href ng-bind="n + 1">1</a>
										</li>

										<li
											ng-class="{disabled: (currentPage) == pagedItems.length - 1}">
											<a href ng-click="nextPage()">Next »</a>
										</li>
									</ul>
								</div>
							</td>

							<tr>
								<th>#</th>
								<th class="fullNameToDisplay" custom-sort order=""
									fullNameToDisplay"" sort="sort">Name</th>
								<th class="address" custom-sort order=""
									googleEmails[0].address"" sort="sort">Email</th>
								<th class="phoneNumber" custom-sort order=""
									phoneNubers[0].phoneNumber"" sort="sort">Phone Number</th>
							</tr>
						</thead>
						<tbody>
							<tr
								ng-repeat="contact in pagedItems[currentPage] | orderBy:sort.sortingOrder:sort.reverse">
								<td>{{$index+1}}</td>
								<td><img ng-src="{{contact.photoLink}}"
									err-src="<c:url value="/resources/img/user-img.png"/>"
									class="img-circle" style="width: 40px; height: auto;" /><span
									style="line-height: 40px; margin-left: 5px">{{contact.fullNameToDisplay}}</span></td>
								<td>
									<ul class="list-group my-ul">
										<li class="list-group-item list-group-item-success"
											ng-repeat="email in contact.googleEmails"><span
											class="fa fa-envelope" style="margin-right: 5px"></span>{{email.address}}</li>
									</ul>
								</td>
								<td>
									<ul class="list-group my-ul">
										<li class="list-group-item list-group-item-info"
											ng-repeat="phoneNumber in contact.phoneNubers"><span
											class="fa fa-phone" style="margin-right: 5px"></span>{{phoneNumber.phoneNumber}}</li>
									</ul>
								</td>
							</tr>
						</tbody>

						<tfoot ng-show="contacts.length">
							<td colspan="4">
								<div>
									<div class="form-group pull-left">
										<label for="contain">Item Count</label> <select
											class="form-control" ng-model="itemsPerPage"
											ng-options="item for item in pagevalues"></select>
									</div>
									<ul class="pagination pull-right">
										<li ng-class="{disabled: currentPage == 0}"><a href
											ng-click="prevPage()">« Prev</a></li>

										<li
											ng-repeat="n in range(pagedItems.length, currentPage, currentPage + gap) "
											ng-class="{active: n == currentPage}" ng-click="setPage()">
											<a href ng-bind="n + 1">1</a>
										</li>

										<li
											ng-class="{disabled: (currentPage) == pagedItems.length - 1}">
											<a href ng-click="nextPage()">Next »</a>
										</li>
									</ul>
								</div>
							</td>
						</tfoot>

					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>