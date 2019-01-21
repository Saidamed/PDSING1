package connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;


// Connexion pour la bd


public class Database {
	private static final String driver_name ="com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/basedetest";
	private static final String user = "root";
	private static final String password = "";
	
	static {
		try {
			Class.forName(driver_name);
			System.out.println("*** Driver ok.");
		}
		catch (ClassNotFoundException e) {
			System.err.println("ERREUR: Driver " + driver_name + "introuvable");
		} 
	}
	
	// Cette méthode créé la connexion 
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}
	
}
