<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Login</title>
<meta name="description" content="" />
<meta name="viewport" content="width=device-width" />
<base href="/" />
<link rel="stylesheet" type="text/css"
	href="/webjars/bootstrap/css/bootstrap.min.css" />

<link rel="stylesheet" type="text/css"
	href="webjars/font-awesome/4.5.0/css/font-awesome.css" />
<script type="text/javascript" src="/webjars/jquery/jquery.min.js"></script>
<script type="text/javascript"
	src="/webjars/bootstrap/js/bootstrap.min.js"></script>

</head>
<body ng-app="app" ng-controller="home as home">

	<c:url value='/app/login' var="login" />
	<div style="height: 50px"></div>
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Sign In</h3>
					</div>
					<div class="panel-body">
						<form role="form" action="${login }" method="post">
							<fieldset>
								<div class="form-group">
									<input class="form-control" placeholder="Username"
										name="username" type="text" autofocus="">
								</div>
								<div class="form-group">
									<input class="form-control" placeholder="Password"
										name="password" type="password" value="">
								</div>
								<div class="checkbox">
									<label> <input name="remember-me" type="checkbox"
										value="Remember Me">Remember Me
									</label>
								</div>
								<!-- Change this to a button or input when using this as a form -->
								<input type="submit" class="btn btn-sm btn-success" />
								<hr class="hr">
								<h4>Sign in with</h4>

								<c:url value="/login/github" var="gitUrl" />
								<c:url value="/login/facebook" var="facebookUrl" />
								<c:url value="/login/google" var="googleUrl" />
								<br /> <a href="${googleUrl}" class="btn btn-sm btn-info">
									<span class="fa fa-google-plus"></span>  Google
								</a> <a href="${facebookUrl}" class="btn btn-sm btn-info"> <span
									class="fa fa-facebook"></span>  Facebook
								</a>
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>



</body>
</html>