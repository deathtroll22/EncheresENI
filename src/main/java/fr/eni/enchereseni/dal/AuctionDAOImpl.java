package fr.eni.enchereseni.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni.enchereseni.bo.Auction;
import fr.eni.enchereseni.bo.SoldItem;
import fr.eni.enchereseni.bo.User;
import fr.eni.enchereseni.dal.util.ConnectionProvider;

public class AuctionDAOImpl implements AuctionDAO {

    final String CREATE_AUCTION = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES (?, ?, GETDATE(), ?)";
    final String UPDATE_AUCTION = "UPDATE ENCHERES SET montant_enchere = ?, date_enchere = GETDATE() WHERE no_utilisateur = ? AND no_article = ?";
    final String DELETE_AUCTION = "DELETE FROM ENCHERES WHERE no_enchere = ?";
    final String SELECT_AUCTION_BY_ID = "SELECT * FROM ENCHERES WHERE no_enchere = ?";
    final String SELECT_COUNT_AUCTION = "SELECT COUNT(*) FROM ENCHERES WHERE no_utilisateur = ? AND no_article = ?";
    final String CURRENT_AUCTION = "SELECT TOP 1 * FROM ENCHERES WHERE no_article = ? ORDER BY date_enchere DESC";
    final String GET_ACTIVE_AUCTIONS_BY_USER = "SELECT * FROM ENCHERES WHERE no_utilisateur = ? AND date_enchere >= GETDATE()";
    final String GET_AUCTIONS_BY_ITEM_ID = "SELECT * FROM ENCHERES WHERE no_article = ?";
    
	final String SELECT_MY_SOLDITEM_OPEN = "SELECT AV.*"
			+ "FROM ARTICLES_VENDUS AV"
			+ "WHERE AV.no_utilisateur = ?"
			+ "  AND AV.date_debut_encheres <= GETDATE()"
			+ "  AND AV.date_fin_encheres > GETDATE()";
	
	final String SELECT_MY_FUTUR_SOLDITEM = "SELECT AV.*"
			+ "FROM ARTICLES_VENDUS AV"
			+ "WHERE AV.no_utilisateur = ?"
			+ "  AND AV.date_debut_encheres > GETDATE()";
	
	final String SELECT_MY_EXPIRED_SOLDITEMS = "SELECT AV.*"
			+ "FROM ARTICLES_VENDUS AV"
			+ "WHERE AV.no_utilisateur = ?"
			+ "  AND AV.date_fin_encheres <= GETDATE()";
	
	final String GET_ACTIVE_AUCTIONS = "SELECT * FROM ENCHERES E INNER JOIN ARTICLES_VENDUS AV ON E.no_article = AV.no_article WHERE AV.date_fin_encheres > GETDATE()";
    final String GET_USER_ACTIVE_AUCTIONS = "SELECT E.* FROM ENCHERES E INNER JOIN ARTICLES_VENDUS AV ON E.no_article = AV.no_article"+
    										"WHERE AV.date_debut_encheres <= GETDATE() AND AV.date_fin_encheres > GETDATE() AND E.no_utilisateur = ?";
    final String SELECT_MY_WIN_AUCTIONS = "SELECT E.* FROM ENCHERES E INNER JOIN ARTICLES_VENDUS AV ON E.no_article = AV.no_article"
    		+ "WHERE AV.date_fin_encheres <= GETDATE() AND E.no_utilisateur = ? AND E.montant_enchere = "
    		+ "   ( SELECT MAX(montant_enchere)"
    		+ "    FROM ENCHERES WHERE no_article = AV.no_article)";


