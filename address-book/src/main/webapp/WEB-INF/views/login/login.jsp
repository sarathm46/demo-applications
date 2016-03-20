<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<script src='<c:url value="/resources/lib/jquery/jquery.min.js" />'></script>

<script src='<c:url value="/resources/lib/pace/pace.min.js"/>'></script>
<script type="text/javascript">
	window.paceOptions = {
		document : true,
		eventLag : true,
		restartOnPushState : true,
		restartOnRequestAfter : true,
		ajax : {
			trackMethods : [ 'POST', 'GET' ]
		}

	};
</script>

</head>
<body>

	<jsp:include page="\template\header.jsp" />

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
									<label> <input name="remember" type="checkbox"
										value="Remember Me">Remember Me
									</label>
								</div>
								<!-- Change this to a button or input when using this as a form -->
								<input type="submit" class="btn btn-sm btn-success" />
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>