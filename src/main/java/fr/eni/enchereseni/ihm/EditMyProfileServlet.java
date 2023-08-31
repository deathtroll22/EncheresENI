package fr.eni.enchereseni.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditMyProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditMyProfileServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: Add any necessary logic to retrieve user data from the database or session
        // For example:
        // User user = (User) request.getSession().getAttribute("user");
        // request.setAttribute("user", user);

        request.getRequestDispatcher("/WEB-INF/editMyProfile.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: Process form data here, update the user's profile and password

        // Example of updating user's data
        // String firstName = request.getParameter("firstName");
        // String lastName = request.getParameter("lastName");
        // ...

        // Example of updating password
        // String oldPassword = request.getParameter("oldPassword");
        // String newPassword = request.getParameter("newPassword");
        // String confirmNewPassword = request.getParameter("confirmNewPassword");
        
        // TODO: Add validation and error handling

        // After updating, you can redirect to a success page
        response.sendRedirect(request.getContextPath() + "/ProfileUpdatedServlet");
    }
}
