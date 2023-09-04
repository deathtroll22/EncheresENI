package fr.eni.enchereseni.bll;

import java.sql.SQLException;
import java.util.List;

import fr.eni.enchereseni.bo.Auction;
import fr.eni.enchereseni.bo.Category;
import fr.eni.enchereseni.bo.SoldItem;
import fr.eni.enchereseni.bo.User;
import fr.eni.enchereseni.dal.AuctionDAO;
import fr.eni.enchereseni.dal.DAOFact;

public class AuctionManagerImpl implements AuctionManager {
	
	private AuctionDAO dao = DAOFact.getAuctionDAO();

	@Override
	public List<Auction> getActiveAuctions(User user) throws ManagerException {
		// TODO Auto-generated method stub
		return null;
	} 

    
    

}
