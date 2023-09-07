package fr.eni.enchereseni.bll;

import fr.eni.enchereseni.bo.SoldItem;
import java.util.List;

public interface SoldItemManager {

	
	public void createItem(SoldItem item , Integer userID) throws ManagerException;

	public SoldItem getSoldItemById(int itemId)throws ManagerException;

	List<SoldItem> getAllItems() throws ManagerException;
	
	public List<SoldItem> getSoldItemsByCategory(int categoryId) throws ManagerException;
	
	List<SoldItem> getAllItemsWithFilter(String whereClause) throws ManagerException;

	public void deleteItem(int itemId) throws ManagerException;


}

