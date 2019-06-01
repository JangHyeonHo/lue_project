<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="signup"/></title>

<script src="https://code.jquery.com/jquery-3.4.1.js"
	integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"
	integrity="sha256-T0Vest3yCU7pafRw9r+settMBX6JkKN06dqBnpQ8d30="
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<script src="js/SignUp.js" type="text/javascript"></script>
<link href="css/Default.css" type="text/css" rel="stylesheet">

</head>
<body>
	<form:form class = "container">
		<fieldset>
			<input type="email" name="email" id="email" placeholder="<spring:message code="email" />" required><br>
			<div style="display:none" id = "emError"><spring:message code="email.error" /></div>
			<input type="password" name="password" id="pw" placeholder="<spring:message code="password" />" required><br>
			<div style="display:none" id = "pwError"><spring:message code="password.placeholder" /></div>
			<input type="password" id="pwConfirm" placeholder="<spring:message code="password.confirm" />" required ><br> 
			<div style="display:none" id = "pwConError"><spring:message code="password.nomatch" /></div>
			<input type="text" name="firstName" id="firstName" placeholder="<spring:message code="F.name" />" required><br>
			<input type="text" name="lastName" id="lastName" placeholder="<spring:message code="L.name" />" required><br>
			<input type="submit" id="signUpBtn" value="<spring:message code="signup"/>">
			<input type="button" id="backBtn" value="<spring:message code="back"/>">
		</fieldset>
	</form:form>


</body>
</html>