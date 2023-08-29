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
                <h2 class="card-header text-center">Login</h2>
                <div class="card-body">
                    <form action="LoginServlet" method="post">
                        <div class="form-row">
                        	<div class="form-group">
                            	<label for="pseudo">Username :</label>
                            	<input type="text" class="form-control" id="pseudo" name="pseudo" required>
                       	 	</div>
                           
                            <div class="form-group">
                            	<label for="mot_de_passe">Password :</label>
                           		<input type="password" class="form-control" id="password" name="password" required>
                        	</div>
                        </div>
                       
                        <button type="submit" class="btn btn-primary">Login</button>
                        <button type="button" class="btn btn-secondary" onclick="window.location.href='accueil.jsp'">Cancel</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
