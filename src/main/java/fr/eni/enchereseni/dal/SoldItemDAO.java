package fr.eni.enchereseni.dal;

import fr.eni.enchereseni.bo.SoldItem;

public interface SoldItemDAO {
	
	public void createItem(SoldItem item, int userId);

	public SoldItem getSoldItemById(int itemId);

}
