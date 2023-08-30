package fr.eni.enchereseni.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchereseni.bo.Auction;
import fr.eni.enchereseni.bo.SoldItem;
import fr.eni.enchereseni.bo.User;
import fr.eni.enchereseni.dal.util.ConnectionProvider;

public class AuctionDAOImpl implements AuctionDAO {

	

	final String CREATE_ITEM = "INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente) VALUES (?,?,?,?,?,?)";
	final String UPDATE_ITEM = "UPDATE ARTICLES_VENDUS SET nom_article = ?, description = ?, date_debut_encheres = ?, date_fin_encheres = ?, prix_initial = ?, prix_vente = ? WHERE no_article = ?";
	final String DELETE_ITEM = "DELETE FROM ARTICLES_VENDUS WHERE no_article = ?";
	final String SELECT_ALL = "SELECT * FROM ARTICLES_VENDUS";
	final String SELECT_USER_BY_USERNAME = "SELECT * FROM Users WHERE username = ?";
	final String SELECT_USER_BY_EMAIL = "SELECT * FROM Users WHERE email = ?";
	
	final String INSERT_USER = "INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	final String UPDATE_USER = "UPDATE UTILISATEURS SET pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ?, rue = ?, code_postal = ?, ville = ?, mot_de_passe = ?, credit = ?, administrateur = ? WHERE no_utilisateur = ?";
	final String DELETE_USER = "DELETE FROM UTILISATEURS WHERE no_utilisateur = ?";
	final String SUSPEND_USER = "UPDATE UTILISATEURS SET suspendu = ? WHERE no_utilisateur = ?";
	
	final String CREATE_AUCTION = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES (?,?,?,?)";
	final String UPDATE_AUCTION = "UPDATE ENCHERES SET no_utilisateur = ?, no_article = ?, date_enchere = ?, montant_enchere = ? WHERE no_enchere = ?";
	final String DELETE_AUCTION = "DELETE FROM ENCHERES WHERE no_enchere = ?";
	final String SELECT_AUCTION_BY_ID = "SELECT * FROM ENCHERES WHERE no_enchere = ?";
	
	//create user
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
	
	//méthode pour extraire les informations d'un utilisateur
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
	
