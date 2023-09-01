package fr.eni.enchereseni.ihm;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
public class ItemServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Ici, vous récupérez les détails de l'article à afficher (par exemple depuis une base de données) et les placez dans des attributs de la requête
        String itemName = "Item Name Here";
        String itemDescription = "Item Description Here";
        String itemCategory = "Item Category Here";
        // ... Autres détails de l'article ...

        request.setAttribute("itemName", itemName);
        request.setAttribute("itemDescription", itemDescription);
        request.setAttribute("itemCategory", itemCategory);

        // Vous pouvez également récupérer les informations sur les enchères en cours, la meilleure offre, etc.

        // Ensuite, vous transférez le contrôle à la page JSP "item.jsp" pour afficher les détails de l'article et le formulaire d'enchère
        request.getRequestDispatcher("/WEB-INF/item.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Ici, vous gérez la soumission du formulaire d'enchère
        String proposal = request.getParameter("proposal");
        // Traitez la proposition d'enchère et effectuez les actions nécessaires (mise à jour de la base de données, etc.)

        // Vous pouvez ensuite rediriger l'utilisateur vers la même page pour afficher les détails mis à jour de l'article après l'enchère
        response.sendRedirect(request.getContextPath() + "/item");
    }
}




