package fr.eni.enchereseni.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchereseni.bo.Category;
import fr.eni.enchereseni.dal.util.ConnectionProvider;

public class CategoryDAOImpl implements CategoryDAO {
	
	final String SELECT_CATEGORY = "SELECT * FROM CATEGORIES";
	
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
