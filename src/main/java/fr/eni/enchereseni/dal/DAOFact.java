package fr.eni.enchereseni.dal;

public class DAOFact {
    private static AuctionDAO auctionDAOInstance;
    private static CategoryDAO categoryDAOInstance;
    private static PickUpDAO pickUpDAOInstance;
    private static SoldItemDAO soldItemDAOInstance;
    private static UserDAO userDAOInstance;

    public static AuctionDAO getAuctionDAO() {
        if (auctionDAOInstance == null) {
            auctionDAOInstance = new AuctionDAOImpl();
        }
        return auctionDAOInstance;
    }

    public static CategoryDAO getCategoryDAO() {
        if (categoryDAOInstance == null) {
            categoryDAOInstance = new CategoryDAOImpl();
        }
        return categoryDAOInstance;
    }

    public static PickUpDAO getPickUpDAO() {
        if (pickUpDAOInstance == null) {
            pickUpDAOInstance = new PickUpDAOImpl();
        }
        return pickUpDAOInstance;
    }

    public static SoldItemDAO getSoldItemDAO() {
        if (soldItemDAOInstance == null) {
            soldItemDAOInstance = new SoldItemDAOImpl();
        }
        return soldItemDAOInstance;
    }

    public static UserDAO getUserDAO() {
        if (userDAOInstance == null) {
            userDAOInstance = new UserDAOImpl();
        }
        return userDAOInstance;
    }
}
