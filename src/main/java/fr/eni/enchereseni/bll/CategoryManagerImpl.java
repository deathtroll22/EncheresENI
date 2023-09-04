package fr.eni.enchereseni.bll;

import java.util.List;

import fr.eni.enchereseni.bo.Category;
import fr.eni.enchereseni.dal.CategoryDAO;
import fr.eni.enchereseni.dal.DAOFact;

public class CategoryManagerImpl implements CategoryManager {
	
	private CategoryDAO dao = DAOFact.getCategoryDAO();
	
	@Override
    public List<Category> getAllCategories() throws ManagerException {
		return dao.getAllCategories();
    }

}
