package fr.eni.enchereseni.ihm;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import fr.eni.enchereseni.bll.ManagerException;
import fr.eni.enchereseni.bll.ManagerSing;
import fr.eni.enchereseni.bll.SoldItemManager;
import fr.eni.enchereseni.bo.Category;
import fr.eni.enchereseni.bo.SoldItem;
import fr.eni.enchereseni.bo.User;


public class ItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public ItemServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	// identifiant de l'article depuis l'URL
        int itemId = Integer.parseInt(request.getParameter("itemId"));
    	
        try {
        	// article à partir de l'identifiant
            SoldItemManager itemManager = ManagerSing.getSoldItemManager();
            SoldItem soldItem = itemManager.getSoldItemById(itemId);

            // Récupérer la catégorie de l'article depuis l'objet soldItem
            Category itemCategory = soldItem.getCategoryItem();

            request.setAttribute("soldItem", soldItem);
            request.setAttribute("itemCategory", itemCategory);

            //page JSP pour afficher les détails de l'article
            request.getRequestDispatcher("/WEB-INF/item.jsp").forward(request, response);
            
        } catch (ManagerException e) {
            e.printStackTrace();
            
            request.setAttribute("errorMessage", "Erreur lors de la récupération des détails de l'article.");
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
        }
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Ici, vous gérez la soumission du formulaire d'enchère
		String proposal = request.getParameter("proposal");
		// Traitez la proposition d'enchère et effectuez les actions nécessaires (mise à
		// jour de la base de données, etc.)

		// Vous pouvez ensuite rediriger l'utilisateur vers la même page pour afficher
		// les détails mis à jour de l'article après l'enchère
		response.sendRedirect(request.getContextPath() + "/item");
	}
}
