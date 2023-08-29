package fr.eni.enchereseni.bll;

public class AuctionManagerException extends Exception {
    public AuctionManagerException() {
        super();
    }

    public AuctionManagerException(String message) {
        super(message);
    }

    public AuctionManagerException(String message, Throwable cause) {
        super(message, cause);
    }
}
