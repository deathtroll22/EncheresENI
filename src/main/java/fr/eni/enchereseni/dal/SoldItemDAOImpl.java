package fr.eni.enchereseni.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchereseni.bo.Category;
import fr.eni.enchereseni.bo.PickUp;
import fr.eni.enchereseni.bo.SoldItem;
import fr.eni.enchereseni.bo.User;
import fr.eni.enchereseni.dal.util.ConnectionProvider;

public class SoldItemDAOImpl implements SoldItemDAO {

	final String CREATE_ITEM = "INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_categorie, no_utilisateur) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	final String DELETE_ITEM = "DELETE FROM ARTICLES_VENDUS WHERE no_article = ?";

	final String SELECT_ALL = "SELECT * FROM ARTICLES_VENDUS";
	final String SELECT_ARTICLE_FOR_AUCTION = 
			"SELECT av.no_article, av.nom_article, av.description, av.date_debut_encheres, av.date_fin_encheres, " +
					"av.prix_initial, av.prix_vente, av.no_categorie, av.no_utilisateur, c.libelle AS categorie, " +
					"r.rue, r.code_postal, r.ville, u.pseudo AS vendeur_pseudo, u.nom AS vendeur_nom, u.prenom AS vendeur_prenom " +
					"FROM ARTICLES_VENDUS av " +
					"LEFT JOIN CATEGORIES c ON av.no_categorie = c.no_categorie " +
					"LEFT JOIN RETRAITS r ON av.no_article = r.no_article " +
					"LEFT JOIN UTILISATEURS u ON av.no_utilisateur = u.no_utilisateur " +
					"WHERE av.no_article = ?";

	final String SELECT_ALL_ARTICLES = 
		    "SELECT av.no_article, av.nom_article, av.description, av.date_debut_encheres, av.date_fin_encheres, " +
		    "av.prix_initial, av.prix_vente, av.no_categorie, av.no_utilisateur, c.libelle AS categorie, " +
		    "r.rue, r.code_postal, r.ville, u.pseudo AS vendeur_pseudo, u.nom AS vendeur_nom, u.prenom AS vendeur_prenom " +
		    "FROM ARTICLES_VENDUS av " +
		    "LEFT JOIN CATEGORIES c ON av.no_categorie = c.no_categorie " +
		    "LEFT JOIN RETRAITS r ON av.no_article = r.no_article " +
		    "LEFT JOIN UTILISATEURS u ON av.no_utilisateur = u.no_utilisateur";


	
	final String SELECT_ARTICLES_BY_CATEGORY = """
		    SELECT av.no_article, av.nom_article, av.description, av.date_debut_encheres, av.date_fin_encheres, 
		    av.prix_initial , av.prix_vente , c.libelle, r.rue, r.code_postal, r.ville, 
		    u.pseudo , u.nom , u.prenom 
		    FROM ARTICLES_VENDUS av
		    INNER JOIN UTILISATEURS u ON av.no_utilisateur = u.no_utilisateur
		    INNER JOIN CATEGORIES c ON av.no_categorie = c.no_categorie
		    INNER JOIN RETRAITS r ON av.no_article = r.no_article
		    WHERE 1=1
		    %s
		    """;

	
	final String SELECT_SOLD_ITEMS_BY_CATEGORY = 
		    "SELECT av.no_article, av.nom_article, av.description, av.date_debut_encheres, av.date_fin_encheres, " +
		    "av.prix_initial, av.prix_vente, av.no_categorie, av.no_utilisateur, c.libelle AS categorie, " +
		    "r.rue, r.code_postal, r.ville, u.pseudo AS vendeur_pseudo, u.nom AS vendeur_nom, u.prenom AS vendeur_prenom " +
		    "FROM ARTICLES_VENDUS av " +
		    "INNER JOIN UTILISATEURS u ON av.no_utilisateur = u.no_utilisateur " +
		    "INNER JOIN CATEGORIES c ON av.no_categorie = c.no_categorie " +
		    "INNER JOIN RETRAITS r ON av.no_article = r.no_article " +
		    "WHERE 1=1 %s";
	
