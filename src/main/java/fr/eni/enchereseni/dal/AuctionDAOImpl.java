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
import fr.eni.enchereseni.bo.SoldItem;
import fr.eni.enchereseni.bo.User;
import fr.eni.enchereseni.dal.util.ConnectionProvider;

public class AuctionDAOImpl implements AuctionDAO {

	final String SELECT_CATEGORY = "SELECT * FROM CATEGORIES";
	final String CREATE_ITEM = "INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente) VALUES (?,?,?,?,?,?)";
	final String UPDATE_ITEM = "UPDATE ARTICLES_VENDUS SET nom_article = ?, description = ?, date_debut_encheres = ?, date_fin_encheres = ?, prix_initial = ?, prix_vente = ? WHERE no_article = ?";
	final String DELETE_ITEM = "DELETE FROM ARTICLES_VENDUS WHERE no_article = ?";
	final String SELECT_ALL = "SELECT * FROM ARTICLES_VENDUS";
	final String SELECT_USER_BY_USERNAME = "SELECT * FROM UTILISATEURS WHERE pseudo = ?";
	final String SELECT_USER_BY_EMAIL = "SELECT * FROM UTILISATEURS WHERE email = ?";

	final String INSERT_USER = "INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	final String UPDATE_USER = "UPDATE UTILISATEURS SET pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ?, rue = ?, code_postal = ?, ville = ?, mot_de_passe = ?, credit = ?, administrateur = ? WHERE no_utilisateur = ?";
	final String DELETE_USER = "DELETE FROM UTILISATEURS WHERE no_utilisateur = ?";
	final String SUSPEND_USER = "UPDATE UTILISATEURS SET suspendu = ? WHERE no_utilisateur = ?";

	final String CREATE_AUCTION = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES (?,?,?,?)";
	final String UPDATE_AUCTION = "UPDATE ENCHERES SET no_utilisateur = ?, no_article = ?, date_enchere = ?, montant_enchere = ? WHERE no_enchere = ?";
	final String DELETE_AUCTION = "DELETE FROM ENCHERES WHERE no_enchere = ?";
	final String SELECT_AUCTION_BY_ID = "SELECT * FROM ENCHERES WHERE no_enchere = ?";

	// create user
	@Override
	public void createUser(User user) {
		try (Connection con = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getLastName());
			stmt.setString(3, user.getFirstName());
			stmt.setString(4, user.getEmail());
			stmt.setString(5, user.getPhoneNumber());
			stmt.setString(6, user.getStreet());
			stmt.setString(7, user.getPostalCode());
			stmt.setString(8, user.getCity());
			stmt.setString(9, user.getPassword());
			stmt.setInt(10, user.getCredit());
			stmt.setBoolean(11, user.isAdmin());

			int affectedRows = stmt.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Creating user failed, no rows affected.");
			}

			try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					user.setUserID(generatedKeys.getInt(1));
				} else {
					throw new SQLException("Creating user failed, no ID obtained.");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isUsernameTaken(String username) {
		try (Connection con = ConnectionProvider.getConnection();
				PreparedStatement stmt = con.prepareStatement("SELECT COUNT(*) FROM Users WHERE username = ?")) {
			stmt.setString(1, username);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					int count = rs.getInt(1);
					return count > 0;
				}
			}
		} catch (SQLException e) {
			// Handle exception
		}
		return false;
	}

	@Override
	public boolean isEmailTaken(String email) {
		try (Connection con = ConnectionProvider.getConnection();
				PreparedStatement stmt = con.prepareStatement("SELECT COUNT(*) FROM Users WHERE email = ?")) {
			stmt.setString(1, email);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					int count = rs.getInt(1);
					return count > 0;
				}
			}
		} catch (SQLException e) {
			// Handle exception
		}
		return false;
	}

	// méthode pour extraire les informations d'un utilisateur
	private User extractUserFromResultSet(ResultSet rs) throws SQLException {
		User user = new User();
		user.setUserID(rs.getInt("no_utilisateur"));
		user.setUsername(rs.getString("pseudo"));
		user.setLastName(rs.getString("nom"));
		user.setFirstName(rs.getString("prenom"));
		user.setEmail(rs.getString("email"));
		user.setPhoneNumber(rs.getString("telephone"));
		user.setStreet(rs.getString("rue"));
		user.setPostalCode(rs.getString("code_postal"));
		user.setCity(rs.getString("ville"));
		user.setPassword(rs.getString("mot_de_passe"));
		user.setCredit(rs.getInt("credit"));
		user.setAdmin(rs.getBoolean("administrateur"));
		return user;
	}

	// login
	@Override
	public User login(String username, String password) {
		User user = null;

		try (Connection con = ConnectionProvider.getConnection();
				PreparedStatement stmt = con
						.prepareStatement("SELECT * FROM UTILISATEURS WHERE pseudo = ? AND mot_de_passe = ?")) {
			stmt.setString(1, username);
			stmt.setString(2, password);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					user = extractUserFromResultSet(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	// get user by login
	@Override
	public User getUserByLoginIdentifier(String loginIdentifier) {
		User user = null;

		try (Connection con = ConnectionProvider.getConnection();
				PreparedStatement stmt = con.prepareStatement("SELECT * FROM UTILISATEURS WHERE pseudo = ?")) {
			stmt.setString(1, loginIdentifier);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					user = extractUserFromResultSet(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	// afficher un profil
	@Override
	public User getUserProfileByUsername(String username) {
		User user = null;

		try (Connection con = ConnectionProvider.getConnection();
				PreparedStatement stmt = con.prepareStatement(SELECT_USER_BY_USERNAME)) {
			stmt.setString(1, username);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					user = extractUserFromResultSet(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	// update my profil
	@Override
	public void updateMyProfil(User user) {
		try (Connection con = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(UPDATE_USER);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getLastName());
			stmt.setString(3, user.getFirstName());
			stmt.setString(4, user.getEmail());
			stmt.setString(5, user.getPhoneNumber());
			stmt.setString(6, user.getStreet());
			stmt.setString(7, user.getPostalCode());
			stmt.setString(8, user.getCity());
			stmt.setString(9, user.getPassword());
			stmt.setInt(10, user.getCredit());
			stmt.setBoolean(11, user.isAdmin());
			stmt.setInt(12, user.getUserID());

			int affectedRows = stmt.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Updating user profile failed, no rows affected.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void createItem(SoldItem item, int userId) {
	    try (Connection con = ConnectionProvider.getConnection()) {
	        String CREATE_ITEM = "INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_categorie, no_utilisateur) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
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

	public List<Category> getAllCategories() {
		List<Category> categories = new ArrayList<>();

		try (Connection con = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(SELECT_CATEGORY, Statement.RETURN_GENERATED_KEYS);
			{

				try (ResultSet rs = stmt.executeQuery()) {
					while (rs.next()) {
						int categoryId = rs.getInt("no_categorie");
						String categoryName = rs.getString("libelle");

						Category category = new Category(categoryId, categoryName);
						categories.add(category);
					}
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categories;
	}
}


