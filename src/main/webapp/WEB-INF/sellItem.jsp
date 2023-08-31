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
</style>
</head>
<body>
    <%@ include file="header.jsp"%>
    <div class="container mt-4">
        <h2 class="text-center text-white">Sell Something!</h2>
        <div class="card mt-4">
            <div class="card-body">
                <form action="insertItemServlet" method="post">
                    <div class="form-group">
                        <label for="itemName">Item Name:</label>
                        <input type="text" class="form-control" id="itemName" name="itemName" required>
                    </div>
                    <div class="form-group">
                        <label for="itemDescription">Description:</label>
                        <textarea class="form-control" id="itemDescription" name="itemDescription" required></textarea>
                    </div>
                    <div class="form-group">
                        <label for="auctionStartDate">Auction Start Date:</label>
                        <input type="date" class="form-control" id="auctionStartDate" name="auctionStartDate" value="<%= java.time.LocalDate.now() %>" required>
                    </div>
                    <div class="form-group">
                        <label for="auctionEndDate">Auction End Date:</label>
                        <input type="date" class="form-control" id="auctionEndDate" name="auctionEndDate" required>
                    </div>
                    <div class="form-group">
                        <label for="startingPrice">Starting Price:</label>
                        <input type="number" class="form-control" id="startingPrice" name="startingPrice" required>
                    </div>
                    <div class="form-group">
                        <label for="category">Category:</label>
                        <select class="form-control" id="category" name="category" required>
                            <option value="" selected disabled>Select a category</option>
                            <option value="1">Computers</option>
                            <option value="2">Furniture</option>
                            <option value="3">Sports and Recreation</option>
                            <option value="4">Clothing</option>
                            <!-- Add more options according to your categories -->
                        </select>
                    </div>
                    <div class="card m-4 bg-dark shadow text-white">
                        <div class="card-body">
                            <h4 class="text-center">Pick Up</h4>
                            <div class="form-row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="pickupStreet">Street Name:</label>
                                        <input type="text" class="form-control" id="pickupStreet" name="pickupStreet" value="rue des bogues" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="pickupPostalCode">Postal Code:</label>
                                        <input type="text" class="form-control" id="pickupPostalCode" name="pickupPostalCode" value="35000" required>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="pickupCity">City:</label>
                                        <input type="text" class="form-control" id="pickupCity" name="pickupCity" value="Paris" required>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary btn-block">Sell That Item!</button>
                    <a href="HomeServlet" class="btn btn-secondary btn-block mt-2">Cancel</a>
                </form>
            </div>
        </div>
    </div>
</body>
</html>