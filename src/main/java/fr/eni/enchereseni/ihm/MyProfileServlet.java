package fr.eni.enchereseni.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.RequestDispatcher;
import fr.eni.enchereseni.bll.AuctionManager;
import fr.eni.enchereseni.bll.AuctionManagerException;
import fr.eni.enchereseni.bll.AuctionManagerSing;
import fr.eni.enchereseni.bo.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/MyProfileServlet")
public class MyProfileServlet extends HttpServlet {
	
	// Appel à la logique métier pour la création du compte utilisateur
    AuctionManager manager = AuctionManagerSing.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.getRequestDispatcher("/WEB-INF/myProfile.jsp").forward(request, response);
    }
}
