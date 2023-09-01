package fr.eni.enchereseni.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import fr.eni.enchereseni.bll.AuctionManager;
import fr.eni.enchereseni.bll.AuctionManagerException;
import fr.eni.enchereseni.bll.AuctionManagerImpl;
import fr.eni.enchereseni.bo.User;

public class EditMyProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditMyProfileServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException {
		    // Récupérer l'utilisateur à partir de la session
		    User user = (User) request.getSession().getAttribute("user");
		    
		    // Placer l'utilisateur en tant qu'attribut de la requête pour l'afficher dans la JSP
		    request.setAttribute("user", user);

		    // Forward vers la page d'édition de profil
		    request.getRequestDispatcher("/WEB-INF/editMyProfile.jsp").forward(request, response);
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String confirmNewPassword = request.getParameter("confirmNewPassword");
		String firstName = request.getParameter("firstName");
	    String lastName = request.getParameter("lastName");
	    String email = request.getParameter("email");
	    String telephone = request.getParameter("telephone");
	    String street = request.getParameter("street");
	    String postalCode = request.getParameter("postalCode");
	    String city = request.getParameter("city");
	    

		// Vérifier que les mots de passe sont identiques
		if (!newPassword.equals(confirmNewPassword)) {
			request.setAttribute("errorMessage", "Les mots de passe ne sont pas identiques");
			request.getRequestDispatcher("/WEB-INF/editMyProfile.jsp").forward(request, response);
			return;
		}

		// Modifier le profil en base de données
		User user = (User) request.getSession().getAttribute("user");
		user.setPassword(newPassword);
		user.setFirstName(firstName); 
	    user.setLastName(lastName);
	    user.setEmail(email);
	    user.setPhoneNumber(telephone);
	    user.setStreet(street);
	    user.setPostalCode(postalCode);
	    user.setCity(city);

		AuctionManager auctionManager = new AuctionManagerImpl();
		try {
			auctionManager.updateMyProfil(user);
			// Rediriger vers la page de profil
			response.sendRedirect(request.getContextPath() + "/MyProfileServlet");
		} catch (AuctionManagerException e) {
			e.printStackTrace();
		}
	}
}
