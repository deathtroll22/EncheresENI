package fr.eni.enchereseni.dal;

import java.util.List;

import fr.eni.enchereseni.bo.SoldItem;

public interface AuctionDAO {
	public void insert(SoldItem item);
	public List<SoldItem> getAll();
	public SoldItem findByUser(int userID);

}
