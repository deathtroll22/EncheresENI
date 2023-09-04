package fr.eni.enchereseni.bll;

import fr.eni.enchereseni.bo.PickUp;
import fr.eni.enchereseni.dal.DAOFact;
import fr.eni.enchereseni.dal.PickUpDAO;

public class PickUpManagerImpl implements PickUpManager {
	
	private PickUpDAO dao = DAOFact.getPickUpDAO();

	@Override
	public void createPickUp(PickUp newPickUp, int itemId) throws ManagerException {
		
		dao.createPickUp(newPickUp, itemId);
		
	}
	
	

}
