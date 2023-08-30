<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Encheres.org</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Fleur+De+Leah&display=swap" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa; /* bg-light */
        }
        .navbar-logo-text {
            font-family: 'Fleur De Leah', cursive;
            color: #005fb0;
            font-size: 40px;
            margin-left: 10px; /* Add some spacing */
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-white"> <!-- Appliquer le fond blanc ici -->
    <div class="container">
        <a class="navbar-brand" href="#">
            <img src="${pageContext.request.contextPath}/img/auction.png" alt="Logo" style="width: 60px;">
            <span class="navbar-logo-text">Enchères.org</span>
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <%-- Si l'utilisateur est connecté, afficher les liens de menu --%>
                <% if (session.getAttribute("user") != null) { %>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Enchères</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Vendre un article</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Mon profil</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/LogoutServlet">Déconnexion</a>
                    </li>
                <% } else { %>
                    <%-- Si l'utilisateur n'est pas connecté, afficher les liens de connexion --%>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/LoginServlet">Login/Create Account</a>
                    </li>
                <% } %>
            </ul>
        </div>
    </div>
</nav>

<!-- Le reste de votre contenu de page ici -->

</body>
</html>
