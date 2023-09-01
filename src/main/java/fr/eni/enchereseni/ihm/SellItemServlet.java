package fr.eni.enchereseni.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import fr.eni.enchereseni.bll.AuctionManager;
import fr.eni.enchereseni.bll.AuctionManagerException;
import fr.eni.enchereseni.bll.AuctionManagerSing;
import fr.eni.enchereseni.bo.SoldItem;
import fr.eni.enchereseni.bo.User;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/SellItemServlet")
public class SellItemServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/sellItem.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            User seller = (User) session.getAttribute("user");

            String itemName = request.getParameter("itemName");
            String itemDescription = request.getParameter("itemDescription");
            int category = Integer.parseInt(request.getParameter("category"));
            double startingPrice = Double.parseDouble(request.getParameter("startingPrice"));
            LocalDate auctionStartDate = LocalDate.parse(request.getParameter("auctionStartDate"));
            LocalDate auctionEndDate = LocalDate.parse(request.getParameter("auctionEndDate"));
            String pickupStreet = request.getParameter("pickupStreet");
            String pickupPostalCode = request.getParameter("pickupPostalCode");
            String pickupCity = request.getParameter("pickupCity");

            if (itemName.isEmpty() || itemDescription.isEmpty() || startingPrice <= 0 ||
                    auctionStartDate.isAfter(auctionEndDate)) {
                request.setAttribute("error", "Invalid input. Please check your data.");
                request.getRequestDispatcher("/WEB-INF/sellItem.jsp").forward(request, response);
            } else {
                SoldItem newItem = new SoldItem(itemName, itemDescription, auctionStartDate, auctionEndDate,
                        startingPrice, 0, "Active", category, pickupStreet, pickupPostalCode, pickupCity, seller);

                AuctionManager manager = AuctionManagerSing.getInstance();
                manager.insertItem(newItem);

                response.sendRedirect(request.getContextPath() + "/HomeServlet");
            }
        } catch (AuctionManagerException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error during item creation.");
        }
    }
}
