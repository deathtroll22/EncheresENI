package fr.eni.enchereseni.dal;

import java.util.List;

import fr.eni.enchereseni.bo.SoldItem;

public interface SoldItemDAO {
	
	public void createItem(SoldItem item, int userId);


	List<SoldItem> getAllItems();

	public SoldItem getSoldItemById(int itemId);



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
