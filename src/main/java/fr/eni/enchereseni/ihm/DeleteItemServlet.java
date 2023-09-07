package fr.eni.enchereseni.ihm;

import java.io.IOException;
import fr.eni.enchereseni.bll.SoldItemManager;
import fr.eni.enchereseni.bll.ManagerException;
import fr.eni.enchereseni.bll.ManagerSing;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class DeleteItemServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupérez l'ID de l'article à supprimer depuis les paramètres de la requête
        int itemId = Integer.parseInt(request.getParameter("itemId"));

        try {
            // Supprimez l'article en utilisant l'ID
            SoldItemManager manager = ManagerSing.getSoldItemManager();
            manager.deleteItem(itemId);

            // Redirigez l'utilisateur vers la page d'accueil (home.jsp)
            response.sendRedirect(request.getContextPath() + "/HomeServlet");
        } catch (ManagerException e) {
            // Gérez les exceptions liées à la suppression de l'article
            request.setAttribute("errorMessage", "Error deleting item: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/errorPage.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}

