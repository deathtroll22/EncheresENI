package fr.eni.enchereseni.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
import fr.eni.enchereseni.bll.AuctionManager;
import fr.eni.enchereseni.bll.BLLException;
import fr.eni.enchereseni.bo.User;
import fr.eni.enchereseni.dal.DALException;
import fr.eni.enchereseni.utils.ErrorsManagement;
import fr.eni.enchereseni.utils.PasswordManagement;
import fr.eni.enchereseni.utils.RequestManagement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/InsertUserServlet")
public class InsertUserServlet extends HttpServlet {
	
	
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        AuctionManager am = new AuctionManager();
        List<String> errors = new ArrayList<>();
        // Hash password
        String password = request.getParameter("password");
        String generatedPassword = PasswordManagement.hashPassword(password);
        // New user
        User user = new User(
                request.getParameter("pseudo"),
                request.getParameter("name"),
                request.getParameter("first_name"),
                request.getParameter("mail"),
                request.getParameter("phone"),
                request.getParameter("street"),
                request.getParameter("post_code"),
                request.getParameter("city"),
                generatedPassword,
                0,
                false
        );
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/insertUser.jsp");
        try {
            am.createUser(user);
        } catch (BLLException e) {
            ErrorsManagement.BLLExceptionsCatcher(e, errors, request);
        } catch (DALException e) {
            ErrorsManagement.DALExceptionsCatcher(e, errors, request);
        }
        if (errors.isEmpty()) {
            try {
                RequestManagement.processHomePageAttributes(request);
            } catch (DALException e) {
                ErrorsManagement.DALExceptionsCatcher(e, errors, request);
            } catch (BLLException e) {
                ErrorsManagement.BLLExceptionsCatcher(e, errors, request);
            }
            request.setAttribute("loginCreated", "true");
            request.setAttribute("page", "home");
        } else {
            request.setAttribute("page", "createLogin");
            request.setAttribute("utilisateurError", user);
        }
        rd.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/insertUser.jsp");
        request.setAttribute("page", "createLogin");
        rd.forward(request, response);
    }
}
