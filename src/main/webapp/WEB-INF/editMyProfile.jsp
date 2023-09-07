<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Encheres.org - Edit My Profile</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<style>
.btn-primary {
	background-color: #005fb0 !important;
	border-color: #007bff;
}

.highlight-info {
	color: #005fb0;
}

.no-padding-left-right-md-8 {
	padding-left: 0;
	padding-right: 0;
}

.no-padding-left-right-md-4 {
	padding-left: 0;
	padding-right: 0;
}
</style>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="container mt-4">
		<h1 class="text-center text-white pacifico">Edit My Profile</h1>
		<div class="card mt-4">
			<div class="card-body">
				<form action="EditMyProfileServlet" method="post">
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
								<label for="street" class="highlight-info">Street:</label> <input
									type="text" class="form-control" id="street" name="street"
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
						        <label for="oldPassword" class="highlight-info">Old Password:</label>
						        <input type="password" class="form-control" id="oldPassword" name="oldPassword" placeholder="Enter old password" required>
						        <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
						        <% if (errorMessage != null) { %>
						        <div class="alert alert-danger mt-2">
						            <%= errorMessage %>
						        </div>
						        <% } %>
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
					<div class="col-12 mt-4">
						<div class="row">
							<div class="col-md-8 px-0">
								<button type="submit" class="btn btn-primary w-100">Save
									Changes</button>
							</div>
							<div class="col-md-4 p3- pr-0">
								<button type="button" class="btn btn-danger w-100"
									data-toggle="modal" data-target="#confirmDeleteModal">
									Delete My Profile</button>
							</div>
						</div>
					</div>
				</form>

			</div>
		</div>
	</div>
	
	<div class="modal fade" id="confirmDeleteModal" tabindex="-1"
		role="dialog" aria-labelledby="confirmDeleteModalLabel"
		aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="confirmDeleteModalLabel">Confirm
						Profile Deletion</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form action="DeleteProfileServlet" method="post">
						<p>Are you sure you want to delete your profile? This action
							cannot be undone.</p>
						<label for="deletePassword">Enter your password:</label> <input
							type="password" id="deletePassword" name="deletePassword"
							class="form-control">
						  <div class="modal-footer px-0">
                        <div class="col-12 row px-0">
                            <div class="col-6 px-0">
                                <button type="button" class="btn btn-secondary w-100" data-dismiss="modal">Cancel</button>
                            </div>
                            <div class="col-6 px-0">
                                <button type="submit" class="btn btn-danger w-100 ml-2">Delete</button>
                            </div>
                        </div>
                    </div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
