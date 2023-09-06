package fr.eni.enchereseni.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import fr.eni.enchereseni.bll.SoldItemManager; // Assure-toi d'importer le SoldItemManager
import fr.eni.enchereseni.bll.CategoryManager;
import fr.eni.enchereseni.bll.CategoryManagerImpl;
import fr.eni.enchereseni.bll.ManagerException;
import fr.eni.enchereseni.bo.Category;
import fr.eni.enchereseni.bo.SoldItem;
import fr.eni.enchereseni.bo.User;
import fr.eni.enchereseni.dal.CategoryDAO;
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

        // Récupérer la liste des catégories depuis CategoryDAOImpl
        CategoryManager category = new CategoryManagerImpl();
        List<Category> categories = null;
		try {
			categories = category.getAllCategories();
		} catch (ManagerException e) {
			e.printStackTrace();
		}
        
        request.setAttribute("categories", categories); // Ajoutez la liste de catégories à l'objet de requête

        
        request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);  
    }

    private User getUserFromSessionOrAuthentication() {
        User user = new User();
        return user;
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    	    throws ServletException, IOException {
    	    User user = getUserFromSessionOrAuthentication(); // Remplace cette ligne par la récupération de l'utilisateur
    	    SoldItemManager soldItemManager = ManagerSing.getSoldItemManager(); // Utilise ManagerSing pour obtenir l'instance de SoldItemManager
    	    List<SoldItem> allItems = null;
    	    
    	    try {
    	        // Récupérez le paramètre de recherche
    	        String searchInput = request.getParameter("searchInput");
    	        
    	        // Récupérez le paramètre de catégorie
    	        String categorySelect = request.getParameter("categorySelect");
    	        
    	        // Si la recherche est vide, affichez tous les articles
    	        if (searchInput == null || searchInput.isEmpty()) {
    	            // Utilisez la nouvelle méthode pour récupérer les articles par catégorie
    	            if (categorySelect != null && !categorySelect.isEmpty()) {
    	                int categoryId = 0;
    	                try {
    	                    categoryId = Integer.parseInt(categorySelect);
    	                } catch (NumberFormatException ex) {
    	                    // Gérer l'exception si la conversion échoue
    	                    ex.printStackTrace();
    	                }
    	                allItems = soldItemManager.getSoldItemsByCategory(categoryId, null, false, false, false, false, false, false, null);
    	            } else {
    	                allItems = soldItemManager.getAllItems();
    	            }
    	        } else {
    	            // Sinon, filtrez les articles par nom en utilisant un stream
    	            allItems = soldItemManager.getAllItems(); // Récupérez tous les articles
    	            allItems = allItems.stream()
    	                .filter(item -> item.getItemName().toLowerCase().contains(searchInput.toLowerCase()))
    	                .collect(Collectors.toList());
    	        }
    	    } catch (ManagerException e) {
    	        // TODO: Gérer l'exception appropriée
    	        e.printStackTrace();
    	    }
    	    
    	    request.setAttribute("allItems", allItems); // Ajoutez la liste filtrée à l'objet de requête

    	    // Appeler doGet pour rafraîchir la page avec la liste des catégories
    	    doGet(request, response);
    	}
    
}
