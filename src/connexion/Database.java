package connexion;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;


public class Database {
	private static final String driver_name = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/basedetest";
	private static final String user = "root";
	private static final String password = "root";
	private ArrayList<Connection> pool;
	private ArrayList<Connection> poolInUse;
	private int nbConnection = 0;

	public Database(int nbCo) {
		this.setNbConnection(nbCo);
		this.setPool(new ArrayList<Connection>());
		this.setPoolInUse(new ArrayList<Connection>());
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

	// This method delete a connection in the table of the available connections and
	// add a connection in the table of the connection used
	public Connection getConnection() {
		if (!getPool().isEmpty()) {
			Connection tmp = getPool().remove(getPool().size() - 1);
			this.getPoolInUse().add(tmp);
			return tmp;
		} else {
			return null;
		}
	}

	public void close(Connection con) {
		if (con != null) {
			this.getPool().add(con);
			boolean value = this.getPoolInUse().remove(con);
			if (!value) {
				System.out.println("erreur : la connexion n'existe pas");
			} else {
				System.out.println("connexion ferm�e , il reste "+this.getPool().size()+" connexion(s) disponible(s)");
				
			}
		}
	}

	private void connectAll() {
		try {
			for (int i = 0; i < this.getNbConnection(); i++) {
				this.getPool().add(connect());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getNbConnection() {
		return nbConnection;
	}

	public void setNbConnection(int nbConnection) {
		this.nbConnection = nbConnection;
	}

	public ArrayList<Connection> getPoolInUse() {
		return poolInUse;
	}

	public void setPoolInUse(ArrayList<Connection> poolInUse) {
		this.poolInUse = poolInUse;
	}

	public ArrayList<Connection> getPool() {
		return pool;
	}

	public void setPool(ArrayList<Connection> pool) {
		this.pool = pool;
	}

}
