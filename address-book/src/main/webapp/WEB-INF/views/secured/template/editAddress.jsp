<div class="col-md-6 col-md-offset-3">
	<div style="padding: 20px;">
		<center>
			<h3>Add Address</h3>
		</center>
		<form role="form" name="addressForm" ng-submit="submitAddress()">
			<div class="form-group">
				<label for="name">Name :</label> <input type="text"
					ng-model="newName" class="form-control" id="name" required>
			</div>
			<div class="form-group">
				<label for="phoneNumber">Phone Number :</label> <input type="text"
					ng-model="newPhoneNumber" class="form-control" id="phoneNumber"
					required>
			</div>

			<div class="form-group">
				<label for="phoneNumber">Email :</label> <input type="text"
					ng-model="newEmail" class="form-control" id="email" required>
			</div>

			<div class="form-group">
				<label for="address">Address :</label>
				<textarea rows="5" class="form-control" id="address"
					ng-model="newAddress" required></textarea>
			</div>
			<button type="submit" class="btn btn-default"
				ng-disabled="addressForm.$invalid">Submit</button>
		</form>
	</div>
</div>