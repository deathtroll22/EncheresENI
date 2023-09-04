package fr.eni.enchereseni.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import fr.eni.enchereseni.bo.PickUp;
import fr.eni.enchereseni.dal.util.ConnectionProvider;

public class PickUpDAOImpl implements PickUpDAO {
	
	final String INSERT_PICK_UP = "INSERT INTO RETRAITS (no_article, rue, code_postal, ville) VALUES (?, ?, ?, ?)";

	@Override
	public void createPickUp(PickUp newPickUp, int itemId) {
		try (Connection con = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(INSERT_PICK_UP, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, itemId);
			stmt.setString(2, newPickUp.getStreet());
			stmt.setString(3, newPickUp.getPostalCode());
			stmt.setString(4, newPickUp.getCity());
			
			int affectedRows = stmt.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Creating pick-up failed, no rows affected.");
			}
		
	} catch (SQLException e) {
		e.printStackTrace();
	}

}
}
