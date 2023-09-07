package fr.eni.enchereseni.ihm;

import java.io.IOException;
import fr.eni.enchereseni.bll.SoldItemManager;
import fr.eni.enchereseni.bo.SoldItem;
import fr.eni.enchereseni.bll.ManagerException;
import fr.eni.enchereseni.bll.ManagerSing;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class EditItemServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupérez l'ID de l'article à éditer depuis les paramètres de la requête
        int itemId = Integer.parseInt(request.getParameter("itemId"));

        try {
            // Obtenez l'article à éditer en utilisant l'ID
            SoldItemManager manager = ManagerSing.getSoldItemManager();
            SoldItem itemToEdit = manager.getSoldItemById(itemId);

            // Placez l'article à éditer dans la session ou les attributs de la requête
            request.setAttribute("itemToEdit", itemToEdit);

            // Redirigez l'utilisateur vers la page d'édition
            request.getRequestDispatcher("/WEB-INF/editItem.jsp").forward(request, response);
        } catch (ManagerException e) {
            // Gérez les exceptions liées à l'édition de l'article
            request.setAttribute("errorMessage", "Error editing item: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/errorPage.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Traitez ici la soumission du formulaire d'édition de l'article
    }
}
