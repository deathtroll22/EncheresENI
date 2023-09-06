package fr.eni.enchereseni.bll;

import java.util.List;

import fr.eni.enchereseni.bo.Auction;
import fr.eni.enchereseni.bo.User;
import fr.eni.enchereseni.dal.AuctionDAO;
import fr.eni.enchereseni.dal.DAOFact;

public class AuctionManagerImpl implements AuctionManager {
	
	private AuctionDAO dao = DAOFact.getAuctionDAO();

	@Override
    public List<Auction> getActiveAuctions(User user) throws ManagerException {
        
            List<Auction> activeAuctions = dao.getActiveAuctions(user);
            return activeAuctions;
        
    }

    @Override
    public Auction getPreviousBestBidder(int itemId) throws ManagerException {
        
            Auction previousBestBidder = dao.getPreviousBestBidder(itemId);
            System.out.println("Item ID: " + itemId);
            System.out.println("Previous Best Bidder: " + previousBestBidder);
            return previousBestBidder;
        
    }

	@Override
	public void createOrUpdateAuction(int userId, int itemId, int bidAmount) throws ManagerException {
		AuctionDAO auctionDAO = DAOFact.getAuctionDAO();
        auctionDAO.createOrUpdateAuction(userId, itemId, bidAmount);
		
	}

	@Override
	public List<Auction> getAuctionsByItemId(int itemId) throws ManagerException {
		List<Auction> AuctionsById = dao.getAuctionsByItemId(itemId);
		return AuctionsById;
	}
}
