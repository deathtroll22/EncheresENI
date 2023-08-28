package fr.eni.enchereseni.bll;
import java.util.List;

import fr.eni.enchereseni.bo.User;

public interface AuctionManager {
    // Gestion des enchères
    //void createAuction(Auction auction);
    //void updateAuction(Auction auction);
    //void deleteAuction(int auctionId);
    //Auction getAuctionById(int auctionId);
    //List<Auction> getAuctionsBySeller(String sellerName);
    //List<Auction> getAuctionsByName(String itemName);
    //List<Auction> getAllAuctions();
    //void placeBid(int auctionId, double bidAmount, String bidderName);
    //void closeAuction(int auctionId);
    //List<Auction> getActiveAuctions();
    //List<Auction> getClosedAuctions();
    //List<Auction> getWonAuctionsByUser(String username);
    //List<Bid> getBidsForAuction(int auctionId);
    //List<Bid> getUserBids(String username);
    //Bid getHighestBidForAuction(int auctionId);
    //List<Auction> getUserActiveAuctions(String username);

    // Gestion des statuts d'article
    public void markItemAsForSale(int auctionId);
    public void markItemAsSold(int auctionId);
    public void markItemAsDelivered(int auctionId);

    // Gestion des comptes
    public void createUser(User user);
    public void deleteUser(String username);
    public User findUserByUsername(String username);
    public void deactivateUser(String username);
    
    public List<User> getAllUsers();
    public List<User> getAdminUsers();
    public List<User> getRegularUsers();

    // Autres fonctionnalités
    public void cancelSaleById(int auctionId);
}