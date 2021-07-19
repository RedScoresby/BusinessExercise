<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
<title>Create a new department</title>
</head>
<body>

	<h2>New department</h2>
	
	<form:form action="/department/new" method="post" modelAttribute="department">
		<form:label path="name"><b>Name</b></form:label>
		<form:input type="text" path="name"></form:input>
		<br/>
		<c:if test="${errorDepartmentExists}">This name belongs to another department <br/></c:if>
		<input type="submit" value="Save">
	</form:form>
</body>
</html>