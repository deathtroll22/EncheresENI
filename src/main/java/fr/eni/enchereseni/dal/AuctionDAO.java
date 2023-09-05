package fr.eni.enchereseni.dal;

import java.util.List;

import fr.eni.enchereseni.bll.ManagerException;
import fr.eni.enchereseni.bo.Auction;
import fr.eni.enchereseni.bo.User;

public interface AuctionDAO {
	
	public void createOrUpdateAuction(int userId, int itemId, int bidAmount);
    public Auction getPreviousBestBidder(int itemId);
    public List<Auction> getActiveAuctions(User user);
	/*public void createItem(SoldItem item);
	public void deleteItem(SoldItem item);
	public void updateItem(SoldItem item);
	public List<SoldItem> getAllItem();
	public SoldItem findBySeller(int userID);*/
	
	
	
	


	/*
	
	public void createAuction(Auction auction);
	public void updateAuction(Auction auction);
	public void deleteAuction(int auctionId);
	public  Auction getAuctionById(int auctionId);
	

	public void placeBid(int auctionId, double bidAmount, String bidderName);
	public void closeAuction(int auctionId);
	public List<Auction> getActiveAuctions();
	public List<Auction> getUserClosedAuctions();
	public  List<Auction> getWonAuctionsByUser(String username);
	public  List<Auction> getUserActiveAuctions(User user);
	public List<Auction> getAuctionsBySeller(String sellerName);
	public List<Auction> getAuctionsByName(String itemName);
	public List<Auction> getAllAuctions();
	
	public User getUserByLoginIdentifier(String loginIdentifier);
	
	public void updateUserCredit(User bidder);
	public void markAuctionAsWon(User winner, Auction auction);
	public Auction getAuctionDetails(Auction auction);*/
	
}
