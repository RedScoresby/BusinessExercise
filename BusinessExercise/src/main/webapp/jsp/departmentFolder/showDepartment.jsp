<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
<title>Department ${department.name }</title>
</head>
<body>

	<div>
		<h2>Department</h2>
		
		<div>
			<b>Name</b>: ${department.name} <br/>
			<br/>
			<b>Employees working here</b>: <br/>
			
			<c:forEach items="${department.employeesWhoWorkHere}" var="employee">
				${employee.name} ${employee.firstSurname} ${employee.secondSurname} <br/>
			</c:forEach>
		</div>
		<br/>
		<form action="/department/modify/${department.id}" method="get">
			<input type="submit" value="Edit">
		</form>
		<form action="/allDepartments" method="get">
			<input type="submit" value="Back">
		</form>
	</div>
</body>
</html>