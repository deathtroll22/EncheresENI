package fr.eni.enchereseni.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.RequestDispatcher;
import fr.eni.enchereseni.bll.AuctionManager;
import fr.eni.enchereseni.bll.AuctionManagerException;
import fr.eni.enchereseni.bll.AuctionManagerSing;
import fr.eni.enchereseni.bo.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	// Appel à la logique métier pour la création du compte utilisateur
    AuctionManager manager = AuctionManagerSing.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("pseudo");
        String password = request.getParameter("password");

        try {
            User user = manager.login(username, password);

            if (user != null) {
                // Connexion réussie
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                response.sendRedirect(request.getContextPath() + "/HomeServlet");
            } else {
                // Informations de connexion incorrectes
                request.setAttribute("error", "Invalid username or password.");
                request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            }
        } catch (AuctionManagerException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error during login.");
        }
    }
}
