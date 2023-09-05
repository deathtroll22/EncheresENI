<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Encheres.org - Home</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<style>
.btn-primary {
	background-color: #005fb0 !important;
	border-color: #007bff;
}

.fancy_card {
	box-shadow: 8px 14px 38px rgba(39, 44, 49, .06), 1px 3px 8px
		rgba(39, 44, 49, .03);
	transition: all .5s ease; /* back to normal */
}

.fancy_card:hover {
	box-shadow: 8px 28px 50px rgba(39, 44, 49, .07), 1px 6px 12px
		rgba(39, 44, 49, .04);
	transition: all .2s ease; /* zoom in */
	transform: translate3D(0, -1px, 0) scale(1.03);
}

.fancy_card a {
	text-decoration: none;
	color: inherit;
}

.fancy_card a:hover {
	color: #343a40;
}

.img-max-height {
	max-height: 200px;
}
.
</style>
</head>
<body>
<%@ include file="header.jsp"%>
<div class="container mt-4">
    <!-- ... (Filters form) ... -->
    
    	<h1 class="text-center text-white pacifico">Auction List</h1>
		<div class="card mt-4">
			<div class="card-body pt-2 pb-0">
				<h2 class="text-center text-secondary mb-4 pacifico">Filters</h2>
				<form>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<input type="text" class="form-control" id="searchInput"
									placeholder="Search by item name">
							</div>
							<div class="form-group">
								<select class="form-control" id="categorySelect">
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
									<label class="form-check-label" for="checkbox1">Open
										Auctions</label>
								</div>
								<div class="form-check">
									<input type="checkbox" class="form-check-input" id="checkbox2"
										<c:if test="${not empty param.typeRadio && param.typeRadio == 'typeRadio1'}">disabled</c:if>>
									<label class="form-check-label" for="checkbox2">My
										Ongoing Bids</label>
								</div>
								<div class="form-check">
									<input type="checkbox" class="form-check-input" id="checkbox3"
										<c:if test="${not empty param.typeRadio && param.typeRadio == 'typeRadio1'}">disabled</c:if>>
									<label class="form-check-label" for="checkbox3">My Won
										Auctions</label>
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
									<label class="form-check-label" for="checkbox4">My
										Ongoing Sales</label>
								</div>
								<div class="form-check">
									<input type="checkbox" class="form-check-input" id="checkbox5"
										<c:if test="${not empty param.typeRadio && param.typeRadio == 'typeRadio2'}">disabled</c:if>>
									<label class="form-check-label" for="checkbox5">Sales
										Not Started</label>
								</div>
								<div class="form-check">
									<input type="checkbox" class="form-check-input" id="checkbox6"
										<c:if test="${not empty param.typeRadio && param.typeRadio == 'typeRadio2'}">disabled</c:if>>
									<label class="form-check-label" for="checkbox6">Completed
										Sales</label>
								</div>
							</div>
						</div>
					</div>
					<button type="submit" class="btn btn-primary btn-block">Search</button>
				</form>
			</div>
		</div>

    <!-- Liste des enchères en cours -->
    <div class="card col-12 bg-white my-4 py-4">
        <div class="row">
          <c:forEach var="item" items="${allItems}">
    <div class="col-md-6">
        <div class="card shadow bg-light mb-4 fancy_card">
            <a href="<c:url value='/ItemServlet'><c:param name='itemId' value='${item.itemNumber}'/></c:url>"
                class="card-link"
                <c:if test="${sessionScope.user == null}">data-toggle="modal" data-target="#loginModal"</c:if>>
                <div class="card-body">
                                <div class="row">
                                    <div class="col-md-4">
                                        <img src="${pageContext.request.contextPath}/img/auction.png"
                                            alt="Table" class="img-fluid rounded img-max-height">
                                    </div>
                                    <div class="col-md-8">
                                        <h4>
                                            <c:out value="${item.itemName}" />
                                        </h4>
                                        <c:choose>
                                            <c:when test="${item.sellingPrice > item.startingPrice}">
                                                <p>Price: <c:out value="${item.sellingPrice}" /> €</p>
                                            </c:when>
                                            <c:otherwise>
                                                <p>Price: <c:out value="${item.startingPrice}" /> pts</p>
                                            </c:otherwise>
                                        </c:choose>
                                        <p>Auction Ends : <fmt:formatDate value="${item.auctionEndDate}" pattern="dd MM yyyy" /></p>
                                        <p>Seller : </p>
                                        <!-- Autres informations sur l'article -->
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>