	final String UPDATE_ITEM = "UPDATE ARTICLES_VENDUS " +
            "SET nom_article = ?, description = ?, " +
            "date_debut_encheres = ?, date_fin_encheres = ?, " +
            "prix_initial = ?, prix_vente = ?, " +
            "no_categorie = ? " +  
            "WHERE no_article = ?";
		

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
	public List<SoldItem> getAllItems() {
		List<SoldItem> itemList = new ArrayList<>();

		try (Connection con = ConnectionProvider.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL_ARTICLES);

			while (rs.next()) {
				SoldItem item = new SoldItem();
				item.setItemNumber(rs.getInt("no_article"));
				item.setItemName(rs.getString("nom_article"));
				item.setItemDescription(rs.getString("description"));
				item.setAuctionStartDate(rs.getDate("date_debut_encheres"));
				item.setAuctionEndDate(rs.getDate("date_fin_encheres"));
				item.setStartingPrice(rs.getDouble("prix_initial"));
				item.setSellingPrice(rs.getDouble("prix_vente"));

				// Créer la catégorie et la définir dans l'objet SoldItem
				Category category = new Category();
				category.setCategoryNumber(rs.getInt("no_categorie"));
				category.setCategoryName(rs.getString("categorie"));
				item.setCategoryItem(category);

				// Créer l'emplacement de retrait et le définir dans l'objet SoldItem
				PickUp pickup = new PickUp();
				pickup.setStreet(rs.getString("rue"));
				pickup.setPostalCode(rs.getString("code_postal"));
				pickup.setCity(rs.getString("ville"));
				item.setpickUp(pickup);

				//info du vendeur
				User seller = new User();
				seller.setUserID(rs.getInt("no_utilisateur"));
				seller.setUsername(rs.getString("vendeur_pseudo"));
				seller.setLastName(rs.getString("vendeur_nom"));
				seller.setFirstName(rs.getString("vendeur_prenom"));
				item.setUser(seller);

				itemList.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return itemList;
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
				soldItem.setpickUp(pickup);

				//info du vendeur
				User seller = new User();
				seller.setUserID(rs.getInt("no_utilisateur"));
				seller.setUsername(rs.getString("vendeur_pseudo"));
				seller.setLastName(rs.getString("vendeur_nom"));
				seller.setFirstName(rs.getString("vendeur_prenom"));
				soldItem.setUser(seller);


			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return soldItem; 
	}
	
	@Override
	public List<SoldItem> getSoldItemsByCategory(int categoryId) {
	    List<SoldItem> soldItems = new ArrayList<>();

	    try (Connection con = ConnectionProvider.getConnection();
	         PreparedStatement stmt = con.prepareStatement(SELECT_SOLD_ITEMS_BY_CATEGORY)) {

	        stmt.setInt(1, categoryId);

	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            SoldItem soldItem = new SoldItem();
	            soldItem.setItemNumber(rs.getInt("no_article"));
				soldItem.setItemName(rs.getString("nom_article"));
				soldItem.setItemDescription(rs.getString("description"));
				soldItem.setAuctionStartDate(rs.getDate("date_debut_encheres"));
				soldItem.setAuctionEndDate(rs.getDate("date_fin_encheres"));
				soldItem.setStartingPrice(rs.getDouble("prix_initial"));
				soldItem.setSellingPrice(rs.getDouble("prix_vente"));
	            soldItems.add(soldItem);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return soldItems;
	}
	
	@Override
	public List<SoldItem> getAllItemsWithFilter(String whereClause) {
	    List<SoldItem> itemList = new ArrayList<>();
	    String query = SELECT_ALL_ARTICLES;

	    if (!whereClause.isEmpty()) {
	        query += " WHERE " + whereClause;
	    }

	    try (Connection con = ConnectionProvider.getConnection();
	         Statement stmt = con.createStatement()) {

	        ResultSet rs = stmt.executeQuery(query);

	        while (rs.next()) {
	            SoldItem item = new SoldItem();
	            item.setItemNumber(rs.getInt("no_article")); // Spécifiez la table 'av' pour 'no_article'
	            item.setItemName(rs.getString("nom_article")); // Spécifiez la table 'av' pour 'nom_article'
	            item.setItemDescription(rs.getString("description")); // Spécifiez la table 'av' pour 'description'
	            item.setAuctionStartDate(rs.getDate("date_debut_encheres")); // Spécifiez la table 'av' pour 'date_debut_encheres'
	            item.setAuctionEndDate(rs.getDate("date_fin_encheres")); // Spécifiez la table 'av' pour 'date_fin_encheres'
	            item.setStartingPrice(rs.getDouble("prix_initial")); // Spécifiez la table 'av' pour 'prix_initial'
	            item.setSellingPrice(rs.getDouble("prix_vente")); // Spécifiez la table 'av' pour 'prix_vente'

	            // Créer la catégorie et la définir dans l'objet SoldItem
	            Category category = new Category();
	            category.setCategoryNumber(rs.getInt("no_categorie")); // Spécifiez la table 'av' pour 'no_categorie'
	            category.setCategoryName(rs.getString("categorie"));
	            item.setCategoryItem(category);

	            // Créer l'emplacement de retrait et le définir dans l'objet SoldItem
	            PickUp pickup = new PickUp();
	            pickup.setStreet(rs.getString("rue"));
	            pickup.setPostalCode(rs.getString("code_postal"));
	            pickup.setCity(rs.getString("ville"));
	            item.setpickUp(pickup);

	            // Info du vendeur
	            User seller = new User();
	            seller.setUserID(rs.getInt("no_utilisateur"));
	            seller.setUsername(rs.getString("vendeur_pseudo"));
	            seller.setLastName(rs.getString("vendeur_nom"));
	            seller.setFirstName(rs.getString("vendeur_prenom"));
	            item.setUser(seller);

	            itemList.add(item);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return itemList;
	}

	@Override
	public void deleteItem(int itemId) {
	    try (Connection con = ConnectionProvider.getConnection()) {
	        // Commencez par supprimer l'article de la table RETRAITS s'il existe
	        String deletePickUpQuery = "DELETE FROM RETRAITS WHERE no_article = ?";
	        try (PreparedStatement deletePickUpStmt = con.prepareStatement(deletePickUpQuery)) {
	            deletePickUpStmt.setInt(1, itemId);
	            deletePickUpStmt.executeUpdate();
	        }

	        // Ensuite, supprimez l'article de la table ARTICLES_VENDUS
	        String deleteItemQuery = "DELETE FROM ARTICLES_VENDUS WHERE no_article = ?";
	        try (PreparedStatement deleteItemStmt = con.prepareStatement(deleteItemQuery)) {
	            deleteItemStmt.setInt(1, itemId);
	            int affectedRows = deleteItemStmt.executeUpdate();

	            if (affectedRows == 0) {
	                throw new SQLException("Deleting item failed, no rows affected.");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); // Vous pouvez gérer l'exception de manière appropriée ici
	    }
	}

	@Override
	public void updateSoldItem(SoldItem soldItem) {
	    try (Connection con = ConnectionProvider.getConnection()) {
	        
	        PreparedStatement stmt = con.prepareStatement(UPDATE_ITEM, Statement.RETURN_GENERATED_KEYS );
	        stmt.setString(1, soldItem.getItemName());
	        stmt.setString(2, soldItem.getItemDescription());
	        stmt.setDate(3, soldItem.getAuctionStartDate());
	        stmt.setDate(4, soldItem.getAuctionEndDate());
	        stmt.setDouble(5, soldItem.getStartingPrice());
	        stmt.setDouble(6, soldItem.getSellingPrice());
	        stmt.setInt(7, soldItem.getCategoryItem().getCategoryNumber());    

	        int affectedRows = stmt.executeUpdate();

	        if (affectedRows == 0) {
	            throw new SQLException("Updating item failed, no rows affected.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}




	/*@Override
	public List<SoldItem> getSoldItemsByCategory(
	        Integer categoryId,
	        String itemName,
	        Boolean openAuctionsFilter,
	        Boolean ongoingAuctionsFilter,
	        Boolean wonAuctionsFilter,
	        Boolean userSellingOpenAuctions,
	        Boolean userSellingNotStartedAuctions,
	        Boolean userSellingFinishedAuctions,
	        Integer userId
	){
	    List<SoldItem> result = new ArrayList<>();

	    try (Connection con = ConnectionProvider.getConnection()) {
	        String whereClause = "";

	        if (categoryId != null && categoryId != 0) {
	            whereClause += " AND av.no_categorie = ?";
	        }
	        if (itemName != null && !itemName.isEmpty()) {
	            whereClause += " AND av.nom_article LIKE ?";
	        }
	        if (openAuctionsFilter) {
	            whereClause += " AND CAST(GETDATE() AS DATE) >= CAST(av.date_debut_encheres AS DATE) " +
	                    "AND CAST(GETDATE() AS DATE) <= CAST(av.date_fin_encheres AS DATE)";
	        }
	        if (ongoingAuctionsFilter) {
	            whereClause += " AND CAST(GETDATE() AS DATE) >= CAST(av.date_debut_encheres AS DATE) " +
	                    "AND CAST(GETDATE() AS DATE) <= CAST(av.date_fin_encheres AS DATE) " +
	                    "AND ENCHERES.no_utilisateur = ?";
	        }
	        if (wonAuctionsFilter) {
	            whereClause += " AND CAST(GETDATE() AS DATE) >= CAST(av.date_fin_encheres AS DATE) " +
	                    "AND av.no_article IN (SELECT ENCHERES.no_article FROM ENCHERES " +
	                    "WHERE date_enchere = (SELECT MAX(date_enchere) FROM ENCHERES) " +
	                    "AND ENCHERES.no_utilisateur = ?)";
	        }
	        if (userSellingOpenAuctions) {
	            whereClause += " AND av.no_utilisateur = ? " +
	                    "AND CAST(GETDATE() AS DATE) >= CAST(av.date_debut_encheres AS DATE) " +
	                    "AND CAST(GETDATE() AS DATE) <= CAST(av.date_fin_encheres AS DATE)";
	        }
	        if (userSellingNotStartedAuctions) {
	            whereClause += " AND CAST(GETDATE() AS DATE) < CAST(av.date_debut_encheres AS DATE)";
	        }
	        if (userSellingFinishedAuctions) {
	            whereClause += " AND CAST(GETDATE() AS DATE) >= CAST(av.date_fin_encheres AS DATE)";
	        }

	        String query = String.format(SELECT_ARTICLES_BY_CATEGORY, whereClause);
	        PreparedStatement stmt = con.prepareStatement(query);
	        int paramIndex = 1;

	        if (categoryId != null && categoryId != 0) {
	            stmt.setInt(paramIndex++, categoryId);
	        }
	        if (itemName != null && !itemName.isEmpty()) {
	            stmt.setString(paramIndex++, "%" + itemName + "%");
	        }
	        if (ongoingAuctionsFilter) {
	            stmt.setInt(paramIndex++, userId);
	        }
	        if (wonAuctionsFilter) {
	            stmt.setInt(paramIndex++, userId);
	        }
	        if (userSellingOpenAuctions || userSellingNotStartedAuctions || userSellingFinishedAuctions) {
	            stmt.setInt(paramIndex++, userId);
	        }

	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            SoldItem soldItem = new SoldItem(
	                rs.getString("nomArticle"),
	                rs.getString("description"),
	                rs.getDate("dateDebutEnchere"),
	                rs.getDate("dateFinEnchere"),
	                rs.getInt("prixInitial"),
	                rs.getInt("prixVente"),
	                rs.getString("lienImg"), null, null, null, null
	            );
	            soldItem.setItemNumber(rs.getInt("no_article"));
	            // Vous devez ajouter le reste des informations ici comme les enchères, le vendeur, etc.

	            result.add(soldItem);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return result;
	}*/


}
