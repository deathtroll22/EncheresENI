package fr.eni.enchereseni.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import fr.eni.enchereseni.bll.UserManager;
import fr.eni.enchereseni.bll.ManagerException;
import fr.eni.enchereseni.bll.UserManagerImpl;
import fr.eni.enchereseni.bo.User;

public class EditMyProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditMyProfileServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException {
		    
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
			request.setAttribute("errorMessage", "The New Password does not match Confirm New Password.");
			request.getRequestDispatcher("/WEB-INF/editMyProfile.jsp").forward(request, response);
			return;
		}
		
		// Récupérer l'utilisateur actuel de la session
	    User user = (User) request.getSession().getAttribute("user");

	    // Vérifier si l'ancien mot de passe correspond au mot de passe actuel de l'utilisateur
	    if (!user.getPassword().equals(oldPassword)) {
	        request.setAttribute("errorMessage", "The old password is incorrect.");
	        request.getRequestDispatcher("/WEB-INF/editMyProfile.jsp").forward(request, response);
	        return;
	    }

		// Modifier le profil en base de données
		user.setPassword(newPassword);
		user.setFirstName(firstName); 
	    user.setLastName(lastName);
	    user.setEmail(email);
	    user.setPhoneNumber(telephone);
	    user.setStreet(street);
	    user.setPostalCode(postalCode);
	    user.setCity(city);

		UserManager userManager = new UserManagerImpl();
		try {
			userManager.updateMyProfil(user);
			// Rediriger vers la page de profil
			response.sendRedirect(request.getContextPath() + "/MyProfileServlet");
		} catch (ManagerException e) {
			e.printStackTrace();
		}
	}
}
