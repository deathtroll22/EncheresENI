package fr.eni.enchereseni.ihm;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import fr.eni.enchereseni.bll.SoldItemManager;
import fr.eni.enchereseni.bll.CategoryManager;
import fr.eni.enchereseni.bll.ManagerException;
import fr.eni.enchereseni.bll.ManagerSing;
import fr.eni.enchereseni.bll.PickUpManager;
import fr.eni.enchereseni.bo.Category;
import fr.eni.enchereseni.bo.PickUp;
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
			CategoryManager manager = ManagerSing.getCategoryManager();
			List<Category> categories = manager.getAllCategories();
			request.setAttribute("categories", categories);
		} catch (ManagerException e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("/WEB-INF/sellItem.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// Obtenez l'utilisateur connecté depuis la session
			HttpSession session = request.getSession();
			User loggedInUser = (User) session.getAttribute("user");

			System.out.println("Session ID: " + session.getId());
			System.out.println("User in session: " + loggedInUser);

			if (loggedInUser != null) {
				System.out.println("User ID: " + loggedInUser.getUserID());

				// Obtenez l'ID de l'utilisateur connecté
				int loggedInUserId = loggedInUser.getUserID();

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
					// La catégorie n'est pas sélectionnée, renvoyez un message d'erreur à la page
					// JSP
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

						// Create new item
						SoldItem newItem = new SoldItem(itemName, itemDescription, auctionStartDate, auctionEndDate,
								startingPrice, 0, "Active", category, pickupStreet, pickupPostalCode, pickupCity,
								loggedInUser);
						
						//create new pick up
						PickUp newPickUp = new PickUp(pickupStreet, pickupPostalCode, pickupCity);
						

						// instance des managers
						SoldItemManager managerItem = ManagerSing.getSoldItemManager();
						PickUpManager managerPickUp = ManagerSing.getPickUpManager();
						
						//affichage du formulaire dans la console
						System.out.println("User ID trying to create item: " + loggedInUserId);
						System.out.println("Item name: " + itemName);
						System.out.println("Item description: " + itemDescription);
						System.out.println("Category parameter: " + categoryParameter);
						System.out.println("Starting price: " + startingPrice);
						System.out.println("Auction start date: " + auctionStartDate);
						System.out.println("Auction end date: " + auctionEndDate);
						System.out.println("Pickup street: " + pickupStreet);
						System.out.println("Pickup postal code: " + pickupPostalCode);
						System.out.println("Pickup city: " + pickupCity);

						// Insert Item 
						managerItem.createItem(newItem, loggedInUserId);
						
						// Insert Pick up
						int articleId = newItem.getItemNumber();
						managerPickUp.createPickUp(newPickUp, articleId);

						// Redirigez l'utilisateur vers la page d'accueil
						response.sendRedirect(request.getContextPath() + "/HomeServlet");
					}
				}
			} else {
				System.out.println("User not found in session.");
				// Autres actions si nécessaire
			}
		} catch (ManagerException e) {
			// Gérez les exceptions liées à la création de l'article
			request.setAttribute("errorMessage", "Error during item creation: " + e.getMessage());
			request.getRequestDispatcher("/WEB-INF/sellItem.jsp").forward(request, response);
		}

	}

}
