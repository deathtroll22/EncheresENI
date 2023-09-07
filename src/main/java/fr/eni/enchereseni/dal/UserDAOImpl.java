package fr.eni.enchereseni.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.eni.enchereseni.bo.User;
import fr.eni.enchereseni.dal.util.ConnectionProvider;

public class UserDAOImpl implements UserDAO {

	final String INSERT_USER = "INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	final String UPDATE_USER = "UPDATE UTILISATEURS SET pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ?, rue = ?, code_postal = ?, ville = ?, mot_de_passe = ?, credit = ?, administrateur = ? WHERE no_utilisateur = ?";
	final String DELETE_USER = "DELETE FROM UTILISATEURS WHERE no_utilisateur = ?";
	final String SUSPEND_USER = "UPDATE UTILISATEURS SET suspendu = ? WHERE no_utilisateur = ?";

	final String SELECT_USER_BY_USERNAME = "SELECT * FROM UTILISATEURS WHERE pseudo = ?";
	final String SELECT_USER_BY_EMAIL = "SELECT * FROM UTILISATEURS WHERE email = ?";
	final String SELECT_USER_BY_ID = "SELECT * FROM UTILISATEURS WHERE no_utilisateur = ?";
	final String COUNT_USERNAME = "SELECT COUNT(*) FROM UTILISATEURS WHERE pseudo = ?";
	final String COUNT_USER_MAIL = "SELECT COUNT(*) FROM UTILISATEURS WHERE email = ?";
	final String UPDATE_CREDIT_USER = "UPDATE UTILISATEURS SET credit = ? WHERE no_utilisateur = ?";

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
	public void deleteUser(Integer userID) {
		try (Connection con = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(DELETE_USER);
			stmt.setInt(1, userID);

			int affectedRows = stmt.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Deleting user failed, no rows affected.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isUsernameTaken(String username) {
		try (Connection con = ConnectionProvider.getConnection();
				PreparedStatement stmt = con.prepareStatement(COUNT_USERNAME)) {
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
	public User getUserById(int userId) {
		User user = null;

		try (Connection con = ConnectionProvider.getConnection();
				PreparedStatement stmt = con.prepareStatement(SELECT_USER_BY_ID)) {
			stmt.setInt(1, userId);

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
	@Override
	public boolean isEmailTaken(String email) {
		try (Connection con = ConnectionProvider.getConnection();
				PreparedStatement stmt = con.prepareStatement(COUNT_USER_MAIL)) {
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
				PreparedStatement stmt = con.prepareStatement(SELECT_USER_BY_USERNAME)) {
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
	public void updateUserCredit(Integer userID, int credit) {
	    
	    try (Connection con = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(UPDATE_CREDIT_USER);	        
			stmt.setInt(1, credit);
			stmt.setInt(2, userID);

	        int rowsUpdated = stmt.executeUpdate();

	        if (rowsUpdated != 1) {
	            throw new SQLException("Failed to update user credit.");
	        }
	    } catch (SQLException e) {
			e.printStackTrace();
	    } 
	}


}
