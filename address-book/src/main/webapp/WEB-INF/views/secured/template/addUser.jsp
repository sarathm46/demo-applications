<div class="col-md-6 col-md-offset-3">
	<div style="padding: 20px;">
		<center>
			<h3>Add New User</h3>
		</center>
		<form role="form" name="addUserForm" ng-submit="submitUser()">
			<div class="form-group">
				<label for="newName">Name :</label> <input type="text"
					ng-model="newName" class="form-control" id="newName" required>
			</div>
			<div class="form-group">
				<label for="newUserName">User Name :</label> <input type="text"
					ng-model="newUsername" class="form-control" id="newUsername"
					required>
			</div>
			<div class="form-group">
				<label for="newPassword">Password :</label> <input type="password"
					ng-model="newPassword" class="form-control" id="newPassword"
					required>
			</div>
			<button type="submit" class="btn btn-default"
				ng-disabled="addUserForm.$invalid">Submit</button>
		</form>
	</div>
</div>