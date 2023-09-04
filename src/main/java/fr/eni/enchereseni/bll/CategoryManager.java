package fr.eni.enchereseni.bll;

import java.util.List;

import fr.eni.enchereseni.bo.Category;

public interface CategoryManager {
	
	List<Category> getAllCategories() throws ManagerException;

}
