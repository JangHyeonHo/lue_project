<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="cha.create" /></title>

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

<script src="../js/ChaCreWin.js" type="text/javascript"></script>
<link href="../css/Default.css" type="text/css" rel="stylesheet">

</head>
<body>
	<form:form>
	<input type = "hidden" name = "creDate" id = "creDate">
	<input type = "text" id="chaName" name="cName" placeholder="<spring:message code="cha.name" />" required><br>
	<b><spring:message code="cha.sex" /></b><br>
	<span><spring:message code="cha.male" /></span><input type="radio" name="sex" id="male" value="male" checked>
	<span><spring:message code="cha.female" /></span><input type="radio" name="sex" id="female" value="female"><br>
	<b><spring:message code="status" /></b><br>
	<spring:message code="stat.str" /> <input type = "button" id = "strMinus" value="-" class="statBtn"><span id = "strPoint">5</span><input type = "hidden" value = "5" name = "status.str" id ="histr"><input type = "button" id = "strPlus" value="+" class="statBtn"><br>
	<spring:message code="stat.dex" /> <input type = "button" id = "dexMinus" value="-" class="statBtn"><span id = "dexPoint">5</span><input type = "hidden" value = "5" name = "status.dex" id ="hidex"><input type = "button" id = "dexPlus" value="+" class="statBtn"><br>
	<spring:message code="stat.int" /> <input type = "button" id = "intMinus" value="-" class="statBtn"><span id = "intPoint">5</span><input type = "hidden" value = "5" name = "status.intel" id ="hiint"><input type = "button" id = "intPlus" value="+" class="statBtn"><br>
	<spring:message code="stat.luc" /> <input type = "button" id = "lucMinus" value="-" class="statBtn"><span id = "lucPoint">5</span><input type = "hidden" value = "5" name = "status.luc"  id ="hiluc"><input type = "button" id = "lucPlus" value="+" class="statBtn"><br>
	<spring:message code="stat.point" /><span id = "statPoint">5</span><br>
	<div id = "statError" style = "display:none;"><spring:message code="stat.error" /></div>
	<div id = "statError2" style = "display:none;"><spring:message code="stat.error2" /></div>
	<input type = "submit" id = "smBtn" value="<spring:message code="cha.create" />">
	<input type = "button" id = "backBtn" value="<spring:message code="back" />">
	</form:form>
</body>
</html>