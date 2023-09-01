<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Encheres.org - Edit My Profile</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
.btn-primary {
	background-color: #005fb0 !important;
	border-color: #007bff;
}

.highlight-info {
	color: #005fb0;
}
</style>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="container mt-4">
		<h2 class="text-center text-white">Edit My Profile</h2>
		<div class="card mt-4">
			<div class="card-body">
				<form
					action="EditMyProfileServlet"
					method="post">
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label for="username" class="highlight-info">Username:</label> <input
									type="text" class="form-control" id="username" name="username"
									value="${sessionScope.user.username}" readonly>
							</div>
							<div class="form-group">
								<label for="firstName" class="highlight-info">First
									Name:</label> <input type="text" class="form-control" id="firstName"
									name="firstName" value="${user.firstName}">
							</div>
							<div class="form-group">
								<label for="lastName" class="highlight-info">Last Name:</label>
								<input type="text" class="form-control" id="lastName"
									name="lastName" value="${user.lastName}">
							</div>
							<div class="form-group">
								<label for="email" class="highlight-info">Email Address:</label>
								<input type="email" class="form-control" id="email" name="email"
									value="${user.email}">
							</div>

						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="telephone" class="highlight-info">Telephone:</label>
								<input type="text" class="form-control" id="telephone"
									name="telephone" value="${user.phoneNumber}">
							</div>
							<div class="form-group">
								<label for="street" class="highlight-info">Street:</label> 
								<input type="text" class="form-control" id="street" name="street"
									value="${user.street}">
							</div>
							<div class="form-group">
								<label for="postalCode" class="highlight-info">Postal
									Code:</label> <input type="text" class="form-control" id="postalCode"
									name="postalCode" value="${user.postalCode}">
							</div>
							<div class="form-group">
								<label for="city" class="highlight-info">City:</label> <input
									type="text" class="form-control" id="city" name="city"
									value="${user.city}">
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label for="oldPassword" class="highlight-info">Old
									Password:</label> <input type="password" class="form-control"
									id="oldPassword" name="oldPassword"
									placeholder="Enter old password">
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label for="newPassword" class="highlight-info">New
									Password:</label> <input type="password" class="form-control"
									id="newPassword" name="newPassword"
									placeholder="Enter new password">
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label for="confirmNewPassword" class="highlight-info">Confirm
									New Password:</label> <input type="password" class="form-control"
									id="confirmNewPassword" name="confirmNewPassword"
									placeholder="Confirm new password">
							</div>
						</div>
					</div>
					<button type="submit" class="btn btn-primary btn-block">Save
						Changes</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
