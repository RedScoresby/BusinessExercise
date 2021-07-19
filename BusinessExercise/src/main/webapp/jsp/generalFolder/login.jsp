<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
<title>Log in</title>
</head>
<body>
    <c:url var="authenticate" value="/authenticate"/>
	
	<div>
		<h2>Write your DNI to log in</h2>
		<form:form action="/authenticate" method="post" modelAttribute="authenticationRequest">
			<form:input type="text" path="username"></form:input>
			<input type="submit" value="Go"> <br/>
			<c:if test="${wrongDNI}">No employee exists with this DNI</c:if>
		</form:form>
	</div>
	
</body>
</html>