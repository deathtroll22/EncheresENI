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

		if (request.getParameter("Create_Account_Button") != null) {
			doAdd(request, response);
		} else if (request.getParameter("Cancel") != null) {
			doFact(request, response);
		}

		request.getRequestDispatcher("/WEB-INF/biere.jsp").forward(request, response);
	}

	private void doFact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.sendRedirect(request.getContextPath() + "/AccueilServlet");
	}

	private void doAdd(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
}
