package fr.eni.enchereseni.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import fr.eni.enchereseni.bll.SoldItemManager;
import fr.eni.enchereseni.bll.CategoryManager;
import fr.eni.enchereseni.bll.CategoryManagerImpl;
import fr.eni.enchereseni.bll.ManagerException;
import fr.eni.enchereseni.bo.Category;
import fr.eni.enchereseni.bo.SoldItem;
import fr.eni.enchereseni.bo.User;
import fr.eni.enchereseni.bll.ManagerSing;

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = getUserFromSessionOrAuthentication(); // Remplacez cette ligne par la récupération de l'utilisateur
        SoldItemManager soldItemManager = ManagerSing.getSoldItemManager(); // Utilisez ManagerSing pour obtenir l'instance de SoldItemManager
        List<SoldItem> allItems = null;
        try {
            allItems = soldItemManager.getAllItems();
        } catch (ManagerException e) {
            e.printStackTrace();
        }
        request.setAttribute("allItems", allItems);

        // Récupérez la liste des catégories depuis CategoryDAOImpl
        CategoryManager category = new CategoryManagerImpl();
        List<Category> categories = null;
        try {
            categories = category.getAllCategories();
        } catch (ManagerException e) {
            e.printStackTrace();
        }

        request.setAttribute("categories", categories);

        request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = getUserFromSessionOrAuthentication(); // Remplacez cette ligne par la récupération de l'utilisateur
        SoldItemManager soldItemManager = ManagerSing.getSoldItemManager(); // Utilisez ManagerSing pour obtenir l'instance de SoldItemManager
        List<SoldItem> allItems = null;

        try {
            String searchInput = request.getParameter("searchInput");
            String categorySelect = request.getParameter("categorySelect");

            // Créez une variable pour stocker la clause WHERE de la requête SQL en fonction des filtres
            String whereClause = "";

            if (searchInput != null && !searchInput.isEmpty()) {
                whereClause = "LOWER(nom_article) LIKE '%" + searchInput.toLowerCase() + "%'";
            }

            if (categorySelect != null && !categorySelect.isEmpty()) {
                int categoryId = Integer.parseInt(categorySelect);
                if (!whereClause.isEmpty()) {
                    whereClause += " AND ";
                }
                whereClause += "av.no_categorie = " + categoryId;
            }

            // Appliquez la clause WHERE à la requête SQL pour obtenir les résultats filtrés
            if (!whereClause.isEmpty()) {
                allItems = soldItemManager.getAllItemsWithFilter(whereClause);
            } else {
                allItems = soldItemManager.getAllItems();
            }
        } catch (ManagerException e) {
            e.printStackTrace();
        }

        request.setAttribute("allItems", allItems);

        CategoryManager category = new CategoryManagerImpl();
        List<Category> categories = null;
        try {
            categories = category.getAllCategories();
        } catch (ManagerException e) {
            e.printStackTrace();
        }

        request.setAttribute("categories", categories);

        request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
    }


    private User getUserFromSessionOrAuthentication() {
        User user = new User();
        return user;
    }
}
