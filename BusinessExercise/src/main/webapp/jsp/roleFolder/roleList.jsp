<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
<title>List of all roles</title>
</head>
<body>

	<form action="/listOfEmployees" method="get">
		<input type="submit" value="Back">
	</form>
	
	<c:if test="${sessionScope.isAdmin}">
		<h2>Create new role</h2>
		<form action="/role/create" method="get">
			<input type="submit" value="New role">
		</form>
	</c:if>

	<h2>All roles</h2>
	<table>
		<tr>
			<td><b>Name</b></td>
		</tr>
  		<c:forEach items="${roles}" var="role">
    		<tr>
      			<td><c:out value="${role.name}"/></td>
				<c:if test="${sessionScope.isAdmin}">
      				<td> 
						<form:form action="/role/${role.id}" method="get" modelAttribute="role">
							<input type="submit" value="Show"> 
						</form:form>
      				</td>
      				<td> 
						<form:form action="/role/modify/${role.id}" method="get">
							<input type="submit" value="Edit"> 
						</form:form>
      				</td>
      				<td> 
						<form:form action="/role/delete/${role.id}" method="get">
							<input type="submit" value="Delete"> 
						</form:form>
      				</td>
				</c:if>
   			</tr>
  		</c:forEach>
	</table>
	<c:if test="${errorDeletingRole}">Can't delete ${errorRoleName} because there is an employee with that role<br/></c:if>
</body>
</html>