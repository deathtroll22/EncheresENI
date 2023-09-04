package fr.eni.enchereseni.bll;

public class ManagerSing {
    private static AuctionManager auctionManagerInstance;
    private static CategoryManager categoryManagerInstance;
    private static PickUpManager pickUpManagerInstance;
    private static SoldItemManager soldItemManagerInstance;
    private static UserManager userManagerInstance;

    public static AuctionManager getAuctionManager() {
        if (auctionManagerInstance == null) {
            auctionManagerInstance = new AuctionManagerImpl();
        }
        return auctionManagerInstance;
    }

    public static CategoryManager getCategoryManager() {
        if (categoryManagerInstance == null) {
            categoryManagerInstance = new CategoryManagerImpl();
        }
        return categoryManagerInstance;
    }

    public static PickUpManager getPickUpManager() {
        if (pickUpManagerInstance == null) {
            pickUpManagerInstance = new PickUpManagerImpl();
        }
        return pickUpManagerInstance;
    }

    public static SoldItemManager getSoldItemManager() {
        if (soldItemManagerInstance == null) {
            soldItemManagerInstance = new SoldItemManagerImpl();
        }
        return soldItemManagerInstance;
    }

    public static UserManager getUserManager() {
        if (userManagerInstance == null) {
            userManagerInstance = new UserManagerImpl();
        }
        return userManagerInstance;
    }
}