    @Override
    public void createOrUpdateAuction(int userId, int itemId, int bidAmount) {
        try (Connection con = ConnectionProvider.getConnection()) {
            if (isAuctionExists(userId, itemId)) {
                PreparedStatement stmt = con.prepareStatement(UPDATE_AUCTION);
                stmt.setInt(1, bidAmount);
                stmt.setInt(2, userId);
                stmt.setInt(3, itemId);
                stmt.executeUpdate();
            } else {
                PreparedStatement stmt = con.prepareStatement(CREATE_AUCTION);
                stmt.setInt(1, userId);
                stmt.setInt(2, itemId);
                stmt.setInt(3, bidAmount);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            // Gérer l'exception SQLException ici, par exemple, en la propageant sous forme de RuntimeException
            throw new RuntimeException("Erreur lors de la création ou de la mise à jour de l'enchère.", e);
        }
    }

    private boolean isAuctionExists(int userId, int itemId) {
        try (Connection con = ConnectionProvider.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(SELECT_COUNT_AUCTION);
            stmt.setInt(1, userId);
            stmt.setInt(2, itemId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int rowCount = rs.getInt(1);
                return rowCount > 0;
            }
            return false;
        } catch (SQLException e) {
            // Gérer l'exception SQLException ici, par exemple, en la propageant sous forme de RuntimeException
            throw new RuntimeException("Erreur lors de la vérification de l'existence de l'enchère.", e);
        }
    }

    public Auction getPreviousBestBidder(int itemId) {
        Auction previousBestBidder = null;
        try (Connection con = ConnectionProvider.getConnection();
             PreparedStatement stmt = con.prepareStatement(CURRENT_AUCTION)) {
            stmt.setInt(1, itemId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int userId = rs.getInt("no_utilisateur");
                int bidAmount = rs.getInt("montant_enchere");
                Date auctionDate = rs.getTimestamp("date_enchere");
                int highestBidderUserId = rs.getInt("no_enchere");
                User user = DAOFact.getUserDAO().getUserById(userId);
                SoldItem soldItem = new SoldItem(); // Vous devrez implémenter une méthode pour récupérer l'article par son ID
                previousBestBidder = new Auction(user, soldItem, auctionDate, bidAmount, highestBidderUserId);
            }
        } catch (SQLException e) {
            // Gérer l'exception SQLException ici, par exemple, en la propageant sous forme de RuntimeException
            throw new RuntimeException("Erreur lors de la récupération du meilleur enchérisseur précédent.", e);
        }
        return previousBestBidder;
    }

    @Override
    public List<Auction> getActiveAuctions(User user) {
        List<Auction> activeAuctions = new ArrayList<>();
        try (Connection con = ConnectionProvider.getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_ACTIVE_AUCTIONS_BY_USER)) {
            stmt.setInt(1, user.getUserID()); 

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // Créez des objets Auction à partir des données de la base de données
                Auction auction = extractAuctionFromResultSet(rs); 
                activeAuctions.add(auction);
            }
        } catch (SQLException e) {
            // Gérez les exceptions SQL ici
        }
        return activeAuctions;
    }

    private Auction extractAuctionFromResultSet(ResultSet rs) throws SQLException {
        int userId = rs.getInt("no_utilisateur");
        int itemId = rs.getInt("no_article");
        int bidAmount = rs.getInt("montant_enchere");
        Date auctionDate = rs.getTimestamp("date_enchere");

        // Utilisez la DAOFact pour récupérer l'utilisateur et l'article
        User user = DAOFact.getUserDAO().getUserById(userId);
        SoldItem soldItem = DAOFact.getSoldItemDAO().getSoldItemById(itemId);

        return new Auction(user, soldItem, auctionDate, bidAmount);
    }

    @Override
    public List<Auction> getAuctionsByItemId(int itemId) {
        List<Auction> auctions = new ArrayList<>();
        try (Connection con = ConnectionProvider.getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_AUCTIONS_BY_ITEM_ID)) {
            stmt.setInt(1, itemId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Auction auction = extractAuctionFromResultSet(rs);
                auctions.add(auction);
            }
        } catch (SQLException e) {
            // Gérez les exceptions SQL ici, par exemple, en la propageant sous forme de RuntimeException
            throw new RuntimeException("Erreur lors de la récupération des enchères par ID d'article.", e);
        }
        return auctions;
    }
    
    @Override
    public List<Auction> getAuctionsByRadioButton(String radioButtonValue, User user) {
        List<Auction> auctions = new ArrayList<>();

        try (Connection con = ConnectionProvider.getConnection();
             PreparedStatement stmt = createPreparedStatement(con, radioButtonValue, user)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // Créez des objets Auction à partir des données de la base de données
                Auction auction = extractAuctionFromResultSet(rs);
                auctions.add(auction);
            }
        } catch (SQLException e) {
            // Gérez les exceptions SQL ici
            throw new RuntimeException("Erreur lors de la récupération des enchères par radiobouton.", e);
        }

        return auctions;
    }

    // Méthode pour créer la PreparedStatement en fonction de la valeur du radiobouton
    private PreparedStatement createPreparedStatement(Connection con, String radioButtonValue, User user) throws SQLException {
        String query = "";

        switch (radioButtonValue) {
            case "openAuctions":
                query = GET_ACTIVE_AUCTIONS;
                break;
            case "myOngoingBids":
                query = GET_USER_ACTIVE_AUCTIONS;
                break;
            case "myWonAuctions":
                query = SELECT_MY_WIN_AUCTIONS;
                break;
            case "myOngoingsales":
                query = SELECT_MY_SOLDITEM_OPEN;
                break;
            case "salesNotStarted":
                query = SELECT_MY_FUTUR_SOLDITEM;
                break;
            case "completedSales":
                query = SELECT_MY_EXPIRED_SOLDITEMS;
                break;
            default:
                // Gestion de cas par défaut
                throw new IllegalArgumentException("Radiobouton inconnu : " + radioButtonValue);
        }

        PreparedStatement stmt = con.prepareStatement(query);

        if ("myOngoingBids".equals(radioButtonValue) || "myWonAuctions".equals(radioButtonValue) || "myOngoingsales".equals(radioButtonValue)
                || "salesNotStarted".equals(radioButtonValue) || "completedSales".equals(radioButtonValue)) {
            stmt.setInt(1, user.getUserID());
        }

        return stmt;
    }


}
