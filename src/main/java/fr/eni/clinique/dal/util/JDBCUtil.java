package fr.eni.clinique.dal.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
	private static String connexion;
	
	public static Connection getConnexion() throws SQLException{
		Connection connection = DriverManager.getConnection(connexion);
		return connection;
	}

}
