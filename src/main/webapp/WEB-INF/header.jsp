
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>N²auction</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css2?family=Pacifico&display=swap" rel="stylesheet">
<style>
body {
  background: linear-gradient(to bottom, rgba(0, 95, 176, 0.7) 0%, rgba(0, 95, 176, 0.2) 50%, rgba(0, 95, 176, 0.7) 100%);
}}

.navbar-brand {
    position: relative;
}
.pacifico{
 font-family: 'Pacifico', cursive;
}
.navbar-logo-text {
    font-family: 'Pacifico', cursive;
    color: #005fb0;
    font-size: 65px;
    position: absolute;
    top: 50%;
    transform: translateY(-50%);
    margin-left: 5px;
}

.navbar-logo-text .highlight {
    color: #003366;
}

.navbar-nav {
    margin-top: 25px;
}

.text-blue-custom {
    color: #003366;
}

.bg-white-custom {
    background-color: #ffffff;
}
</style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-white-custom">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/HomeServlet">
            
            <!--  <img src="${pageContext.request.contextPath}/img/auction.png" alt="Logo" style="width: 80px;">-->
            <span class="navbar-logo-text">N<span class="highlight">²</span>auction</span>
            
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <c:if test="${sessionScope.user != null}">
                    <li class="nav-item d-none d-md-block">
                        <span class="nav-link text-blue-custom font-weight-bold">
                            Hello ${sessionScope.user.username} !
                        </span>
                    </li>
                    <li class="nav-item"><a class="nav-link text-blue-custom" href="${pageContext.request.contextPath}/HomeServlet">Auctions</a></li>
                    <li class="nav-item"><a class="nav-link text-blue-custom" href="${pageContext.request.contextPath}/SellItemServlet">Sell an item</a></li>
                    <li class="nav-item"><a class="nav-link text-blue-custom" href="${pageContext.request.contextPath}/MyProfileServlet">My Profile</a></li>
                    <li class="nav-item"><a class="nav-link text-blue-custom" href="${pageContext.request.contextPath}/LogoutServlet">Logout</a></li>
                </c:if>
                <c:if test="${sessionScope.user == null}">
                    <li class="nav-item"><a class="nav-link text-blue-custom" href="${pageContext.request.contextPath}/LoginServlet">Login/Create Account</a></li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
</body>
</html>
