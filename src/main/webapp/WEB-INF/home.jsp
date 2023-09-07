<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
    <h1 class="text-center text-white pacifico">Auction List</h1>
    <div class="card mt-4">
        <div class="card-body pt-2 pb-0">
            <h2 class="text-center text-secondary mb-4 pacifico">Filters</h2>
            <form method="post" action="HomeServlet">
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <input type="text" class="form-control" id="searchInput" name="searchInput"
                                   placeholder="Search by item name">
                        </div>
                        <div class="form-group">
                            <select class="form-control" id="categorySelect" name="categorySelect">
                                <option value="">-- Select Category --</option>
                                <c:forEach var="category" items="${categories}">
                                    <option value="${category.categoryNumber}">${category.categoryName}</option>
                                </c:forEach>
                            </select>
                            <input type="hidden" name="categorySelectHidden" id="categorySelectHidden" value="">
                        </div>
						</div>
<c:if test="${sessionScope.user != null}">
    <div class="col-md-6 row align-items-end pb-3">
        <div class="col-6">
            <div class="form-check">
                <label class="form-check-label" for="typeRadio1"><strong>Purchases</strong></label>
            </div>
            <div class="form-check">
                <input type="radio" class="form-check-input" id="radio1"
                    name="typeRadio" value="openAuctions"
                    <c:if test="${param.typeRadio == 'typeRadio1'}">checked</c:if>>
                <label class="form-check-label" for="radio1">Open Auctions</label>
            </div>
            <div class="form-check">
                <input type="radio" class="form-check-input" id="radio2"
                    name="typeRadio" value="myOngoingBids"
                    <c:if test="${param.typeRadio == 'typeRadio2'}">checked</c:if>>
                <label class="form-check-label" for="radio2">My Ongoing Bids</label>
            </div>
            <div class="form-check">
                <input type="radio" class="form-check-input" id="radio3"
                    name="typeRadio" value="myWonAuctions"
                    <c:if test="${param.typeRadio == 'typeRadio3'}">checked</c:if>>
                <label class="form-check-label" for="radio3">My Won Auctions</label>
            </div>
        </div>
        <div class="col-6">
            <div class="form-check">
                <label class="form-check-label" for="typeRadio2"><strong>My Sales</strong></label>
            </div>
            <div class="form-check">
                <input type="radio" class="form-check-input" id="radio4"
                    name="typeRadio" value="myOngoingsales"
                    <c:if test="${param.typeRadio == 'typeRadio4'}">checked</c:if>>
                <label class="form-check-label" for="radio4">My Ongoing Sales</label>
            </div>
            <div class="form-check">
                <input type="radio" class="form-check-input" id="radio5"
                    name="typeRadio" value="salesNotStarted"
                    <c:if test="${param.typeRadio == 'typeRadio5'}">checked</c:if>>
                <label class="form-check-label" for="radio5">Sales Not Started</label>
            </div>
            <div class="form-check">
                <input type="radio" class="form-check-input" id="radio6"
                    name="typeRadio" value="completedSales"
                    <c:if test="${param.typeRadio == 'typeRadio6'}">checked</c:if>>
                <label class="form-check-label" for="radio6">Completed Sales</label>
            </div>
        </div>
    </div>
</c:if>


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
							<a
								href="<c:url value='/ItemServlet'><c:param name='itemId' value='${item.itemNumber}'/></c:url>"
								class="card-link"
								<c:if test="${sessionScope.user == null}">data-toggle="modal" data-target="#loginModal"</c:if>>
								<div class="card-body">
									<div class="row">
										<div class="col-md-4 px-0 text-center">
											<img
												src=" https://picsum.photos/350/200?random=${item.itemNumber}"
												alt="Table" class="img-fluid rounded img-max-height">
										</div>
										<div class="col-md-8 pt-4 pt-md-0 px-4">
											<h4>
												<c:out value="${item.itemName}" />
											</h4>
											<c:choose>
												<c:when test="${item.sellingPrice > item.startingPrice}">
													<p>
														Price:
														<c:out value="${item.sellingPrice}" />
														€
													</p>
												</c:when>
												<c:otherwise>
													<p>
														Price:
														<c:out value="${item.startingPrice}" />
														pts
													</p>
												</c:otherwise>
											</c:choose>
											<p>
												Auction Ends :
												<fmt:formatDate value="${item.auctionEndDate}"
													pattern="dd MM yyyy" />
											</p>
											<p>
												Seller:
												<c:out value="${item.user.username}" />
											</p>
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
<script>
    // Fonction pour mettre à jour le champ caché avec la catégorie sélectionnée
    function updateCategoryHiddenField() {
        var categorySelect = document.getElementById("categorySelect");
        var categorySelectHidden = document.getElementById("categorySelectHidden");
        var selectedCategory = categorySelect.options[categorySelect.selectedIndex].value;
        categorySelectHidden.value = selectedCategory;
    }

    // Attachez la fonction à l'événement onchange du sélecteur de catégorie
    document.getElementById("categorySelect").addEventListener("change", updateCategoryHiddenField);
</script>

</html>
