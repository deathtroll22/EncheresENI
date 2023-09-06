package fr.eni.enchereseni.bll;

import fr.eni.enchereseni.bo.SoldItem;
import java.util.List;

public interface SoldItemManager {

	
	public void createItem(SoldItem item , Integer userID) throws ManagerException;

	public SoldItem getSoldItemById(int itemId)throws ManagerException;

	List<SoldItem> getAllItems() throws ManagerException;
	
	List<SoldItem> getSoldItemsByCategory(
		    Integer categoryId,
		    String itemName,
		    Boolean openAuctionsFilter,
		    Boolean ongoingAuctionsFilter,
		    Boolean wonAuctionsFilter,
		    Boolean userSellingOpenAuctions,
		    Boolean userSellingNotStartedAuctions,
		    Boolean userSellingFinishedAuctions,
		    Integer userId
		);

}

