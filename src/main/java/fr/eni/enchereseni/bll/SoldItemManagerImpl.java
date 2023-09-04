package fr.eni.enchereseni.bll;

import fr.eni.enchereseni.bo.SoldItem;
import fr.eni.enchereseni.dal.DAOFact;
import fr.eni.enchereseni.dal.SoldItemDAO;

public class SoldItemManagerImpl implements SoldItemManager {
	
	private SoldItemDAO dao = DAOFact.getSoldItemDAO();
	
	@Override
    public void createItem(SoldItem item , Integer userID) throws ManagerException {
        dao.createItem(item, userID);
    }

}
