<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
<title>List of all the employees</title>
</head>
<body>
		
	<c:if test="${sessionScope.isAdmin}">
		<h2>Create new employee</h2>
		<form action="/employee/create" method="get">
			<input type="submit" value="New employee">
		</form>
	</c:if>
	
	<h2>Filter employees</h2>
	
	<form:form action="/listOfEmployees/filter" method="get" modelAttribute="employeeFilter"> 
		<form:input type="text" path="filterString"></form:input> 
		<br/>
		<form:radiobutton path="filterType" value="name"/>Name 
		<form:radiobutton path="filterType" value="firstSurname"/>First surname 
		<form:radiobutton path="filterType" value="secondSurname"/>Second surname 
		<form:radiobutton path="filterType" value="address"/>Address 
		<form:radiobutton path="filterType" value="role"/>Role 
		<form:radiobutton path="filterType" value="department"/>Department 
		<br/>
		<input type="submit" value="Go">
	</form:form>
	<br/>
	
	<form action="/listOfEmployees" method="get">
		<input type="submit" value="All employees">
	</form>
	<br/>
	
	<form action="/allDepartments" method="get">
		<input type="submit" value="See departments">
	</form>
	<br/>
	
	<form action="/allRoles" method="get">
		<input type="submit" value="See roles">
	</form>
		
	<h2>All employees</h2>
	<table>
		<tr>
			<td><b>Name</b></td>
			<td><b>First surname</b></td>
			<td><b>Second surname</b></td>
			<td><b>DNI</b></td>
			<td><b>Address</b></td>
			<td><b>Role</b></td>
			<td><b>Departments</b></td>
		</tr>
  		<c:forEach items="${listOfAllEmployees}" var="employee">
    		<tr>
      			<td><c:out value="${employee.name}"/></td>
      			<td><c:out value="${employee.firstSurname}"/></td>
      			<td><c:out value="${employee.secondSurname}"/></td>
      			<td><c:out value="${employee.DNI}"/></td>
      			<td><c:out value="${employee.address}"/></td>
      			<td><c:out value="${employee.role.getName()}"/></td>
      			<td>
      				<c:forEach items="${employee.departmentsWorkingIn}" var="department">
      					<c:out value="${department.getName()}"/>
      				</c:forEach>
      			</td>
				<c:if test="${sessionScope.isAdmin}">
      				<td> 
						<form:form action="/employee/${employee.id}" method="get" modelAttribute="employee">
							<input type="submit" value="Show"> 
						</form:form>
      				</td>
      				<td> 
						<form:form action="/employee/modify/${employee.id}" method="get">
							<input type="submit" value="Edit"> 
						</form:form>
      				</td>
      				<c:if test="${user.id != employee.id}">
      					<td> 
							<form:form action="/employee/delete/${employee.id}" method="get">
								<input type="submit" value="Delete"> 
							</form:form>
      					</td>
      				</c:if>
				</c:if>
   			</tr>
  		</c:forEach>
	</table>
</body>
</html>