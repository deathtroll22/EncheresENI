package fr.eni.enchereseni.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import jakarta.servlet.http.Cookie;

import fr.eni.enchereseni.bll.SoldItemManager;
import fr.eni.enchereseni.bll.UserManager;
import fr.eni.enchereseni.bll.AuctionManager;
import fr.eni.enchereseni.bll.CategoryManager;
import fr.eni.enchereseni.bll.CategoryManagerImpl;
import fr.eni.enchereseni.bll.ManagerException;
import fr.eni.enchereseni.bo.Auction;
import fr.eni.enchereseni.bo.Category;
import fr.eni.enchereseni.bo.SoldItem;
import fr.eni.enchereseni.bo.User;
import fr.eni.enchereseni.bll.ManagerSing;

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	AuctionManager auctionManager = ManagerSing.getAuctionManager();
    	
       
        // Récupérer l'utilisateur à partir du cookie "Remember Me" s'il existe
        User user = null;
		try {
			user = getUserFromRememberMeCookie(request);
		} catch (NumberFormatException | ManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        if (user == null) {
            // Si l'utilisateur n'est pas trouvé dans le cookie, récupérez-le depuis la session ou l'authentification
            user = getUserFromSessionOrAuthentication();
        }

        
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
        
     // code pour obtenir la valeur du radiobouton
        String radioButtonValue = request.getParameter("typeRadio");
        
        if (radioButtonValue == null) {
            radioButtonValue = "openAuctions";
        }
        List<Auction> auctions = null;

        try {
            auctions = auctionManager.getAuctionsByRadioButton(radioButtonValue, user);
        } catch (ManagerException e) {
            e.printStackTrace();
            // Gérer l'exception, par exemple, en renvoyant un message d'erreur à l'utilisateur
        }

        // Stockez la liste d'enchères dans la requête pour l'afficher dans votre vue (JSP)
        request.setAttribute("auctions", auctions);

        request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
    }

    // Méthode pour récupérer l'utilisateur à partir du cookie "Remember Me"
    private User getUserFromRememberMeCookie(HttpServletRequest request) throws NumberFormatException, ManagerException {
        jakarta.servlet.http.Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (((Cookie) cookie).getName().equals("rememberMe")) {
                    String rememberMeValue = ((Cookie) cookie).getValue();
                    // Utilisez cette valeur (ID de l'utilisateur) pour récupérer l'utilisateur
                    // depuis votre gestionnaire d'utilisateurs
                    UserManager userManager = ManagerSing.getUserManager();
                    return userManager.getUserById(Integer.parseInt(rememberMeValue));
                }
            }
        }
        return null; // Retourne null si le cookie "Remember Me" n'est pas trouvé
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = getUserFromSessionOrAuthentication(); // Remplacez cette ligne par la récupération de l'utilisateur
        SoldItemManager soldItemManager = ManagerSing.getSoldItemManager(); 
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
