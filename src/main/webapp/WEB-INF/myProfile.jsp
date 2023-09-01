<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Encheres.org - My Profile</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .btn-primary {
            background-color: #005fb0!important;
            border-color: #007bff;
        }
        .highlight-info {
            color: #005fb0;
        }
    </style>
</head>
<body>
    <%@ include file="header.jsp"%>
    <div class="container mt-4">
        <h2 class="text-center text-white">My profile</h2>
        <div class="card mt-4">
            <div class="card-body">
                <div class="row">
                    <div class="col-md-6">
                        <p><span class="highlight-info">Username: </span> ${sessionScope.user.username}</p>
                        <p><span class="highlight-info">First Name: </span> ${user.firstName}</p>
                        <p><span class="highlight-info">Last Name: </span> ${user.lastName}</p>
                        <p><span class="highlight-info">Email Address: </span> ${user.email}</p>
                    </div>
                    <div class="col-md-6">
                    	<p><span class="highlight-info">Telephone: </span>${user.phoneNumber}</p>
                        <p><span class="highlight-info">Street: </span>${user.street}</p>
                        <p><span class="highlight-info">Postal Code: </span>${user.postalCode}</p>
                        <p><span class="highlight-info">City: </span>${user.city}</p>
                    </div>
                </div>
                <c:if test="${sessionScope.user.username == user.username}">
                    <a class="btn btn-primary btn-block" href="${pageContext.request.contextPath}/EditMyProfileServlet">Edit my profile</a>
                </c:if>
                 
            </div>
        </div>
    </div>
</body>
</html>
