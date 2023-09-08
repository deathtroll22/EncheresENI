<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Encheres.org - Edit Item</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

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
    <h1 class="text-center text-white pacifico">Edit Item</h1>
    <div class="card bg-light mt-4">
        <div class="card-body">
            <form action="HomeServlet" method="post">
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="itemName">Item Name:</label>
                            <input type="text" class="form-control" id="itemName" name="itemName" required maxlength="30" value="${soldItem.itemName}">
                        </div>
                        <div class="form-group">
                            <label for="itemDescription">Description:</label>
                            <textarea class="form-control" id="itemDescription" name="itemDescription" required rows="1" maxlength="300">${soldItem.itemDescription}</textarea>
                        </div>
                        <div class="form-group">
                            <label for="category">Category:</label>
                            <select class="form-control" id="category" name="category" required>
                                <option value="" selected disabled>Select a category</option>
                                <c:forEach items="${categories}" var="category">
                                    <option value="${category.categoryNumber}" <c:if test="${category.categoryNumber == itemCategoryId}">selected</c:if>>${category.categoryName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="startingPrice">Starting Price:</label>
                            <input type="number" class="form-control" id="startingPrice" name="startingPrice" required value="${soldItem.startingPrice}">
                        </div>
                        <div class="form-group">
                            <label for="auctionStartDate">Auction Start Date:</label>
                            <input type="date" class="form-control" id="auctionStartDate" name="auctionStartDate" required value="${soldItem.auctionStartDate}">
                        </div>
                        <div class="form-group">
                            <label for="auctionEndDate">Auction End Date:</label>
                            <input type="date" class="form-control" id="auctionEndDate" name="auctionEndDate" required value="${soldItem.auctionEndDate}">
                        </div>
                    </div>
                </div>
                <div class="container mb-3">
                    <div class="row justify-content-center">
                        <div class="col-md-9">
                            <div class="card m-4 bg-carton shadow-lg text-white">
                                <div class="card-body">
                                    <h4 class="text-center mb-3">Pick Up</h4>
                                    <div class="form-group row">
                                        <label class="col-md-3 col-form-label" for="pickupStreet">Street:</label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control form-control-sm" id="pickupStreet" name="pickupStreet" value="${pickUp.street}" required>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-md-3 col-form-label" for="pickupPostalCode">Postal Code:</label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control form-control-sm" id="pickupPostalCode" name="pickupPostalCode" value="${pickUp.postalCode}" required>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-md-3 col-form-label" for="pickupCity">City:</label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control form-control-sm" id="pickupCity" name="pickupCity" value="${pickUp.city}" required>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <button type="submit" class="btn btn-primary btn-block">Save Changes</button>
                <a href="HomeServlet" class="btn btn-secondary btn-block mt-2">Cancel</a>
            </form>
        </div>
    </div>
</div>
</body>
</html>
