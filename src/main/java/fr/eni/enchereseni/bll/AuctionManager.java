package fr.eni.enchereseni.bll;
import java.util.List;

import fr.eni.enchereseni.bo.Auction;
import fr.eni.enchereseni.bo.Category;
import fr.eni.enchereseni.bo.SoldItem;
import fr.eni.enchereseni.bo.User;

public interface AuctionManager {
        
 // Gestion des enchères
    public List<Auction> getActiveAuctions(User user) throws ManagerException;
    public Auction getPreviousBestBidder(int itemId) throws ManagerException;
	

    //public void sellItem(SoldItem item) throws ManagerException;
    //public List<Auction> getClosedAuctions() throws ManagerException;
    
    //public void bid(User bidder, Auction auction, Integer bidAmount) throws ManagerException;
    //public void winSale(User winner, Auction auction) throws ManagerException;
    //public Auction detailsAuction(User user, Auction auction) throws ManagerException;
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