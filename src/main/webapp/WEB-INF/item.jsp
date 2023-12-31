<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.lang.Math"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Encheres.org - Item</title>
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

.image-max-height {
	max-height: 180px;
}

.bg-carton {
	background-color: #b68759 !important;
}

.p-2 {
	padding: 0 !important;
}

.bg-light-green {
	background-color: rgba(200, 255, 200, 1);
	/* Vert clair sans transparence */
}
</style>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="container mt-4">
		<h1 class="text-center text-white pacifico">${soldItem.itemName}</h1>
		<div class="col-12">
			<div class="row mt-4">
				<div class="col-md-6 mb-3 pr-md-0 w-100">
					<div
						class="card ${sessionScope.user.username == highestBidder && soldItem.auctionEndDate < currentDate ? 'bg-light-green' : ''}">
						<div class="card-body">
							<p class="text-center fw-bold">
								<strong>${soldItem.itemName}</strong>
							</p>
							<p>${soldItem.itemDescription}</p>
							<p>
								<span class="highlight-info">Category: </span>
								${itemCategory.categoryName}
							</p>
							<p>
								<span class="highlight-info">Starting Price: </span>
								<fmt:formatNumber value="${soldItem.startingPrice}"
									pattern="###,###" />
								pts
							</p>
							<p style="font-size: 110%; font-weight: bold;">
								<span class="highlight-info">Current Best Offer: </span>
								<c:choose>
									<c:when
										test="${empty highestBid or highestBid <= soldItem.startingPrice}">
										<strong>No one has bid yet. Be the first!</strong>
									</c:when>
									<c:otherwise>
										<c:out value="${highestBid}" /> pts by ${highestBidder}</c:otherwise>
								</c:choose>
							</p>

							<p>
								<c:choose>
									<c:when test="${soldItem.auctionEndDate < currentDate}">
										<span class="highlight-info">Auction End Date: </span>
										<span style="color: red;">ENDED (<fmt:formatDate
												value="${soldItem.auctionEndDate}" pattern="dd MM yyyy" />)
										</span>
									</c:when>
									<c:otherwise>
										<span class="highlight-info">Auction End Date: </span>
										<fmt:formatDate value="${soldItem.auctionEndDate}"
											pattern="dd MM yyyy" />
									</c:otherwise>
								</c:choose>
							</p>

							<div class="card p-0 bg-carton shadow-lg text-white">
								<div class="card-body">
									<h4 class="text-center mb-3">Pick Up :</h4>
									<div class="row text-center">
										<label class="col-12">${pickUp.street},
											${pickUp.postalCode}, ${pickUp.city}</label>
										<p class="col-12">Seller: ${seller.username}</p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6 w-100">
					<div
						class="card mb-3 text-center ${sessionScope.user.username == highestBidder && soldItem.auctionEndDate < currentDate ? 'bg-light-green' : ''}">
						<div class="card-body">
							<img
								src=" https://picsum.photos/400/200?random=${item.itemNumber}"
								alt="Table" class="img-fluid rounded img-max-height">
						</div>
					</div>


					<c:choose>
						<c:when
							test="${sessionScope.user.username == highestBidder && soldItem.auctionEndDate < currentDate}">
							<div class="card p-3">
								<div class="card-body pb-1">
									<img class="mb-3" alt="trophy" src="img/trophy.png"
										style="width: 100px; display: block; margin: 0 auto;">
									<h4 class="text-center mb-3" style="color: green;">You won
										this item !</h4>
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<div class="card">
								<div class="card-body p-2">
									<h2 class="text-center mb-3">My Proposal</h2>
									<form action="ItemServlet?itemId=${soldItem.itemNumber}"
										method="post">
										<input type="number" id="proposal" name="proposal"
											class="form-control" required
											value="${Math.max(soldItem.startingPrice, highestBid)}"
											min="${Math.max(soldItem.startingPrice, highestBid) + 1 }">
										<c:choose>
											<c:when
												test="${soldItem.auctionStartDate.before(currentDate)}">
												<button type="submit" class="btn btn-primary btn-block mt-3">Bid</button>
											</c:when>
											<c:otherwise>
												<button type="submit" class="btn btn-primary btn-block mt-3"
													disabled>Come back soon</button>
											</c:otherwise>
										</c:choose>
									</form>
								</div>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
		
		
		<c:if test="${soldItem.auctionStartDate.after(currentDate) && sessionScope.user.username == seller.username}">
		<div class=" col-12 p-4 ">
			<div class="row">
				<!-- Colonne pour le bouton Modifier -->
				<div class="col-md-8 p-0 px-1">
					<button class="btn btn-primary btn-block"
						onclick="window.location.href='EditItemServlet?itemId=${soldItem.itemNumber}'">Edit
						item</button>
				</div>
				<!-- Colonne pour le bouton Supprimer -->
				<div class="col-md-4 p-0 px-1">
					<button class="btn btn-danger btn-block" onclick="confirmDelete()">Delete
						article</button>
				</div>
			</div>
		</div>
		</c:if>
	</div>
</body>
<script>
    function confirmDelete() {
        if (confirm("Êtes-vous sûr de vouloir supprimer cet article ?")) {
            window.location.href = 'DeleteItemServlet?itemId=${soldItem.itemNumber}';
        }
    }
</script>
</html>
