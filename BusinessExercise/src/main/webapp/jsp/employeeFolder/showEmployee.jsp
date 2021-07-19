<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
<title>${employee.name} profile</title>
</head>
<body>
	
	<div>
		<h2>${employee.name} ${employee.firstSurname} ${employee.secondSurname} profile</h2>
		
		<div>
		<table>
			<tr>
				<td><b>Name</b>:</td>
				<td>${employee.name}</td>
			</tr>
			<tr>
				<td><b>First surname</b>:</td>
				<td>${employee.firstSurname}</td>
			</tr>
			<tr>
				<td><b>Second surname</b>:</td>
				<td>${employee.secondSurname}</td>
			</tr>
			<tr>
				<td><b>DNI</b>:</td>
				<td>${employee.DNI}</td>
			</tr>
			<tr>
				<td><b>Address</b>:</td>
				<td>${employee.address}</td>
			</tr>
			<tr>
				<td><b>Role:</b></td>
				<td>${employee.role.name}</td>
			</tr>
			<tr>
				<td><b>Departments:</b></td>
				<td>
					<c:forEach items="${employee.departmentsWorkingIn}" var="department">
						${department.name} 
					</c:forEach>
				</td>
			</tr>
		</table>
		</div>
		<br/>
		<form action="/employee/modify/${employee.id}" method="get">
			<input type="submit" value="Edit">
		</form>
		<form action="/listOfEmployees" method="get">
			<input type="submit" value="Back">
		</form>
	</div>
	
	
</body>
</html>