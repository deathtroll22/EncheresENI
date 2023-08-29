package fr.eni.enchereseni.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
import fr.eni.enchereseni.bll.AuctionManager;
import fr.eni.enchereseni.bll.BLLException;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/InsertUserServlet")
public class InsertUserServlet extends HttpServlet {
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.getRequestDispatcher("/WEB-INF/insertUser.jsp").forward(request, response);
    }
}
