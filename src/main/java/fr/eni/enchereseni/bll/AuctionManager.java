package fr.eni.enchereseni.bll;
import java.util.List;

import fr.eni.enchereseni.bo.Auction;
import fr.eni.enchereseni.bo.Category;
import fr.eni.enchereseni.bo.SoldItem;
import fr.eni.enchereseni.bo.User;

public interface AuctionManager {
    
 // Gestion des utilisateurs :
    public void createAccount(User account) throws AuctionManagerException;
    public User login(String loginIdentifier, String password) throws AuctionManagerException;
    public User getUserProfileByUsername(String username) throws AuctionManagerException;
    public void updateMyProfil (User user) throws AuctionManagerException;
    //public void deleteAccount(User user) throws AuctionManagerException;
    // 2 public void rememberMe(User user) throws AuctionManagerException;
    // 2 public void forgotPassword(String loginIdentifier) throws AuctionManagerException;
    // 3 public void viewPoints();
    
 // Gestion des enchères
    //public void sellItem(SoldItem item) throws AuctionManagerException;
    //public List<Auction> getClosedAuctions() throws AuctionManagerException;
    // public List<Auction> getActiveAuctions(User user) throws AuctionManagerException;
    //public void bid(User bidder, Auction auction, Integer bidAmount) throws AuctionManagerException;
    //public void winSale(User winner, Auction auction) throws AuctionManagerException;
    //public Auction detailsAuction(User user, Auction auction) throws AuctionManagerException;
    // 2 modifier une vente void updateAuction(Auction auction);
    // 2 annuler une vente void deleteAuction(int auctionId);
    // 2 photo pour la vente
    // 3 pagination
    // 3 voir les enchérisseurs
    // 3 achat de crédits
    
    
    /*Auction getAuctionById(int auctionId);
    List<Auction> getAuctionsBySeller(String sellerName);
    List<Auction> getAuctionsByName(String itemName);
    List<Auction> getAllAuctions();
    void closeAuction(int auctionId);
    List<Auction> getWonAuctionsByUser(String username);
    List<Bid> getBidsForAuction(int auctionId);
    List<Bid> getUserBids(String username);
    Bid getHighestBidForAuction(int auctionId);
    List<Auction> getUserActiveAuctions(String username);

    // Gestion des statuts d'article
    void markItemAsForSale(int auctionId);
    void markItemAsSold(int auctionId);
    void markItemAsDelivered(int auctionId);


    // Autres fonctionnalités
    void cancelSaleById(int auctionId);*/
}