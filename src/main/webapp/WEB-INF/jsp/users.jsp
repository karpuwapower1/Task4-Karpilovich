<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
<title>Users</title>
</head>
<body>

	<form method="post" id='form1'>
		<header>
			<nav class="navbar navbar-expand-lg navbar-light bg-light">
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav mr-auto">
						<li class="nav-link">
							<li class="nav-item">
						<button class="btn btn-primary btn-block" type="submit"
								name="action" value="delete">Delete</button>
						</li><li class="nav-item"><button
								class="btn btn-link btn-block" type="submit" name="action"
								value="unblock">Unblock</button></li>
						<li class="nav-item"><button
								class="btn btn-link btn-block" type="submit" name="action"
								value="block">Block</button></li>
					</ul>
				</div>
				<div class="navbar-nav ml-auto">
					<div class="nav-item">
						<a href="/logout">Logout</a>
					</div>
				</div>
			</nav>
		</header>
		<div>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th scope="col">
							<div class="custom-control custom-checkbox">
								<input type="checkbox" class="custom-control-input"
									id="customCheck1" name="select_all"> <label
									class="custom-control-label" for="customCheck1">Select
									all</label>
							</div>
						</th>
						<th scope="col">Id</th>
						<th scope="col">Name</th>
						<th scope="col">Password</th>
						<th scope="col">Login date</th>
						<th scope="col">Registration date</th>
						<th scope="col">Status</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${users}" var="user">
						<tr>
							<td>
								<div class="form-check">
									<input class="form-check-input position-static" type="checkbox"
										id="blankCheckbox" name="userId" value="${user.id}"
										aria-label="...">
								</div>
							</td>
							<td>${user.id}</td>
							<td>${user.username}</td>
							<td>${user.password}</td>
							<td>${user.lastLoginDate}</td>
							<td>${user.registrationDate}</td>
							<td>${user.nonLocked}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</form>

	<script type="text/javascript">
		var f = document.getElementById('form1');
		f.select_all.onchange = function(e) {
			var el = e.target || e.srcElement;
			var checkbox = el.form
					.getElementsByClassName('form-check-input position-static');
			for (var i = 0; i < checkbox.length; i++) {
				if (el.checked) {
					checkbox[i].checked = true;
				} else {
					checkbox[i].checked = false;
				}
			}
		}
	</script>
	<script type="text/javascript" src="${style}/js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="${style}/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${style}/js/popper.min.js"></script>
	<script type="text/javascript" src="${style}/js/main.js"></script>
</body>
</html>