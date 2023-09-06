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
	        int currentValue = currentAuction != null ? currentAuction.getBidAmount() : 0;

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

	        System.out.println("proposalStr: " + proposalStr); // Ajoutez cette ligne pour vérifier la valeur de proposalStr

	        if (proposalStr != null && !proposalStr.isEmpty()) {
	            try {
	                int proposalAmount = Integer.parseInt(proposalStr);

	                System.out.println("proposalAmount: " + proposalAmount); // Ajoutez cette ligne pour vérifier la valeur de proposalAmount

	                if (isValidProposal(proposalAmount, currentValue, user.getCredit())) {
	                    // La proposition est valide, vous pouvez la traiter
	                    user.setCredit(user.getCredit() - proposalAmount);
	                    auctionManager.createOrUpdateAuction(user.getUserID(), itemId, proposalAmount);

	                    response.sendRedirect(request.getContextPath() + "/item?itemId=" + itemId);
	                } else {
	                    // La proposition n'est pas valide, renvoyez un message d'erreur à l'utilisateur
	                    request.setAttribute("errorMessage", "La proposition d'enchère n'est pas valide.");
	                    request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
	                }
	            } catch (NumberFormatException e) {
	                // Gérer l'exception si la proposition n'est pas un nombre valide
	                request.setAttribute("errorMessage", "La proposition d'enchère n'est pas un nombre valide.");
	                request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
	            }
	        } else {
	            // Gérer le cas où proposalStr est vide (l'utilisateur n'a rien saisi)
	            System.out.println("y a rien dans le champ");
	            request.setAttribute("errorMessage", "Veuillez entrer une proposition d'enchère.");
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
