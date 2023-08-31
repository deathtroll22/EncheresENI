<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Encheres.org - home</title>
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
		<h2 class="text-center text-white">Liste des Enchères</h2>
		<div class="card mt-4">
			<div class="card-body">
				<h4 class="text-center text-secondary mb-3">Filtres et Recherche</h4>
				<form>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<input
									type="text" class="form-control" id="searchInput"
									placeholder="Le nom de l'article contient">
							</div>
							<div class="form-group">
							<select
									class="form-control" id="categorySelect">
									<option value="categorie1">Informatique</option>
									<option value="categorie2">Ameublement</option>
									<option value="categorie3">Vêtements</option>
									<option value="categorie4">Sport & Loisirs</option>
								</select>
							</div>
						</div>
						<div class="col-md-6 row align-items-end pb-3">
							<div class="col-6">
								<div class="form-check">
									<input class="form-check-input" type="radio" name="typeRadio"
										id="typeRadio1" checked> <label
										class="form-check-label" for="typeRadio1">Achats</label>
								</div>
								<div class="form-check">
									<input type="checkbox" class="form-check-input" id="checkbox1"
										<c:if test="${not empty param.typeRadio && param.typeRadio == 'typeRadio1'}">disabled</c:if>>
									<label class="form-check-label" for="checkbox1">Encheres
										ouvertes</label>
								</div>
								<div class="form-check">
									<input type="checkbox" class="form-check-input" id="checkbox2"
										<c:if test="${not empty param.typeRadio && param.typeRadio == 'typeRadio1'}">disabled</c:if>>
									<label class="form-check-label" for="checkbox2">Mes
										enchères en cours</label>
								</div>
								<div class="form-check">
									<input type="checkbox" class="form-check-input" id="checkbox3"
										<c:if test="${not empty param.typeRadio && param.typeRadio == 'typeRadio1'}">disabled</c:if>>
									<label class="form-check-label" for="checkbox3">Mes
										enchères remportées</label>
								</div>
							</div>
							<div class="col-6">
								<div class="form-check">
									<input class="form-check-input" type="radio" name="typeRadio"
										id="typeRadio2"> <label class="form-check-label"
										for="typeRadio2">Mes ventes</label>
								</div>
								<div class="form-check">
									<input type="checkbox" class="form-check-input" id="checkbox4"
										<c:if test="${not empty param.typeRadio && param.typeRadio == 'typeRadio2'}">disabled</c:if>>
									<label class="form-check-label" for="checkbox4">Mes
										ventes en cours</label>
								</div>
								<div class="form-check">
									<input type="checkbox" class="form-check-input" id="checkbox5"
										<c:if test="${not empty param.typeRadio && param.typeRadio == 'typeRadio2'}">disabled</c:if>>
									<label class="form-check-label" for="checkbox5">Vente
										non débutées</label>
								</div>
								<div class="form-check">
									<input type="checkbox" class="form-check-input" id="checkbox6"
										<c:if test="${not empty param.typeRadio && param.typeRadio == 'typeRadio2'}">disabled</c:if>>
									<label class="form-check-label" for="checkbox6">Ventes
										terminées</label>
								</div>
							</div>
						</div>
					</div>
					<button type="submit" class="btn btn-primary btn-block">Rechercher</button>
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
									<img src="${pageContext.request.contextPath}/img/table.jpg"
										alt="Table" class="img-fluid rounded">
								</div>
								<div class="col-md-8">
									<h4>Titre de l'enchère</h4>
									<p>Prix : 100 €</p>
									<p>Fin de l'enchère : 25 Août 2023</p>
									<p>Vendeur : Nom du vendeur</p>
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
									<img src="${pageContext.request.contextPath}/img/table.jpg"
										alt="Table" class="img-fluid rounded">
								</div>
								<div class="col-md-8">
									<h4>Titre de l'enchère</h4>
									<p>Prix : 100 €</p>
									<p>Fin de l'enchère : 25 Août 2023</p>
									<p>Vendeur : Nom du vendeur</p>
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
									<img src="${pageContext.request.contextPath}/img/table.jpg"
										alt="Table" class="img-fluid rounded">
								</div>
								<div class="col-md-8">
									<h4>Titre de l'enchère</h4>
									<p>Prix : 100 €</p>
									<p>Fin de l'enchère : 25 Août 2023</p>
									<p>Vendeur : Nom du vendeur</p>
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
									<img src="${pageContext.request.contextPath}/img/table.jpg"
										alt="Table" class="img-fluid rounded">
								</div>
								<div class="col-md-8">
									<h4>Titre de l'enchère</h4>
									<p>Prix : 100 €</p>
									<p>Fin de l'enchère : 25 Août 2023</p>
									<p>Vendeur : Nom du vendeur</p>
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
