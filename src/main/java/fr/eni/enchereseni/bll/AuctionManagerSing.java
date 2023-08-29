package fr.eni.enchereseni.bll;

public class AuctionManagerSing {
	private static AuctionManager instance;
	public static AuctionManager getInstance() {
		if(instance==null) {
			instance = new AuctionManagerImpl();
		}
		return instance;
	}

}
