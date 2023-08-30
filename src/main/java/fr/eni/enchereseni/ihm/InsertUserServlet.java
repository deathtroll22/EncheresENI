package fr.eni.enchereseni.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.RequestDispatcher;
import fr.eni.enchereseni.bll.AuctionManager;
import fr.eni.enchereseni.bll.AuctionManagerSing;
import fr.eni.enchereseni.bo.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/InsertUserServlet")
public class InsertUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AuctionManager manager = AuctionManagerSing.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/insertUser.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/insertUser.jsp");

		try {
			String username = request.getParameter("pseudo");
			String firstName = request.getParameter("first_name");
			String lastName = request.getParameter("name");
			String email = request.getParameter("mail");
			String phoneNumber = request.getParameter("phone");
			String street = request.getParameter("street");
			String postalCode = request.getParameter("post_code");
			String city = request.getParameter("city");
			String password = request.getParameter("password");
			String passwordconfirm = request.getParameter("confirmer_mot_de_passe");

			if (username.length() == 0 || username.isEmpty()) {
				request.setAttribute("error", "please enter a username");
				dispatcher.forward(request, response);
			} else if (firstName.length() == 0 || firstName.isEmpty()) {
				request.setAttribute("error", "please enter a name");
				dispatcher.forward(request, response);
			} else if (lastName.length() == 0 || lastName.isEmpty()) {

				request.setAttribute("error", "please enter a lastName");
				dispatcher.forward(request, response);
			} else if (email.length() == 0 || email.isEmpty()) {
				request.setAttribute("error", "please enter a email");
				dispatcher.forward(request, response);
			} else if (phoneNumber.length() == 0 || phoneNumber.isEmpty()) {
				request.setAttribute("error", "please enter a phoneNumber");
				dispatcher.forward(request, response);
			} else if (street.length() == 0 || street.isEmpty()) {
				request.setAttribute("error", "please enter a street");
				dispatcher.forward(request, response);
			} else if (postalCode.length() == 0 || postalCode.isEmpty()) {
				request.setAttribute("error", "please enter a postal Code");
				dispatcher.forward(request, response);
			} else if (city.length() == 0 || city.isEmpty()) {
				request.setAttribute("error", "please enter a city");
				dispatcher.forward(request, response);
			} else if (password.length() == 0 || password.isEmpty()) {
				request.setAttribute("error", "please enter a password");
				dispatcher.forward(request, response);
			} else if (passwordconfirm.equals(password)) {

				User connectedUser = new User(username, lastName, firstName, email, phoneNumber, street, postalCode,
						city, passwordconfirm, null, false);

				if (connectedUser != null) {
					HttpSession session = request.getSession();
					session.setAttribute("connectedUser", connectedUser);

					response.sendRedirect(request.getContextPath() + "/LoginServlet");
					
				} else {
					request.setAttribute("error", "no user");
					dispatcher.forward(request, response);
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
