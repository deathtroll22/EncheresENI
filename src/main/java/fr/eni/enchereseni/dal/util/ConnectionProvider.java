package fr.eni.enchereseni.dal.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionProvider {
	private static String urldb;
	private static String userdb;
	private static String passworddb;
	private static DataSource dataSource;

	static {
		// tentative d'accès à la configuration par le context.xml (cas WEB)
		Context context;
		try {
			context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/pool_cnx");
		} catch (NamingException e) {
			e.printStackTrace();
			// pas possible d'accéder à la configuration WEB, on va donc utilisé le
			// settings.properties
			try {
				Properties properties = new Properties();
				properties.load(ConnectionProvider.class.getResourceAsStream("settings.properties"));
				Class.forName(properties.getProperty("driverdb"));
				urldb = properties.getProperty("urldb");
				userdb = properties.getProperty("userdb");
				passworddb = properties.getProperty("passworddb");
			} catch (ClassNotFoundException e1) {
				System.out.println("Votre jar de connection à la base de données n'est pas présent dans le classPath");
			} catch (IOException e1) {
			}
	}

	}

	public static Connection getConnection() throws SQLException {
		if(dataSource!=null) { // nous somme en web
			return ConnectionProvider.dataSource.getConnection();
		}
		else { // nous ne somme pas en web
			return DriverManager.getConnection(urldb, userdb, passworddb);
		}
	}
}
