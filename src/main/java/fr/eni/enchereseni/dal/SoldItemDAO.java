package fr.eni.enchereseni.dal;

import java.util.List;

import fr.eni.enchereseni.bo.SoldItem;

public interface SoldItemDAO {
	
	public void createItem(SoldItem item, int userId);

<<<<<<< HEAD
	List<SoldItem> getAllItems();
=======
	public SoldItem getSoldItemById(int itemId);
>>>>>>> origin/nolwenn_item_en_enchere

}
