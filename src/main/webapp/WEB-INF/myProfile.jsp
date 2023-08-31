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
                        <p><span class="highlight-info">Username:</span> ${sessionScope.user.username}</p>
                        <p><span class="highlight-info">First Name:</span> John</p>
                        <p><span class="highlight-info">Last Name:</span> Doe</p>
                        <p><span class="highlight-info">Email Address:</span> john@example.com</p>
                    </div>
                    <div class="col-md-6">
                    	<p><span class="highlight-info">Telephone:</span>0667766775</p>
                        <p><span class="highlight-info">Street:</span> 123 Main St</p>
                        <p><span class="highlight-info">Postal Code:</span> 12345</p>
                        <p><span class="highlight-info">City:</span> Anytown</p>
                    </div>
                </div>
                 <a class="btn btn-primary btn-block" href="${pageContext.request.contextPath}/EditMyProfileServlet">Edit my profile</a>
            </div>
        </div>
    </div>
</body>
</html>
