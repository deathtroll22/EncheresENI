package fr.eni.enchereseni.dal;

import java.util.List;

import fr.eni.enchereseni.bo.Auction;
import fr.eni.enchereseni.bo.SoldItem;

public interface AuctionDAO {
	public void insert(SoldItem item);
	public void delete(SoldItem item);
	public void update(SoldItem item);
	public List<SoldItem> getAll();
	public SoldItem findByUser(int userID);
	public void createAuction(Auction auction);
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
    List<Auction> getUserActiveAuctions(String username);
}
