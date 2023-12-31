package fr.eni.enchereseni.bll;

import java.sql.SQLException;
import java.util.List;
import fr.eni.enchereseni.bo.SoldItem;
import fr.eni.enchereseni.dal.DAOFact;
import fr.eni.enchereseni.dal.SoldItemDAO;

public class SoldItemManagerImpl implements SoldItemManager {
	
	private SoldItemDAO dao = DAOFact.getSoldItemDAO();
	
	@Override
    public void createItem(SoldItem item, Integer userID) throws ManagerException {
        dao.createItem(item, userID);
    }

	@Override

	public List<SoldItem> getAllItems() throws ManagerException {
        try {
            return dao.getAllItems();
        } catch (Exception e) {
            throw new ManagerException("Error while getting all items", e);
        }
	}

	public SoldItem getSoldItemById(int itemId) throws ManagerException {
		
		SoldItem soldItem = dao.getSoldItemById(itemId);
		
		return soldItem;
	}

	@Override
	public List<SoldItem> getSoldItemsByCategory(int categoryId) throws ManagerException {
	    return dao.getSoldItemsByCategory(categoryId);
	}
	
	@Override
	public List<SoldItem> getAllItemsWithFilter(String whereClause) throws ManagerException {
	    try {
	        return dao.getAllItemsWithFilter(whereClause);
	    } catch (Exception e) {
	        throw new ManagerException("Error while getting filtered items", e);
	    }
	}
	@Override
	public void deleteItem(int itemId) throws ManagerException {
	dao.deleteItem(itemId);
	}

	@Override
	public void updateSoldItem(SoldItem solItem) throws ManagerException {
		dao.updateSoldItem(solItem);		
	}




}
