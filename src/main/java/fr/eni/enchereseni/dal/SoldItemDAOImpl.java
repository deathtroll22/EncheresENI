package fr.eni.enchereseni.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import fr.eni.enchereseni.bo.Category;
import fr.eni.enchereseni.bo.PickUp;
import fr.eni.enchereseni.bo.SoldItem;
import fr.eni.enchereseni.dal.util.ConnectionProvider;

public class SoldItemDAOImpl implements SoldItemDAO {
	
	final String CREATE_ITEM = "INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_categorie, no_utilisateur) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	final String UPDATE_ITEM = "UPDATE ARTICLES_VENDUS SET nom_article = ?, description = ?, date_debut_encheres = ?, date_fin_encheres = ?, prix_initial = ?, prix_vente = ? WHERE no_article = ?";
	final String DELETE_ITEM = "DELETE FROM ARTICLES_VENDUS WHERE no_article = ?";
	final String SELECT_ALL = "SELECT * FROM ARTICLES_VENDUS";
	final String SELECT_ARTICLE_FOR_AUCTION =   "SELECT av.no_article, av.nom_article, av.description, av.date_debut_encheres, av.date_fin_encheres, av.prix_initial, av.prix_vente, av.no_categorie, av.no_utilisateur, c.libelle AS categorie, r.rue, r.code_postal, r.ville " +
									"FROM ARTICLES_VENDUS av " +
									"LEFT JOIN CATEGORIES c ON av.no_categorie = c.no_categorie " +
									"LEFT JOIN RETRAITS r ON av.no_article = r.no_article " +
									"WHERE ai.no_article = ?";
	
	
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


	@Override
    public SoldItem getSoldItemById(int itemId) {
		
		SoldItem soldItem = null;
		
        try (Connection con = ConnectionProvider.getConnection()) {
            
            PreparedStatement stmt = con.prepareStatement(SELECT_ARTICLE_FOR_AUCTION);
            stmt.setInt(1, itemId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                soldItem = new SoldItem();
                soldItem.setItemNumber(rs.getInt("no_article"));
                soldItem.setItemName(rs.getString("nom_article"));
                soldItem.setItemDescription(rs.getString("description"));
                soldItem.setAuctionStartDate(rs.getDate("date_debut_encheres"));
                soldItem.setAuctionEndDate(rs.getDate("date_fin_encheres"));
                soldItem.setStartingPrice(rs.getDouble("prix_initial"));
                soldItem.setSellingPrice(rs.getDouble("prix_vente"));
                soldItem.setSaleStatus(rs.getString("sale_status"));

                // Créer la catégorie et la définir dans l'objet SoldItem
                Category category = new Category();
                category.setCategoryNumber(rs.getInt("no_categorie"));
                category.setCategoryName(rs.getString("categorie"));
                soldItem.setCategoryItem(category);

                // Créer l'emplacement de retrait et le définir dans l'objet SoldItem
                PickUp pickup = new PickUp();
                pickup.setStreet(rs.getString("rue"));
                pickup.setPostalCode(rs.getString("code_postal"));
                pickup.setCity(rs.getString("ville"));
                soldItem.setWithdrawalLocation(pickup);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return soldItem; 
    }

}
