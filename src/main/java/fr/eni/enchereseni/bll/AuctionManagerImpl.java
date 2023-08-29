package fr.eni.enchereseni.bll;

import java.util.List;

import fr.eni.enchereseni.bo.User;
import fr.eni.enchereseni.dal.AuctionDAO;
import fr.eni.enchereseni.dal.DAOFact;

public class AuctionManagerImpl implements AuctionManager {

	private AuctionDAO dao = DAOFact.getAuctionDAO(); 

    @Override
    public void createAccount(User account) throws AuctionManagerException {
        // Validate the username and email uniqueness
        if (dao.isUsernameTaken(account.getUsername())) {
            throw new AuctionManagerException("Username already taken.");
        }

        if (dao.isEmailTaken(account.getEmail())) {
            throw new AuctionManagerException("Email already registered.");
        }

        // Validate the username format (alphanumeric)
        if (!account.getUsername().matches("^[a-zA-Z0-9]*$")) {
            throw new AuctionManagerException("Invalid username format.");
        }

        // Set initial credit to 0
        account.setCredit(0);

        // Assuming you have a method to hash passwords before storing
        String hashedPassword = hashPassword(account.getPassword());
        account.setPassword(hashedPassword);

        // Assuming you have a DAO method to insert the user into the database
        dao.insertUser(account);
    }

}
