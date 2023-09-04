package fr.eni.enchereseni.bll;

import fr.eni.enchereseni.bo.PickUp;

public interface PickUpManager {

	void createPickUp(PickUp newPickUp, int itemId) throws ManagerException;

}
