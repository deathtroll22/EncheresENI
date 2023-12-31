package fr.eni.enchereseni.ihm;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import fr.eni.enchereseni.bll.AuctionManager;
import fr.eni.enchereseni.bll.ManagerException;
import fr.eni.enchereseni.bll.ManagerSing;
import fr.eni.enchereseni.bll.SoldItemManager;
import fr.eni.enchereseni.bll.UserManager;
import fr.eni.enchereseni.bo.Auction;
import fr.eni.enchereseni.bo.Category;
import fr.eni.enchereseni.bo.PickUp;
import fr.eni.enchereseni.bo.SoldItem;
import fr.eni.enchereseni.bo.User;
import fr.eni.enchereseni.dal.UserDAO;

public class ItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final AuctionManager auctionManager = ManagerSing.getAuctionManager();
	private final SoldItemManager soldItemManager = ManagerSing.getSoldItemManager();
	private final UserManager userManager = ManagerSing.getUserManager();

	public ItemServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    int itemId = Integer.parseInt(request.getParameter("itemId"));
	    HttpSession session = request.getSession();
	    User currentUser = (User) session.getAttribute("user");
	    long millis = System.currentTimeMillis();

	    // Création d'un nouvel objet de la classe Date
	    java.sql.Date currentDate = new java.sql.Date(millis);

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
	        List<Auction> auctions = auctionManager.getAuctionsByItemId(itemId);

	        // Ajoutez ces lignes pour trouver l'enchère la plus élevée et le nom d'utilisateur correspondant
	        double highestBid = 0;
	        String highestBidder = "";

	        for (Auction auction : auctions) {
	            if (auction.getBidAmount() > highestBid) {
	                highestBid = auction.getBidAmount();
	                highestBidder = auction.getUser().getUsername();
	            }
	        }

	        request.setAttribute("soldItem", soldItem);
	        request.setAttribute("itemCategory", itemCategory);
	        request.setAttribute("pickUp", pickUp);
	        request.setAttribute("seller", user);
	        request.setAttribute("currentDate", currentDate);
	        request.setAttribute("auctions", auctions);
	        
	        // Ajoutez ces lignes pour passer les variables `highestBid` et `highestBidder` à la JSP
	        request.setAttribute("highestBid", highestBid);
	        request.setAttribute("highestBidder", highestBidder);

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
	        int currentValue = currentAuction != null ? currentAuction.getBidAmount() : 0;

	        System.out.println("currentValue: " + currentValue);

	        Auction previousBestBidder = auctionManager.getPreviousBestBidder(itemId);

	        if (previousBestBidder != null) {
	            User previousBestBidderUser = previousBestBidder.getUser();
	            int previousBidAmount = previousBestBidder.getBidAmount();
	            previousBestBidderUser.setCredit(previousBestBidderUser.getCredit() + previousBidAmount);
	            userManager.updateUserCredit(previousBestBidderUser.getUserID(), previousBestBidderUser.getCredit());
	        }

	        request.setAttribute("currentValue", currentValue);

	        String proposalStr = request.getParameter("proposal");

	        if (proposalStr != null && !proposalStr.isEmpty()) {
	            try {
	                int proposalAmount = Integer.parseInt(proposalStr);

	                if (isValidProposal(proposalAmount, currentValue, user.getCredit())) {
	                    // La proposition est valide, vous pouvez la traiter
	                    user.setCredit(user.getCredit() - proposalAmount);
	                    userManager.updateUserCredit(user.getUserID(), user.getCredit());
	                    auctionManager.createOrUpdateAuction(user.getUserID(), itemId, proposalAmount);

	                    response.sendRedirect(request.getContextPath() + "/ItemServlet?itemId=" + itemId);
	                    return;
	                } else {
	                    // La proposition n'est pas valide, renvoyez un message d'erreur à l'utilisateur
	                    request.setAttribute("errorMessage", "The bid offer is not valid.");
	                    request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
	                }
	            } catch (NumberFormatException e) {
	                // Gérer l'exception si la proposition n'est pas un nombre valide
	                request.setAttribute("errorMessage", "The bid amount is not a valid number.");
	                request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
	            }
	        } else {
	            // Gérer le cas où proposalStr est vide (l'utilisateur n'a rien saisi)
	            request.setAttribute("errorMessage", "Please enter a bid.");
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
		request.setAttribute("errorMessage", "Error while retrieving item details.");
		request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
	}
}
