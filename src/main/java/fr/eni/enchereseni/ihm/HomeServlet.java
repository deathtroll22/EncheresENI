package fr.eni.enchereseni.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import fr.eni.enchereseni.bll.SoldItemManager;
import fr.eni.enchereseni.bll.UserManager;
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
       
    	// Si l'utilisateur n'est pas trouvé dans le cookie, récupérez-le depuis la session ou l'authentification
    	User user = getUserFromSessionOrAuthentication(request);
        
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

    // Méthode pour récupérer l'utilisateur à partir du cookie "Remember Me"
    private User getUserFromRememberMeCookie(HttpServletRequest request) throws ManagerException {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ((cookie.getName().equals("rememberMe"))) {
                    String rememberMeValue = cookie.getValue();
                    // Utilisez cette valeur (ID de l'utilisateur) pour récupérer l'utilisateur
                    // depuis votre gestionnaire d'utilisateurs;
                }
            }
        }
        return null; // Retourne null si le cookie "Remember Me" n'est pas trouvé
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = getUserFromSessionOrAuthentication(request); // Remplacez cette ligne par la récupération de l'utilisateur
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


    private User getUserFromSessionOrAuthentication(HttpServletRequest request) {
    	 HttpSession session = request.getSession();

    	 User user = null;
    	 user = (User) session.getAttribute("user");
    	 
     	if (user == null) {
     		System.out.println("Pas d'utilisateur en session - rechercher cookie");
			try {
				user = getUserFromRememberMeCookie(request);
			} catch (ManagerException e) {
				e.printStackTrace();
			}
	        
	    }
     	
     	System.out.println("getUserFromSessionOrAuthentication: " + user);
    	 
    	 return user;
    }
}
