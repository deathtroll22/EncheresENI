package fr.eni.enchereseni.bll;

import fr.eni.enchereseni.bo.User;
import fr.eni.enchereseni.dal.DAOFact;
import fr.eni.enchereseni.dal.UserDAO;
import fr.eni.enchereseni.dal.UserDAOImpl;

public class UserManagerImpl implements UserManager {
	
	private UserDAO dao = DAOFact.getUserDAO();
	
	//s'inscrire
		@Override
	    public void createAccount(User account) throws ManagerException {
	        // username and email unique
	        if (dao.isUsernameTaken(account.getUsername())) {
	            throw new ManagerException("Username already taken.");
	        }

	        if (dao.isEmailTaken(account.getEmail())) {
	            throw new ManagerException("Email already registered.");
	        }

	        // username format
	        if (!account.getUsername().matches("^[a-zA-Z0-9]*$")) {
	            throw new ManagerException("Invalid username format.");
	        }

	        // credit = 0
	        account.setCredit(0);

	        // insert user into bdd
	        dao.createUser(account);
	    }
		
		//se connecter
		@Override
	    public User login(String loginIdentifier, String password) throws ManagerException {
	        // Vérification des entrées utilisateur
	        if (loginIdentifier == null || loginIdentifier.isEmpty() || password == null || password.isEmpty()) {
	            throw new ManagerException("Both username/email and password are required.");
	        }

	        User user = dao.getUserByLoginIdentifier(loginIdentifier);

	        if (user == null) {
	            throw new ManagerException("Login identifier not found.");
	        }
	        // vérification du mot de passe
	        if (!password.equals(user.getPassword())) {
	            throw new ManagerException("Incorrect password.");
	        }
	        return user;
	    }
		//afficher un profil
		@Override
		public User getUserProfileByUsername(String username) throws ManagerException {
		    // Vérifiez si le nom d'utilisateur est valide (non vide)
		    if (username == null || username.isEmpty()) {
		        throw new ManagerException("Username cannot be empty.");
		    }
		    
		    // Appelez la méthode de la DAL
		    return dao.getUserProfileByUsername(username);
		}

		@Override
		public void updateMyProfil(User user) throws ManagerException {
		    // l'utilisateur est connecté?
		    if (user == null) {
		        throw new ManagerException("User must be logged in to update profile.");
		    }

		    // l'utilisateur met à jour son propre profil?
		    User existingUser = dao.getUserProfileByUsername(user.getUsername());
		    if (existingUser == null || !existingUser.getUsername().equals(user.getUsername())) {
		        throw new ManagerException("Unauthorized profile update.");
		    }

		    // méthode de la DAL
		    dao.updateMyProfil(user);
		}


	    @Override
	    public void deleteUser(Integer userID) {
	        // Appeler la méthode de suppression de l'utilisateur depuis la couche DAO
	        UserDAO userDAO = new UserDAOImpl();
	        userDAO.deleteUser(userID);
	    }

		@Override
		public User getUserById(int userId) throws ManagerException {
		    
		    if (userId <= 0) {
		        throw new ManagerException("Invalid user ID.");
		    }
		    
		   
		    User user = dao.getUserById(userId);

		    if (user == null) {
		        throw new ManagerException("User not found.");
		    }

		    return user;
		}

		@Override
		public boolean isUsernameTaken(String username) throws ManagerException {
			return this.dao.isUsernameTaken(username);
		}

		@Override
		public boolean isEmailTaken(String email) throws ManagerException {
			return this.dao.isEmailTaken(email);
		}

		@Override
		public void updateUserCredit(Integer userID, int credit) throws ManagerException {
			dao.updateUserCredit(userID, credit);
		}




}
