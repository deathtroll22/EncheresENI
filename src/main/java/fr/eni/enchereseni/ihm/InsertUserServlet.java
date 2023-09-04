package fr.eni.enchereseni.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import fr.eni.enchereseni.bll.UserManager;
import fr.eni.enchereseni.bll.ManagerException;
import fr.eni.enchereseni.bll.ManagerSing;
import fr.eni.enchereseni.bo.User;

import java.io.IOException;

@WebServlet("/InsertUserServlet")
public class InsertUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/insertUser.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Récupération des paramètres du formulaire
            String username = request.getParameter("pseudo");
            String firstName = request.getParameter("first_name");
            String lastName = request.getParameter("name");
            String email = request.getParameter("mail");
            String phoneNumber = request.getParameter("phone");
            String street = request.getParameter("street");
            String postalCode = request.getParameter("post_code");
            String city = request.getParameter("city");
            String password = request.getParameter("password");
            String passwordConfirm = request.getParameter("confirmer_mot_de_passe");

            // Vérification des champs et création de l'utilisateur
            if (username.isEmpty() || firstName.isEmpty() || lastName.isEmpty() ||
                    email.isEmpty() || phoneNumber.isEmpty() || street.isEmpty() ||
                    postalCode.isEmpty() || city.isEmpty() || password.isEmpty() ||
                    !password.equals(passwordConfirm)) {
                request.setAttribute("error", "Invalid input. Please check your data.");
                request.getRequestDispatcher("/WEB-INF/insertUser.jsp").forward(request, response);
            } else {
                // Création de l'utilisateur
            	User newUser = new User(username, lastName, firstName, email, phoneNumber, street, postalCode, city, password, 0, false);


                // Appel à la logique métier pour la création du compte utilisateur
                UserManager manager = ManagerSing.getUserManager();
                manager.createAccount(newUser);

                // Mise en session de l'utilisateur et redirection vers la page de connexion
                HttpSession session = request.getSession();
                session.setAttribute("user", newUser);
                session.setAttribute("connected", true);

                response.sendRedirect(request.getContextPath() + "/HomeServlet");
            }
        } catch (ManagerException e) {
            
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error during user creation.");
        }
    }
}
