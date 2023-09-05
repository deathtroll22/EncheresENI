package fr.eni.enchereseni.ihm;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import fr.eni.enchereseni.bll.AuctionManager;
import fr.eni.enchereseni.bll.ManagerException;
import fr.eni.enchereseni.bll.ManagerSing;
import fr.eni.enchereseni.bll.SoldItemManager;
import fr.eni.enchereseni.bo.Auction;
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
		
		int itemId = Integer.parseInt(request.getParameter("itemId"));

		// Récupérez l'enchère actuelle pour l'article
		Auction currentAuction = getAuctionForItem(itemId);;

		// Obtenez l'utilisateur connecté
		User user = (User) session.getAttribute("user"); 

		// Obtenez le montant de l'offre actuelle
		int currentOfferAmount = currentAuction.getBidAmount();
		
		// Créditez le meilleur enchérisseur précédent
		if (previousBestBidder != null) {
		    User previousBestBidderUser = previousBestBidder.getUser();
		    int previousBidAmount = previousBestBidder.getBidAmount();
		    previousBestBidderUser.setCredit(previousBestBidderUser.getCredit() + previousBidAmount);
		    // Mettez à jour le crédit de l'utilisateur dans la base de données (vous devrez ajouter cette fonctionnalité)
		}

		// Affichez le montant de l'offre actuelle sur la page JSP
		request.setAttribute("currentOfferAmount", currentOfferAmount);

		// Gérez la proposition d'enchère
		String proposalStr = request.getParameter("proposal");
	    
	    try {
	        int proposalAmount = Integer.parseInt(proposalStr);
	        
	        
	        // Validez la proposition d'enchère
	        if (isValidProposal(proposalAmount, currentOfferAmount, user.getCredit())) {
	            // Débitez le montant de la proposition du solde de l'utilisateur
	            user.setCredit(user.getCredit() - proposalAmount);

	            // Créez ou mettez à jour une enchère dans la base de données
	            createOrUpdateAuction(user.getUserID(), itemId, proposalAmount);

	            // Redirigez l'utilisateur vers la même page pour afficher les détails mis à jour de l'article
	            response.sendRedirect(request.getContextPath() + "/item?itemId=" + itemId);
	        } else {
	        	request.setAttribute("errorMessage", "La proposition d'enchère n'est pas valide.");
	            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
	        }
	    } catch (NumberFormatException e) {
	    	request.setAttribute("errorMessage", "La proposition d'enchère n'est pas un nombre valide.");
	        request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
	    }
	}

	private boolean isValidProposal(int proposalAmount, int currentOfferAmount, int userPoints) {
	    // Vérifiez si la proposition est supérieure à l'offre actuelle
	    // Vérifiez également si le compte de points de l'utilisateur ne devient pas négatif
	    return proposalAmount > currentOfferAmount && proposalAmount <= userPoints;
	}


}
