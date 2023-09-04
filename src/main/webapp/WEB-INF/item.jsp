<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Encheres.org - Item</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .btn-primary {
            background-color: #005fb0 !important;
            border-color: #007bff;
        }
        .highlight-info {
            color: #005fb0;
        }
        .image-max-height {
            max-height: 222px;
        }
        .bg-carton {
            background-color: #b68759!important;
        }
        .p-2 {
            padding: 0!important;
        }
    </style>
</head>
<body>
    <%@ include file="header.jsp"%>
    <div class="container mt-4">
        <h1 class="text-center text-white pacifico">Item Name</h1>
        <div class="row mt-5">
            <div class="col-md-6 pr-0">
                <div class="card">
                    <div class="card-body">
                        <h2 class="mb-2 text-center">Item Details</h2>
                        <p><span class="highlight-info">Item Name: </span> ${soldItem.itemName}</p>
                        <p><span class="highlight-info">Description: </span> ${soldItem.itemDescription}</p>
                        <p><span class="highlight-info">Category: </span> ${itemCategory}</p>
                        <p><span class="highlight-info">Current Best Offer: </span> ${currentValue} pts by Username</p>
                        <p><span class="highlight-info">Starting Price: </span> ${soldItem.startingPrice} pts</p>
                        <p><span class="highlight-info">Auction End Date: </span> ${soldItem.auctionEndDate}</p>
                          <div class="card bg-carton shadow-lg text-white">
                            <div class="card-body">
                                <h4 class="text-center mb-3">Pick Up :</h4>
                                <div class="row text-center">
                                    <label class="col-12">Street, Postal Code City</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="card mb-3 text-center">
                    <div class="card-body">
                        <img src="img/auction.png" alt="Item Photo" class="img-fluid mx-auto image-max-height">
                    </div>
                </div>
                <div class="card">
                    <div class="card-body p-2">
                        <h2 class="text-center mb-3">My Proposal</h2>
                        <form action="ItemServlet" method="post">
                            <input type="number" id="proposal" name="proposal" class="form-control" min="${currentValue}" step="10" value="${currentValue}" required>
                            <button type="submit" class="btn btn-primary btn-block mt-3">Bid</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
