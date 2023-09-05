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
	private final AuctionManager auctionManager = ManagerSing.getAuctionManager();
	private final SoldItemManager soldItemManager = ManagerSing.getSoldItemManager();

	public ItemServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int itemId = Integer.parseInt(request.getParameter("itemId"));
		HttpSession session = request.getSession();
	    User currentUser = (User) session.getAttribute("user");
	    
	    // Vérifier si l'utilisateur est en session
	    if (currentUser == null) {
	        response.sendRedirect(request.getContextPath() + "/HomeServlet");
	        return; // Arrête l'exécution de la méthode pour éviter de continuer le traitement
	    }

		try {
			SoldItem soldItem = soldItemManager.getSoldItemById(itemId);
			Category itemCategory = soldItem.getCategoryItem();
			PickUp pickUp = soldItem.getpickUp();
			User user = soldItem.getUser();

			request.setAttribute("soldItem", soldItem);
			request.setAttribute("itemCategory", itemCategory);
			request.setAttribute("pickUp", pickUp);
			request.setAttribute("seller", user);

			request.getRequestDispatcher("/WEB-INF/item.jsp").forward(request, response);
		} catch (ManagerException e) {
			handleException(e, request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    int itemId = Integer.parseInt(request.getParameter("itemId"));

	    HttpSession session = request.getSession();
	    User user = (User) session.getAttribute("user");

	    try {
	        Auction currentAuction = auctionManager.getPreviousBestBidder(itemId);
	        int currentValue = currentAuction.getBidAmount();

	        System.out.println("currentValue: " + currentValue);

	        Auction previousBestBidder = auctionManager.getPreviousBestBidder(itemId);

	        if (previousBestBidder != null) {
	            User previousBestBidderUser = previousBestBidder.getUser();
	            int previousBidAmount = previousBestBidder.getBidAmount();
	            previousBestBidderUser.setCredit(previousBestBidderUser.getCredit() + previousBidAmount);
	            // Mettez à jour le crédit de l'utilisateur dans la base de données
	            // Assurez-vous que cette fonctionnalité est implémentée correctement dans votre DAO.
	        }

	        request.setAttribute("currentValue", currentValue);

	        String proposalStr = request.getParameter("proposal");

	        try {
	            int proposalAmount = Integer.parseInt(proposalStr);

	            if (isValidProposal(proposalAmount, currentValue, user.getCredit())) {
	                user.setCredit(user.getCredit() - proposalAmount);
	                auctionManager.createOrUpdateAuction(user.getUserID(), itemId, proposalAmount);

	                response.sendRedirect(request.getContextPath() + "/item?itemId=" + itemId);
	            } else {
	                request.setAttribute("errorMessage", "La proposition d'enchère n'est pas valide.");
	                request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
	            }
	        } catch (NumberFormatException e) {
	            request.setAttribute("errorMessage", "La proposition d'enchère n'est pas un nombre valide.");
	            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
	        }
	    } catch (ManagerException e) {
	        handleException(e, request, response);
	    }
	}


	private boolean isValidProposal(int proposalAmount, int currentValue, int userPoints) {
		return proposalAmount > currentValue && proposalAmount <= userPoints;
	}

	private void handleException(ManagerException e, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		e.printStackTrace(); // Log the exception for debugging purposes
		request.setAttribute("errorMessage", "Erreur lors de la récupération des détails de l'article.");
		request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
	}
}
