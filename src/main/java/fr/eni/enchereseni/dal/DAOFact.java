package fr.eni.enchereseni.dal;

public class DAOFact {
	public static AuctionDAO getAuctionDAO() {
		return new AuctionDAOImpl();
	}
}
