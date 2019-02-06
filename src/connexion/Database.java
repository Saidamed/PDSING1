package connexion;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;

// Connexion pour la bd

public class Database {
	private static final String driver_name = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/basedetest";
	private static final String user = "root";
	private static final String password = "";
	private ArrayList<Connection> pool;
	private ArrayList<Connection> poolInUse;
	private int nbConnection = 0;

	public Database(int nbCo) {
		this.nbConnection = nbCo;
		this.pool = new ArrayList<>();
		this.poolInUse = new ArrayList<>();
		connectAll();
	}

	static {
		try {
			Class.forName(driver_name);
			System.out.println("*** Driver ok.");
		} catch (ClassNotFoundException e) {
			System.err.println("ERREUR: Driver " + driver_name + "introuvable");
		}
	}

	// Create the connection
	public static Connection connect() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}
	// This method delete a connection in the table of the available connections and add a connection in the table of the connection used
	public Connection getConnection() {
		if (!pool.isEmpty()) {
			Connection tmp = pool.remove(pool.size() - 1);
			this.poolInUse.add(tmp);
			return tmp;
		} else {
			return null;
		}
	}

	public void close(Connection con) {
		if (con != null) {
			this.pool.add(con);
			boolean value = this.poolInUse.remove(con);
			if (!value) {
				System.out.println("erreur connection do not exist");
			} else {
				System.out.println("connection closed");
			}
		}
	}

	private void connectAll() {
		try {
			for (int i = 0; i < this.nbConnection; i++) {
				this.pool.add(connect());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