	//login
	@Override
	public User login(String username, String password) {
	    User user = null;

	    try (Connection con = ConnectionProvider.getConnection();
	         PreparedStatement stmt = con.prepareStatement("SELECT * FROM UTILISATEURS WHERE pseudo = ? AND mot_de_passe = ?")) {
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
	
	//get user by login
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


/*
	@Override
	public void createItem(SoldItem item) {
	    try (Connection con = ConnectionProvider.getConnection()) {
	        PreparedStatement stmt = con.prepareStatement(CREATE_ITEM, Statement.RETURN_GENERATED_KEYS);
	        stmt.setString(1, item.getItemName());
	        stmt.setString(2, item.getItemDescription());
	        stmt.setDate(3, new java.sql.Date(item.getAuctionStartDate().getTime()));
	        stmt.setDate(4, new java.sql.Date(item.getAuctionEndDate().getTime()));
	        stmt.setDouble(5, item.getStartingPrice());
	        stmt.setDouble(6, item.getSellingPrice());

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
	public void updateItem(SoldItem item) {
	    try (Connection con = ConnectionProvider.getConnection();
	         PreparedStatement stmt = con.prepareStatement(UPDATE_ITEM)) {

	        stmt.setString(1, item.getItemName());
	        stmt.setString(2, item.getItemDescription());
	        stmt.setDate(3, new java.sql.Date(item.getAuctionStartDate().getTime()));
	        stmt.setDate(4, new java.sql.Date(item.getAuctionEndDate().getTime()));
	        stmt.setDouble(5, item.getStartingPrice());
	        stmt.setDouble(6, item.getSellingPrice());
	        stmt.setInt(7, item.getItemNumber());

	        int affectedRows = stmt.executeUpdate();

	        if (affectedRows == 0) {
	            throw new SQLException("Updating item failed, no rows affected.");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	@Override
	public void deleteItem(SoldItem item) {
	    try (Connection con = ConnectionProvider.getConnection();
	         PreparedStatement stmt = con.prepareStatement(DELETE_ITEM)) {

	        stmt.setInt(1, item.getItemNumber());

	        int affectedRows = stmt.executeUpdate();

	        if (affectedRows == 0) {
	            throw new SQLException("Deleting item failed, no rows affected.");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	
	@Override
	public List<SoldItem> getAllItem() {
	    List<SoldItem> itemList = new ArrayList<>();

	    try (Connection con = ConnectionProvider.getConnection();
	         PreparedStatement stmt = con.prepareStatement(SELECT_ALL);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            SoldItem item = new SoldItem();
	            item.setItemNumber(rs.getInt("no_article"));
	            item.setItemName(rs.getString("nom_article"));
	            item.setItemDescription(rs.getString("description"));
	            item.setAuctionStartDate(rs.getDate("date_debut_encheres"));
	            item.setAuctionEndDate(rs.getDate("date_fin_encheres"));
	            item.setStartingPrice(rs.getDouble("prix_initial"));
	            item.setSellingPrice(rs.getDouble("prix_vente"));

	            itemList.add(item);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return itemList;
	}

	@Override
	public SoldItem findBySeller(int userID) {
	    SoldItem item = null;

	    try (Connection con = ConnectionProvider.getConnection();
	         PreparedStatement stmt = con.prepareStatement(SELECT_BY_USER)) {

	        stmt.setInt(1, userID);

	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                item = new SoldItem();
	                item.setItemNumber(rs.getInt("no_article"));
	                item.setItemName(rs.getString("nom_article"));
	                item.setItemDescription(rs.getString("description"));
	                item.setAuctionStartDate(rs.getDate("date_debut_encheres"));
	                item.setAuctionEndDate(rs.getDate("date_fin_encheres"));
	                item.setStartingPrice(rs.getDouble("prix_initial"));
	                item.setSellingPrice(rs.getDouble("prix_vente"));
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return item;
	}*/

	
	
	/*
	@Override
	public void updateUser(User user) {
	    try (Connection con = ConnectionProvider.getConnection()) {
	        PreparedStatement stmt = con.prepareStatement(UPDATE_USER);
	        stmt.setString(1, user.getUsername());
	        stmt.setString(2, user.getLastName());
	        stmt.setString(3, user.getFirstName());
	        stmt.setString(4, user.getEmail());
	        stmt.setString(5, user.getPhoneNumber());
	        stmt.setString(6, user.getAddress().getStreet());
	        stmt.setString(7, user.getAddress().getPostalCode());
	        stmt.setString(8, user.getAddress().getCity());
	        stmt.setString(9, user.getPassword());
	        stmt.setInt(10, user.getCredit());
	        stmt.setBoolean(11, user.isAdmin());
	        stmt.setInt(12, user.getUserID());

	        int affectedRows = stmt.executeUpdate();

	        if (affectedRows == 0) {
	            throw new SQLException("Updating user failed, no rows affected.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void deleteUser(User user) {
	    try (Connection con = ConnectionProvider.getConnection()) {
	        PreparedStatement stmt = con.prepareStatement(DELETE_USER);
	        stmt.setInt(1, user.getUserID());

	        int affectedRows = stmt.executeUpdate();

	        if (affectedRows == 0) {
	            throw new SQLException("Deleting user failed, no rows affected.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void suspendUser(User user) {
	    try (Connection con = ConnectionProvider.getConnection()) {
	        PreparedStatement stmt = con.prepareStatement(SUSPEND_USER);
	        stmt.setBoolean(1, true); // Mettre à jour le champ suspendu en le passant à "true"
	        stmt.setInt(2, user.getUserID());

	        int affectedRows = stmt.executeUpdate();

	        if (affectedRows == 0) {
	            throw new SQLException("Suspending user failed, no rows affected.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}




	@Override
	public void createAuction(Auction auction) {
	    try (Connection con = ConnectionProvider.getConnection()) {
	        PreparedStatement stmt = con.prepareStatement(CREATE_AUCTION, Statement.RETURN_GENERATED_KEYS);
	        stmt.setInt(1, User.getUserID());
	        stmt.setInt(2, SoldItem.getItemId());
	        stmt.setTimestamp(3, new java.sql.Timestamp(auction.getAuctionDate().getTime()));
	        stmt.setInt(4, auction.getBidAmount());

	        int affectedRows = stmt.executeUpdate();

	        if (affectedRows == 0) {
	            throw new SQLException("Creating auction failed, no rows affected.");
	        }

	        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                auction.setAuctionId(generatedKeys.getInt(1));
	            } else {
	                throw new SQLException("Creating auction failed, no ID obtained.");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	@Override
	public void updateAuction(Auction auction) {
	    try (Connection con = ConnectionProvider.getConnection()) {
	        PreparedStatement stmt = con.prepareStatement(UPDATE_AUCTION);
	        stmt.setString(1, auction.getItemName());
	        stmt.setString(2, auction.getItemDescription());
	        stmt.setDate(3, new java.sql.Date(auction.getAuctionStartDate().getTime()));
	        stmt.setDate(4, new java.sql.Date(auction.getAuctionEndDate().getTime()));
	        stmt.setDouble(5, auction.getStartingPrice());
	        stmt.setDouble(6, auction.getSellingPrice());
	        stmt.setInt(7, auction.getSellerId());
	        stmt.setInt(8, auction.getCategoryId());
	        stmt.setInt(9, auction.getAuctionId());

	        int affectedRows = stmt.executeUpdate();

	        if (affectedRows == 0) {
	            throw new SQLException("Updating auction failed, no rows affected.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void deleteAuction(int auctionId) {
	    try (Connection con = ConnectionProvider.getConnection()) {
	        PreparedStatement stmt = con.prepareStatement(DELETE_AUCTION);
	        stmt.setInt(1, auctionId);

	        int affectedRows = stmt.executeUpdate();

	        if (affectedRows == 0) {
	            throw new SQLException("Deleting auction failed, no rows affected.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public Auction getAuctionById(int auctionId) {
	    Auction auction = null;

	    try (Connection con = ConnectionProvider.getConnection();
	         PreparedStatement stmt = con.prepareStatement(SELECT_AUCTION_BY_ID)) {

	        stmt.setInt(1, auctionId);

	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                auction = new Auction();
	                auction.setAuctionId(rs.getInt("no_article"));
	                auction.setItemName(rs.getString("nom_article"));
	                auction.setItemDescription(rs.getString("description"));
	                auction.setAuctionStartDate(rs.getDate("date_debut_encheres"));
	                auction.setAuctionEndDate(rs.getDate("date_fin_encheres"));
	                auction.setStartingPrice(rs.getDouble("prix_initial"));
	                auction.setSellingPrice(rs.getDouble("prix_vente"));
	                auction.setSellerId(rs.getInt("no_utilisateur"));
	                auction.setCategoryId(rs.getInt("no_categorie"));
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return auction;
	}


	@Override
	public List<Auction> getAuctionsBySeller(String sellerName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Auction> getAuctionsByName(String itemName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Auction> getAllAuctions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void placeBid(int auctionId, double bidAmount, String bidderName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeAuction(int auctionId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Auction> getActiveAuctions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Auction> getClosedAuctions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Auction> getWonAuctionsByUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Auction> getUserActiveAuctions(String username) {
		// TODO Auto-generated method stub
		return null;
	}*/

}
