package fr.eni.enchereseni.dal.util;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionTest {

	public static void main(String[] args) {
		Connection con;
		try {
			con = ConnectionProvider.getConnection();
			System.out.println(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
