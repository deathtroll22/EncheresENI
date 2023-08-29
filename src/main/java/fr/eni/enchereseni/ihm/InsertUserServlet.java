package fr.eni.enchereseni.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchereseni.dal.ConnectionProvider;
import fr.eni.enchereseni.bo.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/insertUserServlet")
public class InsertUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pseudo = request.getParameter("pseudo");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String telephone = request.getParameter("telephone");
        String rue = request.getParameter("rue");
        String codePostal = request.getParameter("code_postal");
        String ville = request.getParameter("ville");
        String motDePasse = request.getParameter("mot_de_passe");
        String confirmerMotDePasse = request.getParameter("confirmer_mot_de_passe");
        
        // Vérification du mot de passe
        if (!motDePasse.equals(confirmerMotDePasse)) {
            // Rediriger vers une page d'erreur ou afficher un message
            response.sendRedirect("error.jsp");
            return;
        }
        
        // Création d'un nouvel utilisateur avec les données
        User user = new User(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse);
        
        // Utiliser le AuctionManager pour créer le compte utilisateur
        AuctionManager auctionManager = new AuctionManager(); // Remplacez par l'instance réelle
        try {
            auctionManager.createAccount(user);
            // Rediriger vers une page de succès ou afficher un message
            response.sendRedirect("success.jsp");
        } catch (AuctionManagerException e) {
            // Rediriger vers une page d'erreur ou afficher un message d'erreur
            response.sendRedirect("error.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Gérer le doGet si nécessaire
    }
}
