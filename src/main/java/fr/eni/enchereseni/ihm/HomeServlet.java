package fr.eni.enchereseni.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import fr.eni.enchereseni.bll.AuctionManager;
import fr.eni.enchereseni.bll.ManagerException;
import fr.eni.enchereseni.bo.User;
import fr.eni.enchereseni.bo.Auction; // Assure-toi que le package et le nom de la classe sont corrects
import fr.eni.enchereseni.bll.ManagerSing; // Assure-toi d'importer le ManagerSing

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = getUserFromSessionOrAuthentication(); // Remplace cette ligne par la récupération de l'utilisateur
        AuctionManager auctionManager = ManagerSing.getAuctionManager(); // Utilise ManagerSing pour obtenir l'instance d'AuctionManager
        List<Auction> activeAuctions = null;
		try {
			activeAuctions = auctionManager.getActiveAuctions(user);
		} catch (ManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        request.setAttribute("activeAuctions", activeAuctions); // Ajoute la liste d'enchères à l'objet de requête

        request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
    }

 
    private User getUserFromSessionOrAuthentication() {
        User user = new User();
        return user;
    }
}
