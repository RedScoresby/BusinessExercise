<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
<title>Create new role</title>
</head>
<body>

	<h2>New role</h2>
	
	<form:form action="/role/new" method="post" modelAttribute="role">
		<form:label path="name"><b>Name</b></form:label>
		<form:input type="text" path="name"></form:input>
		<br/>
		<c:if test="${errorRoleExists}">This name belongs to another role <br/></c:if>
		<input type="submit" value="Save">
	</form:form>
</body>
</html>