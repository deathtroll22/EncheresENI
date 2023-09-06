package fr.eni.enchereseni.dal;

import java.util.List;

import fr.eni.enchereseni.bll.ManagerException;
import fr.eni.enchereseni.bo.Auction;
import fr.eni.enchereseni.bo.User;

public interface AuctionDAO {
	
	public void createOrUpdateAuction(int userId, int itemId, int bidAmount);
    public Auction getPreviousBestBidder(int itemId);
    public List<Auction> getActiveAuctions(User user);
    public List<Auction> getAuctionsByItemId(int itemId);
}
