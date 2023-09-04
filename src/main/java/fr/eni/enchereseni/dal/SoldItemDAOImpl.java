package fr.eni.enchereseni.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import fr.eni.enchereseni.bo.SoldItem;
import fr.eni.enchereseni.dal.util.ConnectionProvider;

public class SoldItemDAOImpl implements SoldItemDAO {
	
	final String CREATE_ITEM = "INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_categorie, no_utilisateur) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	final String UPDATE_ITEM = "UPDATE ARTICLES_VENDUS SET nom_article = ?, description = ?, date_debut_encheres = ?, date_fin_encheres = ?, prix_initial = ?, prix_vente = ? WHERE no_article = ?";
	final String DELETE_ITEM = "DELETE FROM ARTICLES_VENDUS WHERE no_article = ?";
	final String SELECT_ALL = "SELECT * FROM ARTICLES_VENDUS";
	
	
	@Override
	public void createItem(SoldItem item, int userId) {
	    try (Connection con = ConnectionProvider.getConnection()) {
	        
	        PreparedStatement stmt = con.prepareStatement(CREATE_ITEM, Statement.RETURN_GENERATED_KEYS);
	        stmt.setString(1, item.getItemName());
	        stmt.setString(2, item.getItemDescription());
	        stmt.setDate(3, new java.sql.Date(item.getAuctionStartDate().getTime()));
	        stmt.setDate(4, new java.sql.Date(item.getAuctionEndDate().getTime()));
	        stmt.setDouble(5, item.getStartingPrice());
	        stmt.setDouble(6, item.getSellingPrice());

	        // Obtenez l'identifiant de catégorie à partir de l'objet categoryItem
	        if (item.getCategoryItem() != null) {
	            stmt.setInt(7, item.getCategoryItem().getCategoryNumber());
	        } else {
	            stmt.setNull(7, Types.INTEGER);
	        }

	        // Définissez le no_utilisateur à partir de l'utilisateur connecté
	        stmt.setInt(8, userId);

	        int affectedRows = stmt.executeUpdate();

	        if (affectedRows == 0) {
	            throw new SQLException("Creating item failed, no rows affected.");
	        }

	        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                item.setItemNumber(generatedKeys.getInt(1));
	            } else {
	                throw new SQLException("Creating item failed, no ID obtained.");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

}