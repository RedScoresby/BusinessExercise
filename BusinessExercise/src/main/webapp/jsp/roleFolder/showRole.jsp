<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
<title>Role ${role.name}</title>
</head>
<body>

	<div>
		<h2>Role</h2>
		
		<div>
			<b>Name</b>: ${role.name} <br/>
			<br/>
			<b>Employees with this role</b>:<br/>
			
			<c:forEach items="${role.employeesWithThisRole}" var="employee">
				${employee.name} ${employee.firstSurname} ${employee.secondSurname} <br/>
			</c:forEach>
		</div>
		<br/>
		<form action="/role/modify/${role.id}" method="get">
			<input type="submit" value="Edit">
		</form>
		<form action="/allRoles" method="get">
			<input type="submit" value="Back">
		</form>
	</div>
</body>
</html>