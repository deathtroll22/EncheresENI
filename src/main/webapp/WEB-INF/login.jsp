<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Encheres.org</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
body {
	background-color: #f8f9fa; /* bg-light */
}

.centered-card {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.card {
	padding: 20px;
}

.form-row {
	display: flex;
	align-items: center;
	margin-bottom: 10px;
}

.form-label {
	width: 120px; /* Adjust as needed */
}

.form-group {
	margin-bottom: 0; /* Remove default margin */
}
</style>
</head>
<body>
	<div class="card-body">
		<form action="LoginServlet" method="post">
			<div class="form-row mt-2">
				<div class="form-label">
					<label for="pseudo">Username :</label>
				</div>
				<div class="form-group">
					<input type="text" class="form-control" id="pseudo" name="pseudo"
						value="<c:if test="${cookie.rememberMe != null}"><c:out value="${cookie.rememberMe.value}"/></c:if>"
						placeholder="Enter your username" required>
				</div>
			</div>
			<div class="form-row mb-2">
				<div class="form-label">
					<label for="mot_de_passe">Password :</label>
				</div>
				<div class="form-group ">
					<input type="password" class="form-control" id="password"
						name="password" required>
				</div>
			</div>
			<div class="form-row my-3">
				<div class="col-6">
					<div class="form-group">
						<button type="submit" class="btn btn-primary w-100">Login</button>
					</div>
				</div>
				<div class="col-6">
					<div class="form-group text-right">
						<input type="checkbox" class="form-check-input" id="rememberMe"
							name="rememberMe"> <label class="form-check-label"
							for="rememberMe">Remember Me</label>
					</div>
					<div class="form-group text-right">
						<a href="forgotPassword.jsp">Forgot Password?</a>
					</div>
				</div>
			</div>
			<div class="form-group">
				<a class="btn btn-lg btn-success w-100"
					href="${pageContext.request.contextPath}/InsertUserServlet"
					data-dismiss="modal" data-toggle="modal"
					data-target="#insertUserModal">Create an Account</a>
			</div>
		</form>
	</div>


</body>
</html>
