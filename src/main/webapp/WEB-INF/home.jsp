<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Encheres.org - Home</title>
<link rel="stylesheet"
    href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    
    <style>
    .btn-primary {
        background-color: #005fb0!important;
        border-color: #007bff;
    </style>
</head>
<body>
    <%@ include file="header.jsp"%>
    <div class="container mt-4">
        <h1 class="text-center text-white pacifico">Auction List</h1>
        <div class="card mt-4">
            <div class="card-body">
                <h2 class="text-center text-secondary mb-3 pacifico">Filters</h2>
                <form>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <input
                                    type="text" class="form-control" id="searchInput"
                                    placeholder="Search by item name">
                            </div>
                            <div class="form-group">
                            <select
                                    class="form-control" id="categorySelect">
                                    <option value="category1">Computers</option>
                                    <option value="category2">Furniture</option>
                                    <option value="category3">Clothing</option>
                                    <option value="category4">Sports & Leisure</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-6 row align-items-end pb-3">
                            <div class="col-6">
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="typeRadio"
                                        id="typeRadio1" checked> <label
                                        class="form-check-label" for="typeRadio1">Purchases</label>
                                </div>
                                <div class="form-check">
                                    <input type="checkbox" class="form-check-input" id="checkbox1"
                                        <c:if test="${not empty param.typeRadio && param.typeRadio == 'typeRadio1'}">disabled</c:if>>
                                    <label class="form-check-label" for="checkbox1">Open Auctions</label>
                                </div>
                                <div class="form-check">
                                    <input type="checkbox" class="form-check-input" id="checkbox2"
                                        <c:if test="${not empty param.typeRadio && param.typeRadio == 'typeRadio1'}">disabled</c:if>>
                                    <label class="form-check-label" for="checkbox2">My Ongoing Bids</label>
                                </div>
                                <div class="form-check">
                                    <input type="checkbox" class="form-check-input" id="checkbox3"
                                        <c:if test="${not empty param.typeRadio && param.typeRadio == 'typeRadio1'}">disabled</c:if>>
                                    <label class="form-check-label" for="checkbox3">My Won Auctions</label>
                                </div>
                            </div>
                            <div class="col-6">
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="typeRadio"
                                        id="typeRadio2"> <label class="form-check-label"
                                        for="typeRadio2">My Sales</label>
                                </div>
                                <div class="form-check">
                                    <input type="checkbox" class="form-check-input" id="checkbox4"
                                        <c:if test="${not empty param.typeRadio && param.typeRadio == 'typeRadio2'}">disabled</c:if>>
                                    <label class="form-check-label" for="checkbox4">My Ongoing Sales</label>
                                </div>
                                <div class="form-check">
                                    <input type="checkbox" class="form-check-input" id="checkbox5"
                                        <c:if test="${not empty param.typeRadio && param.typeRadio == 'typeRadio2'}">disabled</c:if>>
                                    <label class="form-check-label" for="checkbox5">Sales Not Started</label>
                                </div>
                                <div class="form-check">
                                    <input type="checkbox" class="form-check-input" id="checkbox6"
                                        <c:if test="${not empty param.typeRadio && param.typeRadio == 'typeRadio2'}">disabled</c:if>>
                                    <label class="form-check-label" for="checkbox6">Completed Sales</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary btn-block">Search</button>
                </form>
            </div>
        </div>
        <div class="card col-12 bg-white my-4 py-4">
            <div class="row">
                <div class="col-md-6">
                    <div class="card shadow bg-light mb-4">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-4">
                                    <img src="${pageContext.request.contextPath}/img/auction.png"
                                        alt="Table" class="img-fluid rounded">
                                </div>
                                <div class="col-md-8">
                                    <h4>Auction Title</h4>
                                    <p>Price: 100 €</p>
                                    <p>Auction Ends: August 25, 2023</p>
                                    <p>Seller: Seller's Name</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="card shadow bg-light mb-4">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-4">
                                    <img src="${pageContext.request.contextPath}/img/auction.png"
                                        alt="Table" class="img-fluid rounded">
                                </div>
                                <div class="col-md-8">
                                    <h4>Auction Title</h4>
                                    <p>Price: 100 €</p>
                                    <p>Auction Ends: August 25, 2023</p>
                                    <p>Seller: Seller's Name</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="card shadow bg-light mb-4">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-4">
                                    <img src="${pageContext.request.contextPath}/img/auction.png"
                                        alt="Table" class="img-fluid rounded">
                                </div>
                                <div class="col-md-8">
                                    <h4>Auction Title</h4>
                                    <p>Price: 100 €</p>
                                    <p>Auction Ends: August 25, 2023</p>
                                    <p>Seller: Seller's Name</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="card shadow bg-light mb-4">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-4">
                                    <img src="${pageContext.request.contextPath}/img/auction.png"
                                        alt="Table" class="img-fluid rounded">
                                </div>
                                <div class="col-md-8">
                                    <h4>Auction Title</h4>
                                    <p>Price: 100 €</p>
                                    <p>Auction Ends: August 25, 2023</p>
                                    <p>Seller: Seller's Name</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
