<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang='en'>
<head>
<meta charset="utf-8">
<link type="text/css"
	href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" />
<link type="text/css" href="${contextPath}/resources/css/main_page.css"
	rel="stylesheet" />
<title>Registration</title>
</head>

<body>
	<div class="text-center">
		<div class="login-form col-md-4 offset-md-4">
			<form:form method="POST" modelAttribute="user">
				<h2>Registration</h2>
				<form:input class="form-control" type="text" path="username"
					placeholder="Username" required="true"></form:input>
				<div class="error-code">
					<form:errors path="username"></form:errors>
					${usernameError}
				</div>
				<form:input class="form-control" id="password" type="password"
					path="password" placeholder="Password" required="true"></form:input>
				<div class="error-code">
					<form:errors path="password"></form:errors>
					${passwordError}
				</div>
				<form:input class="form-control" id="confirmPassword"
					type="password" path="passwordConfirm"
					placeholder="Confirm your password" required="true"></form:input>
				<form:input class="form-control" type="email" path="email"
					placeholder="Email" required="true"></form:input>

				<button class="btn btn-primary btn-block " type="submit">Register</button>
			</form:form>
			<a href="/login">Login</a>
		</div>
	</div>
	<script type="text/javascript">
		window.onload = function() {
			document.getElementById("password").onchange = validatePassword;
			document.getElementById("confirmPassword").onchange = validatePassword;
		}
		function validatePassword() {
			var password = document.getElementById("password").value;
			var confirmPassword = document.getElementById("confirmPassword").value;
			if (password != confirmPassword)
				document.getElementById("confirmPassword").setCustomValidity(
						"Passwords don't Match");
			else
				document.getElementById("confirmPassword")
						.setCustomValidity('');
		}
	</script>
	<script type="text/javascript" src="${style}/js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="${style}/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${style}/js/popper.min.js"></script>
	<script type="text/javascript" src="${style}/js/main.js"></script>
</body>
</body>
</html>