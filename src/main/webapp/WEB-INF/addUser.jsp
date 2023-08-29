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
            <h1 class="text-center">Création d'un nouveau compte</h1>
            <form action="insertUserServlet" method="post">
                <div class="form-group">
                    <label for="pseudo">Pseudo :</label>
                    <input type="text" class="form-control" id="pseudo" name="pseudo" required>
                </div>
                <div class="form-group">
                    <label for="nom">Nom :</label>
                    <input type="text" class="form-control" id="nom" name="nom" required>
                </div>
                <div class="form-group">
                    <label for="prenom">Prénom :</label>
                    <input type="text" class="form-control" id="prenom" name="prenom" required>
                </div>
                <div class="form-group">
                    <label for="email">Email :</label>
                    <input type="email" class="form-control" id="email" name="email" required>
                </div>
                <div class="form-group">
                    <label for="telephone">Téléphone :</label>
                    <input type="tel" class="form-control" id="telephone" name="telephone">
                </div>
                <div class="form-group">
                    <label for="rue">Rue :</label>
                    <input type="text" class="form-control" id="rue" name="rue" required>
                </div>
                <div class="form-group">
                    <label for="code_postal">Code Postal :</label>
                    <input type="text" class="form-control" id="code_postal" name="code_postal" required>
                </div>
                <div class="form-group">
                    <label for="ville">Ville :</label>
                    <input type="text" class="form-control" id="ville" name="ville" required>
                </div>
                <div class="form-group">
                    <label for="mot_de_passe">Mot de passe :</label>
                    <input type="password" class="form-control" id="mot_de_passe" name="mot_de_passe" required>
                </div>
                <div class="form-group">
                    <label for="confirmer_mot_de_passe">Confirmer le mot de passe :</label>
                    <input type="password" class="form-control" id="confirmer_mot_de_passe" name="confirmer_mot_de_passe" required>
                </div>
                <button type="submit" class="btn btn-primary">Creer mon compte</button>
                <button type="button" class="btn btn-secondary" onclick="window.location.href='accueil.jsp'">Annuler</button>
            </form>
        </div>
    </div>
</div>

</body>
</html>
