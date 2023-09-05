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
import fr.eni.enchereseni.bo.PickUp;
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
            
            //récupérer le pick up
            PickUp pickUp = soldItem.getpickUp();
            
            //récupérer le vendeur
            User user = soldItem.getUser();

            request.setAttribute("soldItem", soldItem);
            request.setAttribute("itemCategory", itemCategory);
            request.setAttribute("pickUp", pickUp);
            request.setAttribute("seller", user);

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
	    // Récupérez la proposition d'enchère depuis les paramètres de la requête
	    String proposalStr = request.getParameter("proposal");
	    
	    try {
	        int proposalAmount = Integer.parseInt(proposalStr);
	        
	        // Validez la proposition d'enchère
	        if (isValidProposal(proposalAmount, currentOfferAmount, user.getPoints())) {
	            // Débitez le montant de la proposition du solde de l'utilisateur
	            user.setPoints(user.getPoints() - proposalAmount);

	            // Créez ou mettez à jour une enchère dans la base de données
	            createOrUpdateAuction(user.getId(), itemId, proposalAmount);

	            // Redirigez l'utilisateur vers la même page pour afficher les détails mis à jour de l'article
	            response.sendRedirect(request.getContextPath() + "/item?itemId=" + itemId);
	        } else {
	            // La proposition n'est pas valide, vous pouvez gérer l'erreur ici
	            // Par exemple, renvoyer un message d'erreur à l'utilisateur ou rediriger vers une page d'erreur
	        }
	    } catch (NumberFormatException e) {
	        // Gérez l'exception si la proposition n'est pas un nombre valide
	        // Par exemple, renvoyer un message d'erreur à l'utilisateur ou rediriger vers une page d'erreur
	    }
	}

	private boolean isValidProposal(int proposalAmount, int currentOfferAmount, int userPoints) {
	    // Vérifiez si la proposition est supérieure à l'offre actuelle
	    // Vérifiez également si le compte de points de l'utilisateur ne devient pas négatif
	    return proposalAmount > currentOfferAmount && proposalAmount <= userPoints;
	}


}
