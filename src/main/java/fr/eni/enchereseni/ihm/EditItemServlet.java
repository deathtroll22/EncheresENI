package fr.eni.enchereseni.ihm;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import fr.eni.enchereseni.bll.SoldItemManager;
import fr.eni.enchereseni.bo.Category;
import fr.eni.enchereseni.bo.PickUp;
import fr.eni.enchereseni.bo.SoldItem;
import fr.eni.enchereseni.bo.User;
import fr.eni.enchereseni.bll.CategoryManager;
import fr.eni.enchereseni.bll.ManagerException;
import fr.eni.enchereseni.bll.ManagerSing;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EditItemServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupérez l'ID de l'article à éditer depuis les paramètres de la requête
        int itemId = Integer.parseInt(request.getParameter("itemId"));

        try {
            // Obtenez l'article à éditer en utilisant l'ID
        	SoldItemManager soldItemManager = ManagerSing.getSoldItemManager();
        	CategoryManager categoryManager = ManagerSing.getCategoryManager();
        	SoldItem soldItem = soldItemManager.getSoldItemById(itemId);
        	Category itemCategory = soldItem.getCategoryItem();
        	PickUp pickUp = soldItem.getpickUp();
	        User user = soldItem.getUser();
	        List<Category> categories = categoryManager.getAllCategories();
	        
	        int itemCategoryId = itemCategory.getCategoryNumber();
	     // Placez l'article à éditer dans la session pour pouvoir le récupérer dans la méthode doPost
	        request.getSession().setAttribute("soldItem", soldItem);

            // Placez l'article à éditer dans les attributs de la requête pour préremplir les champs
        	request.setAttribute("soldItem", soldItem);
	        request.setAttribute("itemCategory", itemCategory);
	        request.setAttribute("itemCategoryId", itemCategoryId);
	        request.setAttribute("pickUp", pickUp);
	        request.setAttribute("seller", user);
	        request.setAttribute("categories", categories);

            // Redirigez l'utilisateur vers la page d'édition (editItem.jsp)
            request.getRequestDispatcher("/WEB-INF/editItem.jsp").forward(request, response);
        } catch (ManagerException e) {
            // Gérez les exceptions liées à l'édition de l'article
            request.setAttribute("errorMessage", "Erreur lors de la modification de l'article : " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/errorPage.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	SoldItem soldItem = (SoldItem) request.getSession().getAttribute("soldItem");
        // Récupérez les données du formulaire
    	String itemName = request.getParameter("itemName");
        String itemDescription = request.getParameter("itemDescription");
        String categoryParameter = request.getParameter("category");
        double startingPrice = Double.parseDouble(request.getParameter("startingPrice"));
        Date auctionStartDate = Date.valueOf(request.getParameter("auctionStartDate"));
        Date auctionEndDate = Date.valueOf(request.getParameter("auctionEndDate"));
        
        int categoryNumber = Integer.parseInt(categoryParameter);
        Category category = new Category(categoryNumber, "");


        // Mettez à jour les propriétés de l'article
        soldItem.setItemName(itemName);
        soldItem.setItemDescription(itemDescription);
        soldItem.setStartingPrice(startingPrice);
        soldItem.setAuctionStartDate(auctionStartDate);
        soldItem.setAuctionEndDate(auctionEndDate);
        soldItem.getCategoryItem().setCategoryName(categoryParameter);

     // Mettez à jour l'article dans la base de données (vous devez implémenter cette partie)
        try {
            SoldItemManager manager = ManagerSing.getSoldItemManager();
            manager.updateSoldItem(soldItem);
            response.sendRedirect("HomeServlet");
        } catch (ManagerException e) {
            // Gérez les exceptions liées à la mise à jour de l'article
            request.setAttribute("errorMessage", "Erreur lors de la mise à jour de l'article : " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/errorPage.jsp").forward(request, response);
            return;
        }

    }
}