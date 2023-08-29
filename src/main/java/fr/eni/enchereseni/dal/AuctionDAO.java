package fr.eni.enchereseni.dal;

import java.util.List;

import fr.eni.enchereseni.bo.Auction;
import fr.eni.enchereseni.bo.SoldItem;
import fr.eni.enchereseni.bo.User;

public interface AuctionDAO {
	public void createItem(SoldItem item);
	public void deleteItem(SoldItem item);
	public void updateItem(SoldItem item);
	public List<SoldItem> getAllItem();
	public SoldItem findBySeller(int userID);
	
	
	public void createUser (User user);
	public void updateUser (User user);
	public void deleteUser (User user);
	public void suspendUser (User user);
	
	
	public void createAuction(Auction auction);
	public void updateAuction(Auction auction);
	public void deleteAuction(int auctionId);
	public  Auction getAuctionById(int auctionId);
	

	/*public void placeBid(int auctionId, double bidAmount, String bidderName);
	public void closeAuction(int auctionId);
	public List<Auction> getActiveAuctions();
	public List<Auction> getClosedAuctions();
	public  List<Auction> getWonAuctionsByUser(String username);
	public  List<Auction> getUserActiveAuctions(String username);
	public List<Auction> getAuctionsBySeller(String sellerName);
	public List<Auction> getAuctionsByName(String itemName);
	public List<Auction> getAllAuctions();
	*/
}
