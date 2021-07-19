<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
<title>List of all departments</title>
</head>
<body>

	<form action="/listOfEmployees" method="get">
		<input type="submit" value="Back">
	</form>
	
	<c:if test="${sessionScope.isAdmin}">
		<h2>Create new department</h2>
		<form action="/department/create" method="get">
			<input type="submit" value="New department">
		</form>
	</c:if>
	
	<h2>All departments</h2>
	<table>
		<tr>
			<td><b>Name</b></td>
		</tr>
  		<c:forEach items="${departments}" var="department">
    		<tr>
      			<td><c:out value="${department.name}"/></td>
				<c:if test="${sessionScope.isAdmin}">
      				<td> 
						<form:form action="/department/${department.id}" method="get" modelAttribute="department">
							<input type="submit" value="Show"> 
						</form:form>
      				</td>
      				<td> 
						<form:form action="/department/modify/${department.id}" method="get">
							<input type="submit" value="Edit"> 
						</form:form>
      				</td>
      				<td> 
						<form:form action="/department/delete/${department.id}" method="get">
							<input type="submit" value="Delete"> 
						</form:form>
      				</td>
				</c:if>
   			</tr>
  		</c:forEach>
	</table>
	<c:if test="${errorDeletingDepartment}">Can't delete ${errorDepartmentName} because there is an employee working there<br/></c:if>
</body>
</html>