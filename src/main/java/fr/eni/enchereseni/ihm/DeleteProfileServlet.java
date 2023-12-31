package fr.eni.enchereseni.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import fr.eni.enchereseni.bll.UserManager;
import fr.eni.enchereseni.bll.ManagerException;
import fr.eni.enchereseni.bll.ManagerSing;
import fr.eni.enchereseni.bo.User;

import java.io.IOException;

@WebServlet("/DeleteProfileServlet")
public class DeleteProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Afficher une page de confirmation de suppression (si nécessaire)
        request.getRequestDispatcher("/WEB-INF/confirmDeleteProfile.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
		User currentUser = (User) session.getAttribute("user");

		// Vérifier le mot de passe saisi
		String deletePassword = request.getParameter("deletePassword");
		if (!currentUser.getPassword().equals(deletePassword)) {
		    request.setAttribute("error", "Incorrect password. Profile not deleted.");
		    request.getRequestDispatcher("/WEB-INF/editProfile.jsp").forward(request, response);
		} else {
		    // Appel à la logique métier pour supprimer le profil utilisateur
		    UserManager userManager = ManagerSing.getUserManager();
		    userManager.deleteUser(currentUser.getUserID());

		    // Déconnexion de l'utilisateur
		    session.removeAttribute("user");
		    session.removeAttribute("connected");

		    response.sendRedirect(request.getContextPath() + "/HomeServlet");
		}
    }
}
