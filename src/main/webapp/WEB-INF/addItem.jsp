<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Encheres.org</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa; /* bg-light */
        }
        .centered-card {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .card {
            padding: 20px;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <h1 class="text-center">Ajouter un article</h1>
            <form action="insertItemServlet" method="post">
                <div class="form-group">
                    <label for="itemName">Nom de l'article :</label>
                    <input type="text" class="form-control" id="itemName" name="itemName" required>
                </div>
                <div class="form-group">
                    <label for="itemDescription">Description :</label>
                    <textarea class="form-control" id="itemDescription" name="itemDescription" required></textarea>
                </div>
                <div class="form-group">
                    <label for="auctionStartDate">Date de début d'enchères :</label>
                    <input type="date" class="form-control" id="auctionStartDate" name="auctionStartDate" required>
                </div>
                <div class="form-group">
                    <label for="auctionEndDate">Date de fin d'enchères :</label>
                    <input type="date" class="form-control" id="auctionEndDate" name="auctionEndDate" required>
                </div>
                <div class="form-group">
                    <label for="startingPrice">Prix de départ :</label>
                    <input type="number" class="form-control" id="startingPrice" name="startingPrice" required>
                </div>
                <div class="form-group">
                    <label for="category">Catégorie :</label>
                    <select class="form-control" id="category" name="category" required>
                        <option value="" selected disabled>Choisir une catégorie</option>
                        <option value="1">Catégorie 1</option>
                        <option value="2">Catégorie 2</option>
                        <option value="3">Catégorie 3</option>
                        <!-- Ajoutez d'autres options selon vos catégories -->
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Ajouter</button>
            </form>
        </div>
    </div>
</div>

</body>
</html>
