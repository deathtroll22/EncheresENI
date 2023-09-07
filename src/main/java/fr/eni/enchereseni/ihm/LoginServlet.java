package fr.eni.enchereseni.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import fr.eni.enchereseni.bll.UserManager;
import fr.eni.enchereseni.bll.ManagerException;
import fr.eni.enchereseni.bll.ManagerSing;
import fr.eni.enchereseni.bo.User;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	// Appel à la logique métier pour la création du compte utilisateur
    UserManager manager = ManagerSing.getUserManager();

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

                // Vérifie si la case "Remember Me" est cochée
                if (request.getParameter("rememberMe") != null) {
                    // Crée un cookie "rememberMe" avec l'ID de l'utilisateur
                    Cookie rememberMeCookie = new Cookie("rememberMe", String.valueOf(user.getUserID()));
                    // Définit la durée de validité du cookie (30 jours ici)
                    rememberMeCookie.setMaxAge(30 * 24 * 60 * 60);
                    // Ajoute le cookie à la réponse
                    response.addCookie(rememberMeCookie);
                }

                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                response.sendRedirect(request.getContextPath() + "/HomeServlet");
            } else {
                // Informations de connexion incorrectes
                request.setAttribute("error", "Invalid username or password.");
                request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            }
        } catch (ManagerException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error during login.");
        }
    }
}
