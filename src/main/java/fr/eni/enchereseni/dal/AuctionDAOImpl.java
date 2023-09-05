package fr.eni.enchereseni.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni.enchereseni.bll.ManagerException;
import fr.eni.enchereseni.bo.Auction;
import fr.eni.enchereseni.bo.Category;
import fr.eni.enchereseni.bo.SoldItem;
import fr.eni.enchereseni.bo.User;
import fr.eni.enchereseni.dal.util.ConnectionProvider;

public class AuctionDAOImpl implements AuctionDAO {


	final String CREATE_AUCTION = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere)VALUES (?, ?, GETDATE(), ?)";
	final String UPDATE_AUCTION = "UPDATE ENCHERES SET montant_enchere = ?, date_enchere = GETDATE() WHERE no_utilisateur = ? AND no_article = ?";
	final String DELETE_AUCTION = "DELETE FROM ENCHERES WHERE no_enchere = ?";
	final String SELECT_AUCTION_BY_ID = "SELECT * FROM ENCHERES WHERE no_enchere = ?";
	final String SELECT_COUNT_AUCTION = "SELECT COUNT(*) FROM ENCHERES WHERE no_utilisateur = ? AND no_article = ?";
	final String CURRENT_AUCTION = "SELECT TOP 1 * FROM ENCHERES WHERE no_article = ? ORDER BY date_enchere DESC";
	
	
	@Override
	public void createOrUpdateAuction(int userId, int itemId, int bidAmount) throws ManagerException {

	    java.util.Date utilDate = new java.util.Date();
	    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());


	    try (Connection con = ConnectionProvider.getConnection()) {
	        // Vérifie s'il existe déjà une enchère pour cet utilisateur et cet article
	        // Si oui, met à jour l'enchère, sinon insére une nouvelle enchère
	        if (isAuctionExists(userId, itemId)) {
	        	PreparedStatement stmt = con.prepareStatement(UPDATE_AUCTION);
	        	stmt.setInt(1, userId);
	        	stmt.setInt(2, itemId);
	        	stmt.setInt(3, bidAmount);
	        	stmt.setDate(4, new java.sql.Date(System.currentTimeMillis()));
	        	stmt.executeUpdate();
	        } else {
	        	PreparedStatement stmt = con.prepareStatement(CREATE_AUCTION);
	            stmt.setInt(1, userId);
	            stmt.setInt(2, itemId);
	            stmt.setDate(3, new java.sql.Date(System.currentTimeMillis()));
	            stmt.setInt(4, bidAmount);
	            stmt.executeUpdate();
	        }
	    } catch (SQLException e) {
	        // Gérer l'exception SQLException ici, par exemple, en la propageant sous forme de ManagerException
	        throw new ManagerException("Erreur lors de la création ou de la mise à jour de l'enchère.", e);
	    }
	     
	}
	

	private boolean isAuctionExists(int userId, int itemId) throws ManagerException, SQLException {

	    try (Connection con = ConnectionProvider.getConnection()) {
            
            PreparedStatement stmt = con.prepareStatement(SELECT_COUNT_AUCTION);
	        stmt.setInt(1, userId);
	        stmt.setInt(2, itemId);
	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            int rowCount = rs.getInt(1);
	            return rowCount > 0; // Si le nombre de lignes est supérieur à zéro, l'enchère existe déjà
	        }
	        
	        return false; // Aucune enchère existante
	    } 
	}
	
	public Auction getPreviousBestBidder(int itemId) throws ManagerException {
	    Auction previousBestBidder = null;
	    try (Connection con = ConnectionProvider.getConnection();
	         PreparedStatement stmt = con.prepareStatement(CURRENT_AUCTION)) {
	        stmt.setInt(1, itemId);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            // Récupérez les informations de l'enchère la plus récente
	            int userId = rs.getInt("no_utilisateur");
	            int bidAmount = rs.getInt("montant_enchere");
	            Date auctionDate = rs.getTimestamp("date_enchere");
	            int highestBidderUserId = rs.getInt("no_enchere");

	            // Créez un objet Auction avec ces informations
	            User user = new User(); // Vous devrez implémenter une méthode pour récupérer l'utilisateur par son ID
	            SoldItem soldItem = new SoldItem(); // Vous devrez implémenter une méthode pour récupérer l'article par son ID
	            previousBestBidder = new Auction(user, soldItem, auctionDate, bidAmount, highestBidderUserId);
	        }
	    } catch (SQLException e) {
	        // Gérer l'exception SQLException ici, par exemple, en la propageant sous forme de ManagerException
	        throw new ManagerException("Erreur lors de la récupération du meilleur enchérisseur précédent.", e);
	    }
	    return previousBestBidder;
	}


}


