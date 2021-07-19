<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
<title>Modify ${department.name}</title>
</head>
<body>

	<h2>Edit the department</h2>
	
	<form:form action="/department/update" method="post" modelAttribute="department">
		<form:input type="hidden" path="id" value="${department.id}"></form:input>
		<form:label path="name"><b>Name</b></form:label>
		<form:input type="text" path="name" placeholder="${department.name}"></form:input>
		<br/>
		<c:if test="${errorModifyingDepartment}">This name belongs to another department <br/></c:if>
		<input type="submit" value="Save">
	</form:form>
	<form action="/allDepartments" method="get">
		<input type="submit" value="Back">
	</form>
</body>
</html>