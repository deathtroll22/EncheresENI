<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Encheres.org - Sell Something</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<style>
body {
	background-color: #005fb0;
}

.btn-primary {
	background-color: #005fb0 !important;
	border-color: #007bff;
}

.bg-carton {
	background-color: #b68759 !important;
}
</style>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="container mt-4">
		<h2 class="text-center text-white">Sell Something!</h2>
		<div class="card bg-light mt-4">
			<div class="card-body">
				<form action="SellItemServlet" method="post">
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label for="itemName">Item Name:</label> <input type="text"
									class="form-control" id="itemName" name="itemName" required>
							</div>
							<div class="form-group">
								<label for="itemDescription">Description:</label>
								<textarea class="form-control" id="itemDescription"
									name="itemDescription" required rows="1"></textarea>
							</div>
							<div class="form-group">
								<label for="category">Category:</label> <select
									class="form-control" id="category" name="category" required>
									<option value="" selected disabled>Select a category</option>
									<c:forEach items="${categories}" var="category">
										<option value="${category.categoryNumber}">${category.categoryName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="startingPrice">Starting Price:</label> <input
									type="number" class="form-control" id="startingPrice"
									name="startingPrice" required>
							</div>
							<div class="form-group">
								<label for="auctionStartDate">Auction Start Date:</label> <input
									type="date" class="form-control" id="auctionStartDate"
									name="auctionStartDate" value="<%=java.time.LocalDate.now()%>"
									required>
							</div>
							<div class="form-group">
								<label for="auctionEndDate">Auction End Date:</label> <input
									type="date" class="form-control" id="auctionEndDate"
									name="auctionEndDate" required>
							</div>
						</div>
					</div>
					<div class="container">
						<div class="row justify-content-center">
							<div class="col-md-9">
								<div class="card m-4 bg-carton shadow-lg text-dark">
									<div class="card-body">
										<h4 class="text-center">Pick Up</h4>
										<div class="form-group row">
											<label class="col-md-3 col-form-label" for="pickupStreet">Street:</label>
											<div class="col-md-9">
												<input type="text" class="form-control form-control-sm"
													id="pickupStreet" name="pickupStreet"
													value="rue des bogues" required>
											</div>
										</div>
										<div class="form-group row">
											<label class="col-md-3 col-form-label" for="pickupPostalCode">Postal
												Code:</label>
											<div class="col-md-9">
												<input type="text" class="form-control form-control-sm"
													id="pickupPostalCode" name="pickupPostalCode" value="35000"
													required>
											</div>
										</div>
										<div class="form-group row">
											<label class="col-md-3 col-form-label" for="pickupCity">City:</label>
											<div class="col-md-9">
												<input type="text" class="form-control form-control-sm"
													id="pickupCity" name="pickupCity" value="Paris" required>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<button type="submit" class="btn btn-primary btn-block">Sell
						That Item!</button>
					<a href="HomeServlet" class="btn btn-secondary btn-block mt-2">Cancel</a>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
