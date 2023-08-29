<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
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
        .form-row {
            display: flex;
            justify-content: space-between;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <h2 class="card-header text-center">Create a New Account</h2>
                <div class="card-body">
                    <form action="InsertUserServlet" method="post">
                        <div class="form-group">
                            <label for="pseudo">Username :</label>
                            <input type="text" class="form-control" id="pseudo" name="pseudo" required>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="prenom">First Name :</label>
                                <input type="text" class="form-control" id="prenom" name="first_name" required>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="nom">Last Name :</label>
                                <input type="text" class="form-control" id="nom" name="name" required>
                            </div>
                            
                        </div>
                        <div class="form-group">
                            <label for="email">Email :</label>
                            <input type="email" class="form-control" id="email" name="mail" required>
                        </div>
                        <div class="form-group">
                            <label for="telephone">Phone :</label>
                            <input type="tel" class="form-control" id="telephone" name="phone">
                        </div>
                        <div class="form-group">
                            <label for="rue">Street :</label>
                            <input type="text" class="form-control" id="rue" name="street" required>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="code_postal">Postal Code :</label>
                                <input type="text" class="form-control" id="code_postal" name="post_code" required>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="ville">City :</label>
                                <input type="text" class="form-control" id="ville" name="city" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="mot_de_passe">Password :</label>
                            <input type="password" class="form-control" id="mot_de_passe" name="password" required>
                        </div>
                        <div class="form-group">
                            <label for="confirmer_mot_de_passe">Confirm Password :</label>
                            <input type="password" class="form-control" id="confirmer_mot_de_passe" name="confirmer_mot_de_passe" required>
                        </div>
                        <button type="submit" class="btn btn-primary">Create My Account</button>
                        <button type="button" class="btn btn-secondary" onclick="window.location.href='accueil.jsp'">Cancel</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
