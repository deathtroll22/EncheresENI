package fr.eni.enchereseni.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import fr.eni.enchereseni.bll.SoldItemManager; // Assure-toi d'importer le SoldItemManager
import fr.eni.enchereseni.bll.ManagerException;
import fr.eni.enchereseni.bo.SoldItem;
import fr.eni.enchereseni.bo.User;
import fr.eni.enchereseni.bll.ManagerSing; // Assure-toi d'importer le ManagerSing

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = getUserFromSessionOrAuthentication(); // Remplace cette ligne par la récupération de l'utilisateur
        SoldItemManager soldItemManager = ManagerSing.getSoldItemManager(); // Utilise ManagerSing pour obtenir l'instance de SoldItemManager
        List<SoldItem> allItems = null;
        try {
            allItems = soldItemManager.getAllItems();
        } catch (ManagerException e) {
            // TODO: Gérer l'exception appropriée
            e.printStackTrace();
        }
        request.setAttribute("allItems", allItems); // Ajoute la liste d'articles à l'objet de requête

        request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
    }

    private User getUserFromSessionOrAuthentication() {
        User user = new User();
        return user;
    }
}
