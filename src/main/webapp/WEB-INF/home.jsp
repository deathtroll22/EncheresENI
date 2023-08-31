<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Encheres.org - home</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <%@ include file="header.jsp" %>
    <div class="container mt-4">
        <h1 class="text-center">Liste des Enchères</h1>
        <div class="card mt-4">
            <div class="card-body">
                <h4 class="text-center">Filtres et Recherche</h4>
                <form>
                    <div class="form-group">
                        <label for="searchInput">Le nom de l'article contient:</label>
                        <input type="text" class="form-control" id="searchInput" placeholder="Le nom de l'article contient">
                    </div>
                    <div class="form-group">
                        <label for="categorySelect">Catégorie:</label>
                        <select class="form-control" id="categorySelect">
                            <option value="categorie1">Informatique</option>
                            <option value="categorie2">Ameublement</option>
                            <option value="categorie3">Vêtements</option>
                            <option value="categorie4">Sport & Loisirs</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Choisir un type:</label>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="typeRadio" id="typeRadio1" checked>
                            <label class="form-check-label" for="typeRadio1">Achats</label>
                            <div class="ml-4">
                                <input type="checkbox" class="form-check-input" id="checkbox1"
                                    <c:if test="${not empty param.typeRadio && param.typeRadio == 'typeRadio1'}">disabled</c:if>>
                                <label class="form-check-label" for="checkbox1">Encheres ouvertes</label>
                            </div>
                            <div class="ml-4">
                                <input type="checkbox" class="form-check-input" id="checkbox2"
                                    <c:if test="${not empty param.typeRadio && param.typeRadio == 'typeRadio1'}">disabled</c:if>>
                                <label class="form-check-label" for="checkbox2">Mes enchères en cours</label>
                            </div>
                            <div class="ml-4">
                                <input type="checkbox" class="form-check-input" id="checkbox3"
                                    <c:if test="${not empty param.typeRadio && param.typeRadio == 'typeRadio1'}">disabled</c:if>>
                                <label class="form-check-label" for="checkbox3">Mes enchères remportées</label>
                            </div>
                        </div>
                        <div class="form-check mt-3">
                            <input class="form-check-input" type="radio" name="typeRadio" id="typeRadio2">
                            <label class="form-check-label" for="typeRadio2">Mes ventes</label>
                            <div class="ml-4">
                                <input type="checkbox" class="form-check-input" id="checkbox4"
                                    <c:if test="${not empty param.typeRadio && param.typeRadio == 'typeRadio2'}">disabled</c:if>>
                                <label class="form-check-label" for="checkbox4">Mes ventes en cours</label>
                            </div>
                            <div class="ml-4">
                                <input type="checkbox" class="form-check-input" id="checkbox5"
                                    <c:if test="${not empty param.typeRadio && param.typeRadio == 'typeRadio2'}">disabled</c:if>>
                                <label class="form-check-label" for="checkbox5">Vente non débutées</label>
                            </div>
                            <div class="ml-4">
                                <input type="checkbox" class="form-check-input" id="checkbox6"
                                    <c:if test="${not empty param.typeRadio && param.typeRadio == 'typeRadio2'}">disabled</c:if>>
                                <label class="form-check-label" for="checkbox6">Ventes terminées</label>
                            </div>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary">Rechercher</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
