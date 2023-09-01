package fr.eni.enchereseni.bll;

import java.sql.SQLException;
import java.util.List;

import fr.eni.enchereseni.bo.Category;
import fr.eni.enchereseni.bo.SoldItem;
import fr.eni.enchereseni.bo.User;
import fr.eni.enchereseni.dal.AuctionDAO;
import fr.eni.enchereseni.dal.DAOFact;

public class AuctionManagerImpl implements AuctionManager {
	
	private AuctionDAO dao = DAOFact.getAuctionDAO(); 
	
	// Gestion des utilisateurs :
	//s'inscrire
	@Override
    public void createAccount(User account) throws AuctionManagerException {
        // username and email unique
        if (dao.isUsernameTaken(account.getUsername())) {
            throw new AuctionManagerException("Username already taken.");
        }

        if (dao.isEmailTaken(account.getEmail())) {
            throw new AuctionManagerException("Email already registered.");
        }

        // username format
        if (!account.getUsername().matches("^[a-zA-Z0-9]*$")) {
            throw new AuctionManagerException("Invalid username format.");
        }

        // credit = 0
        account.setCredit(0);

        // insert user into bdd
        dao.createUser(account);
    }
	
	//se connecter
	@Override
    public User login(String loginIdentifier, String password) throws AuctionManagerException {
        // Vérification des entrées utilisateur
        if (loginIdentifier == null || loginIdentifier.isEmpty() || password == null || password.isEmpty()) {
            throw new AuctionManagerException("Both username/email and password are required.");
        }

        User user = dao.getUserByLoginIdentifier(loginIdentifier);

        if (user == null) {
            throw new AuctionManagerException("Login identifier not found.");
        }
        // vérification du mot de passe
        if (!password.equals(user.getPassword())) {
            throw new AuctionManagerException("Incorrect password.");
        }
        return user;
    }
	//afficher un profil
	@Override
	public User getUserProfileByUsername(String username) throws AuctionManagerException {
	    // Vérifiez si le nom d'utilisateur est valide (non vide)
	    if (username == null || username.isEmpty()) {
	        throw new AuctionManagerException("Username cannot be empty.");
	    }
	    
	    // Appelez la méthode de la DAL
	    return dao.getUserProfileByUsername(username);
	}

	@Override
	public void updateMyProfil(User user) throws AuctionManagerException {
	    // l'utilisateur est connecté?
	    if (user == null) {
	        throw new AuctionManagerException("User must be logged in to update profile.");
	    }

	    // l'utilisateur met à jour son propre profil?
	    User existingUser = dao.getUserProfileByUsername(user.getUsername());
	    if (existingUser == null || !existingUser.getUsername().equals(user.getUsername())) {
	        throw new AuctionManagerException("Unauthorized profile update.");
	    }

	    // méthode de la DAL
	    dao.updateMyProfil(user);
	}


	
    /*
      
    @Override
    public void deleteAccount(User user) throws AuctionManagerException {
        dao.deleteUser(user);

        // Log the user out
        logout(user);
    }
    */
    // Gestion des enchères
    @Override
    public void createItem(SoldItem item , Integer userID) throws AuctionManagerException {
        dao.createItem(item, 0);
    }
    
    //récupérer la liste des catégories
    @Override
    public List<Category> getAllCategories() throws AuctionManagerException {
		return dao.getAllCategories();
    }

}
