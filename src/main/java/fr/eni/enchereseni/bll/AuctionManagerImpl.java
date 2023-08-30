package fr.eni.enchereseni.bll;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.eni.enchereseni.bo.Auction;
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

	
    /*
    @Override
    public void logout(User user) throws AuctionManagerException {
        // nettoyage ?

        // Update user status
        user.setLoggedIn(false);
        dao.updateUser(user);
    }
    
    @Override
    public User viewOtherUserProfile(String username) throws AuctionManagerException {
        
        return dao.getUserProfileByUsername(username);
    }
    
    @Override
    public void editMyProfile(User user) throws AuctionManagerException {
        dao.updateUser(user);
    }
    
    @Override
    public void deleteAccount(User user) throws AuctionManagerException {
        dao.deleteUser(user);

        // Log the user out
        logout(user);
    }
    
    // Gestion des enchères
    @Override
    public void sellItem(SoldItem item) throws AuctionManagerException {
        dao.createItem(item);
    }
    
    @Override
    public List<Auction> getClosedAuctions() throws AuctionManagerException {
        return dao.getUserClosedAuctions();
    }
    
    @Override
    public List<Auction> getActiveAuctions(User user) throws AuctionManagerException {
        return dao.getUserActiveAuctions(user);
    }
    
 // Map pour stocker les enchères avec leurs enchérisseurs et montants
    private Map<Auction, Map<User, Integer>> auctionBids = new HashMap<>();

    @Override
    public void bid(User bidder, Auction auction, Integer bidAmount) throws AuctionManagerException {
        // Vérifiez si le montant de l'enchère est supérieur à l'enchère actuelle et si le crédit de l'utilisateur est suffisant
        if (bidAmount <= auction.getBidAmount() || bidder.getCredit() < bidAmount) {
            throw new AuctionManagerException("Invalid bid.");
        }

        // Vérifier si l'enchère est déjà dans la liste des enchères avec les enchérisseurs et montants
        if (!auctionBids.containsKey(auction)) {
            auctionBids.put(auction, new HashMap<>());
        }

        Map<User, Integer> bids = auctionBids.get(auction);

        // Mettre à jour le crédit du précédent plus offrant
        if (bids.containsKey(auction.getHighestBidder())) {
            Integer previousBid = bids.get(auction.getHighestBidder());
            auction.getHighestBidder().setCredit(auction.getHighestBidder().getCredit() + previousBid);
        }

        // Déduire le montant de l'enchère du crédit de l'enchérisseur
        bidder.setCredit(bidder.getCredit() - bidAmount);

        // Mettre à jour les détails de l'enchère
        bids.put(bidder, bidAmount);
        auctionBids.put(auction, bids);

        // Mettre à jour les détails de l'enchère et les crédits utilisateur
        dao.updateAuction(auction);
        dao.updateUserCredit(bidder);
    }
    
    @Override
    public void winSale(User winner, Auction auction) throws AuctionManagerException {
        dao.markAuctionAsWon(winner, auction);
    }
    
    @Override
    public Auction detailsAuction(User user, Auction auction) throws AuctionManagerException {
        // auction details
        Auction detailedAuction = dao.getAuctionDetails(auction);

        // Check if the user is the seller and the auction has not started
        boolean isSeller = user.equals(detailedAuction.getSeller());
        boolean isAuctionNotStarted = !detailedAuction.isStarted();

        // Update the auction details to reflect user's permissions
        detailedAuction.setCanModify(isSeller && isAuctionNotStarted);
        detailedAuction.setCanBid(!isSeller && isAuctionNotStarted);

        return detailedAuction;
    }*/

}
