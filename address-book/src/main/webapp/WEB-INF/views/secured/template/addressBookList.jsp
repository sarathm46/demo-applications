<table class="table table-bordered">
	<tr>
		<th>#</th>
		<th>Name</th>
		<th>Phone Number</th>
		<th>Address</th>
		<th>Email</th>
		<th>Created User</th>
		<th>Edit</th>
		<th>Delete</th>
	</tr>
	<tr ng-repeat="address in addressBookList">
		<td>{{ $index+1 }}</td>
		<td>{{address.name}}</td>
		<td>{{address.phoneNumber}}</td>
		<td>{{address.address}}</td>
		<td>{{address.email}}</td>
		<td>{{address.appUser.name}}</td>
		<td><a href="#/editAddress/{{address.id}}"><span
				class="glyphicon glyphicon-edit"></span></a></td>
		<td><a href="" ng-click="deleteAddress(address.id)"><span
				class="glyphicon glyphicon-trash"></span></a></td>
	</tr>
</table>