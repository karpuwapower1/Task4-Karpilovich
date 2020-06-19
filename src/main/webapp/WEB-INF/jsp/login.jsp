<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html lang='en'>
<head>
  <meta charset="utf-8">
<link type="text/css" href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet" />
<link type="text/css" href="${contextPath}/resources/css/main_page.css"
	rel="stylesheet" />
  <title>Log in</title>
</head>

<body>
<sec:authorize access="isAuthenticated()">
  <% response.sendRedirect("/users"); %>
</sec:authorize>
<div class="text-center">
		<div class="login-form col-md-4 offset-md-4">
  <form method="post" action="/login">
    <h2>Login</h2>
    <div>
      <input class="form-control" name="username" type="text" placeholder="Username"/>
      <input class="form-control" name="password" type="password" placeholder="Password"/>
      <button class="btn btn-primary btn-block" type="submit">Log In</button>
      <a href="/registration">Register</a>
    </div>
  </form>
</div>
</div>
</body>
</html>