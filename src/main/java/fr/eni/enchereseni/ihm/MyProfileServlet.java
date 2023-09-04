package fr.eni.enchereseni.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.RequestDispatcher;
import fr.eni.enchereseni.bll.UserManager;
import fr.eni.enchereseni.bll.ManagerSing;
import fr.eni.enchereseni.bo.User;
import java.io.IOException;

@WebServlet("/MyProfileServlet")
public class MyProfileServlet extends HttpServlet {
	
	// Appel à la logique métier pour la création du compte utilisateur
    UserManager manager = ManagerSing.getUserManager();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/Login");
            return;
        }

        request.setAttribute("user", user);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/myProfile.jsp");
        dispatcher.forward(request, response);
    }
}
