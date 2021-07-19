<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
<title>New employee</title>
</head>
<body>

	<div>
		<h2>Write the new employee's information</h2>
		<form:form action="/employee/new" method="post" modelAttribute="employee">
			<table>
				<tr>
					<td> <form:label path="name">Name</form:label></td>
					<td> <form:input type="text" path="name"></form:input> </td>
				</tr>
				<tr>
					<td><form:label path="firstSurname">First surname</form:label></td>
					<td><form:input type="text" path="firstSurname"></form:input> </td>
				</tr>
				<tr>
					<td><form:label path="secondSurname">Second surname</form:label></td>
					<td><form:input type="text" path="secondSurname"></form:input> </td>
				</tr>
				<tr>
					<td><form:label path="DNI">DNI</form:label></td>
					<td><form:input type="text" path="DNI" maxlength="9"></form:input></td>
					<td><c:if test="${errorEmployeeExists}">This DNI belongs to another employee</c:if></td>
				</tr>
				<tr>
					<td><form:label path="address">Address</form:label></td>
					<td><form:input type="text" path="address"></form:input> </td>
				</tr>
				<tr>
					<td><form:label path="role">Role</form:label></td>
					<c:forEach items="${roleList}" var="role">
						<td><form:radiobutton path="role" value="${role.id}"/>${role.name}</td>
					</c:forEach>
				</tr>
				<tr>
					<td><form:label path="departmentsWorkingIn">Departments</form:label></td>
					<c:forEach items="${departmentList}" var="department">
						<td><form:checkbox path="departmentsWorkingIn" value="${department.id}"/>${department.name}</td>
					</c:forEach>
				</tr>
				<tr>
					<td><input type="submit" value="Save"> </td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>