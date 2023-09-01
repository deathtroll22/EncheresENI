<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Encheres.org</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link
	href="https://fonts.googleapis.com/css2?family=Fleur+De+Leah&display=swap"
	rel="stylesheet">
<style>
body {
	background-color: #005fb0; /* bg-light */
}

.navbar-brand {
	position: relative;
	/* Ajoutez ceci pour permettre le positionnement absolu */
}

.navbar-logo-text {
	font-family: 'Fleur De Leah', cursive;
	color: #005fb0;
	font-size: 65px;
	position: absolute;
	top: 56%; /* Déplace le texte vers le bas de moitié de sa hauteur */
	transform: translateY(-50%);
	/* Ramène le texte de moitié de sa hauteur vers le haut */
}

.navbar-nav {
	margin-top: 25px;
	/* Ajustez cette valeur pour décaler les items du menu */
}

.text-blue-custom {
	color: #005fb0;
}
</style>
</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-light bg-white">
		<div class="container">
			<a class="navbar-brand"
				href="${pageContext.request.contextPath}/HomeServlet"> <img
				src="${pageContext.request.contextPath}/img/auction.png" alt="Logo"
				style="width: 70px;"> <span class="navbar-logo-text">Enchères.org</span>
			</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarNav" aria-controls="navbarNav"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav ml-auto">
					<%-- Si l'utilisateur est connecté, afficher les liens de menu --%>
					<c:if test="${sessionScope.user != null}">
						<li class="nav-item d-none d-md-block"><span class="nav-link"
							style="color: #005fb0; font-weight: bold;"> Hello
								${sessionScope.user.username} ! </span></li>

						<li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath}/HomeServlet">Enchères</a>
						</li>
						<li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath}/SellItemServlet">Vendre un
								article</a></li>
						<li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath}/MyProfileServlet">Mon
								profil</a></li>
						<li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath}/LogoutServlet">Déconnexion</a>
						</li>
					</c:if>
					<%-- Si l'utilisateur n'est pas connecté, afficher les liens de connexion --%>
					<c:if test="${sessionScope.user == null}">
						<li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath}/LoginServlet">Login/Create
								Account</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</nav>


	<!-- Le reste de votre contenu de page ici -->

</body>
</html>
