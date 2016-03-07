
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet"
	href='<c:url value="/resources/lib/bootstrap/css/bootstrap.min.css" />'>
<link rel="stylesheet"
	href='<c:url value="/resources/lib/bootstrap/css/bootstrap-theme.min.css" />'>


<link rel="stylesheet"
	href='<c:url value="/resources/css/commons.css" />'>

<script
	src='<c:url value="/resources/lib/bootstrap/js/bootstrap.min.js" />'></script>


<script src='<c:url value="/resources/lib/bootstrap/js/bootbox.js" />'></script>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<c:url var="home" value="/app/home"></c:url>
<c:url var="logout" value="/app/logout"></c:url>

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
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav" id="left-menu">
				<li id="home"><a href="${home }">Home</a></li>

				<li class="dropdown"><a href="" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">Address <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#/addAddress">Add</a></li>
						<li><a href="#/addressBookList">List</a></li>
					</ul></li>
				<li class="dropdown"><a href="" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">User <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#/addUser">Add</a></li>
						<li><a href="#/listUser	">List</a></li>
					</ul></li>
			</ul>
			<ul class="nav navbar-nav navbar-right" id="right-menu">
				<li id="login"><a href="${logout }"><span
						class="glyphicon glyphicon-log-out" style="margin-right: 3px;"></span>Logout</a></li>
			</ul>
		</div>
	</div>
</nav>