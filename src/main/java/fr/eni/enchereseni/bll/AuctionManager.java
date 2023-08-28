package fr.eni.enchereseni.bll;
import java.util.List;

public interface AuctionManager {
    // Gestion des enchères
    void createAuction(Auction auction);
    void updateAuction(Auction auction);
    void deleteAuction(int auctionId);
    Auction getAuctionById(int auctionId);
    List<Auction> getAuctionsBySeller(String sellerName);
    List<Auction> getAuctionsByName(String itemName);
    List<Auction> getAllAuctions();
    void placeBid(int auctionId, double bidAmount, String bidderName);
    void closeAuction(int auctionId);
    List<Auction> getActiveAuctions();
    List<Auction> getClosedAuctions();
    List<Auction> getWonAuctionsByUser(String username);
    List<Bid> getBidsForAuction(int auctionId);
    List<Bid> getUserBids(String username);
    Bid getHighestBidForAuction(int auctionId);
    List<Auction> getUserActiveAuctions(String username);

    // Gestion des statuts d'article
    void markItemAsForSale(int auctionId);
    void markItemAsSold(int auctionId);
    void markItemAsDelivered(int auctionId);

    // Gestion des comptes
    void createAccount(UserAccount account);
    void deleteAccount(String username);
    UserAccount findAccountByUsername(String username);
    void deactivateAccount(String username);

    // Autres fonctionnalités
    void cancelSaleById(int auctionId);
}