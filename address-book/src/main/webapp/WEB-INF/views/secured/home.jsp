<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<script src='<c:url value="/resources/lib/jquery/jquery.min.js" />'></script>

<script src='<c:url value="/resources/lib/angular/angular.min.js" />'></script>
<script
	src='<c:url value="/resources/lib/angular/angular-ui-router.min.js" />'></script>

<script src='<c:url value="/resources/js/default.js"/>'></script>
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
<body ng-app="addressBook">
	<jsp:include page="\template\header.jsp" />

	<div class="container">
		<div class="row" ui-view>
		</div>
	</div>


	<div id="messageModel" class="modal fade" role="dialog">
		<div class="modal-dialog modal-sm">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title" id="m_title">Modal Header</h4>
				</div>
				<div class="modal-body">
					<p id="m_message">Some text in the modal.</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>


	<div id="confirm" class="modal fade" role="dialog">
		<div class="modal-dialog modal-sm">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title" id="m_title_confirm">Confirm</h4>
				</div>
				<div class="modal-body">
					<p id="m_message_confirm">Are you sure?</p>
				</div>
				<div class="modal-footer">
					<button type="button" id ="_no" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<button type="button" id ="_yes" class="btn btn-default" data-dismiss="modal">OK</button>
				</div>
			</div>

		</div>
	</div>

</body>