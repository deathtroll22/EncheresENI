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
    public void createOrUpdateAuction(int userId, int itemId, int bidAmount)throws ManagerException;
    public List<Auction> getAuctionsByItemId(int itemId)throws ManagerException;
    List<Auction> getAuctionsByRadioButton(String radioButtonValue, User user)throws ManagerException;

}