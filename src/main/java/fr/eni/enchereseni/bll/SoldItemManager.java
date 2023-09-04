package fr.eni.enchereseni.bll;

import fr.eni.enchereseni.bo.SoldItem;

public interface SoldItemManager {
	
	public void createItem(SoldItem item , Integer userID) throws ManagerException;

	public SoldItem getSoldItemById(int itemId)throws ManagerException;

}
