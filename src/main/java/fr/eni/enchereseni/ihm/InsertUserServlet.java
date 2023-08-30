package fr.eni.enchereseni.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
import fr.eni.enchereseni.bll.AuctionManager;
import fr.eni.enchereseni.bll.AuctionManagerSing;
import fr.eni.enchereseni.bll.BLLException;
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
		
		User user = null;
		
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
		}

		if (request.getParameter("Create_Account_Button") != null) {
			doAdd(request, response);
		} else if (request.getParameter("Cancel") != null) {
			doFact(request, response);
		}

		request.getRequestDispatcher("/WEB-INF/biere.jsp").forward(request, response);
	}

	
}
