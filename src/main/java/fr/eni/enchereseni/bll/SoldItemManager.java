package fr.eni.enchereseni.bll;

import fr.eni.enchereseni.bo.SoldItem;
import java.util.List;

public interface SoldItemManager {
    void createItem(SoldItem item, Integer userID) throws ManagerException;
    List<SoldItem> getAllItems() throws ManagerException;
}