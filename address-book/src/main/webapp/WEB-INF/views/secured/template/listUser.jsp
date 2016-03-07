<table class="table table-bordered">
	<tr>
		<th>#</th>
		<th>Name</th>
		<th>User Name</th>
		<th>Password</th>
		<th>Edit</th>
		<th>Delete</th>
	</tr>
	<tr ng-repeat="user in users">
		<td>{{ $index+1 }}</td>
		<td>{{user.name}}</td>
		<td>{{user.username}}</td>
		<td>{{user.password}}</td>
		<td><a href="#/editUser/{{user.id}}"><span
				class="glyphicon glyphicon-edit"></span></a></td>
		<td><a href="" ng-click="deleteAppUser(user.id)"><span
				class="glyphicon glyphicon-trash"></span></a></td>
	</tr>
</table>
