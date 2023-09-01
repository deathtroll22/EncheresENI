package fr.eni.enchereseni.ihm;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import fr.eni.enchereseni.bll.AuctionManager;
import fr.eni.enchereseni.bll.AuctionManagerException;
import fr.eni.enchereseni.bll.AuctionManagerSing;
import fr.eni.enchereseni.bo.Category;
import fr.eni.enchereseni.bo.SoldItem;
import fr.eni.enchereseni.bo.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/SellItemServlet")
public class SellItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			AuctionManager manager = AuctionManagerSing.getInstance();
			List<Category> categories = manager.getAllCategories();
			request.setAttribute("categories", categories);
		} catch (AuctionManagerException e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("/WEB-INF/sellItem.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Obtenez l'utilisateur connecté depuis la session
        	HttpSession session = request.getSession();
        	User loggedInUser = (User) session.getAttribute("loggedInUser");
        	
        	if (loggedInUser != null) {
        	    System.out.println("User ID: " + loggedInUser.getUserID());

            // Récupérez les données du formulaire
            String itemName = request.getParameter("itemName");
            String itemDescription = request.getParameter("itemDescription");
            String categoryParameter = request.getParameter("category");
            double startingPrice = Double.parseDouble(request.getParameter("startingPrice"));
            Date auctionStartDate = Date.valueOf(request.getParameter("auctionStartDate"));
            Date auctionEndDate = Date.valueOf(request.getParameter("auctionEndDate"));
            String pickupStreet = request.getParameter("pickupStreet");
            String pickupPostalCode = request.getParameter("pickupPostalCode");
            String pickupCity = request.getParameter("pickupCity");

            // Vérifiez si la catégorie est sélectionnée (non nulle)
            if (categoryParameter == null || categoryParameter.isEmpty()) {
                // La catégorie n'est pas sélectionnée, renvoyez un message d'erreur à la page JSP
                request.setAttribute("errorMessage", "Please select a category.");
                request.getRequestDispatcher("/WEB-INF/sellItem.jsp").forward(request, response);
            } else {
                // La catégorie est sélectionnée, continuez avec la création de l'article
                int categoryNumber = Integer.parseInt(categoryParameter);
                
                // Validez les autres données du formulaire
                if (itemName.isEmpty() || itemDescription.isEmpty() || startingPrice <= 0
                        || auctionStartDate.toLocalDate().isAfter(auctionEndDate.toLocalDate())) {
                    // Les données sont invalides, renvoyez un message d'erreur à la page JSP
                    request.setAttribute("errorMessage", "Invalid input. Please check your data.");
                    request.getRequestDispatcher("/WEB-INF/sellItem.jsp").forward(request, response);
                } else {
                    // Créez une instance de Category en utilisant la valeur de la catégorie
                    Category category = new Category(categoryNumber, "");

                    // Créez un nouvel article
                    SoldItem newItem = new SoldItem(itemName, itemDescription, auctionStartDate, auctionEndDate,
                            startingPrice, 0, "Active", category, pickupStreet, pickupPostalCode, pickupCity, loggedInUser);

                    // Obtenez une instance de votre gestionnaire AuctionManager
                    AuctionManager manager = AuctionManagerSing.getInstance();

                    // Insérez l'article en utilisant la méthode createItem de votre gestionnaire
                    manager.createItem(newItem, loggedInUser.getUserID());

                    // Redirigez l'utilisateur vers la page d'accueil
                    response.sendRedirect(request.getContextPath() + "/HomeServlet");
                }
            }
        	} else {
                System.out.println("User not found in session.");
                // Autres actions si nécessaire
            }
        } catch (AuctionManagerException e) {
            // Gérez les exceptions liées à la création de l'article
            request.setAttribute("errorMessage", "Error during item creation: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/sellItem.jsp").forward(request, response);
        }
        
    }

}
