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
import fr.eni.enchereseni.dal.util.ConnectionProvider;

public class AuctionDAOImpl implements AuctionDAO {

	final String INSERT = "INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente) VALUES (?,?,?,?,?,?)";
	final String SELECT_ALL = "SELECT * FROM ARTICLES_VENDUS";
	final String SELECT_BY_USER = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur = ?";
	final String DELETE_ITEM = "DELETE FROM ARTICLES_VENDUS WHERE no_article = ?";
	final String UPDATE_ITEM = "UPDATE ARTICLES_VENDUS SET nom_article = ?, description = ?, date_debut_encheres = ?, date_fin_encheres = ?, prix_initial = ?, prix_vente = ? WHERE no_article = ?";

	@Override
	public void insert(SoldItem item) {
		try (Connection con = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
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
	public List<SoldItem> getAll() {
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
	public SoldItem findByUser(int userID) {
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
	}

	@Override
	public void delete(SoldItem item) {
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
	public void update(SoldItem item) {
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
	public void createAuction(Auction auction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAuction(Auction auction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAuction(int auctionId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Auction getAuctionById(int auctionId) {
		// TODO Auto-generated method stub
		return null;
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
	}
}
