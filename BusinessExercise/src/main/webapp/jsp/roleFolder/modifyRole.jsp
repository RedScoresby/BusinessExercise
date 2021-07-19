<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
<title>Modify ${role.name}</title>
</head>
<body>

	<h2>Edit the role</h2>
	
	<form:form action="/role/update" method="post" modelAttribute="role">
		<form:input type="hidden" path="id" value="${role.id}"></form:input>
		<form:label path="name"><b>Name</b></form:label>
		<form:input type="text" path="name" placeholder="${role.name}"></form:input>
		<br/>
		<c:if test="${errorModifyingRole}">This name belongs to another role <br/></c:if>
		<input type="submit" value="Save">
	</form:form>
	<form action="/allRoles" method="get">
		<input type="submit" value="Back">
	</form>
</body>
</html>