package fr.eni.enchereseni.bll;

import fr.eni.enchereseni.bo.SoldItem;
import java.util.List;

public interface SoldItemManager {
<<<<<<< HEAD
    void createItem(SoldItem item, Integer userID) throws ManagerException;
    List<SoldItem> getAllItems() throws ManagerException;
}
=======
	
	public void createItem(SoldItem item , Integer userID) throws ManagerException;

	public SoldItem getSoldItemById(int itemId)throws ManagerException;

}
>>>>>>> origin/nolwenn_item_en_enchere
