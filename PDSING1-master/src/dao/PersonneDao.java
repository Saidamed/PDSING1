package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import personne.Personne;
import personne.Profile;

public class PersonneDao extends Dao<Personne> {

	private Connection con;

	public PersonneDao(Connection conn) {
		super(conn);
		this.con = conn;
	}

	public Connection getConnection() {
		return this.con;
	}

	@Override
	public boolean create(Personne obj) {
		try {
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeUpdate("INSERT INTO personne(nom) values (\'" + obj.getNom() + "\')");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Personne obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Personne obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Personne find(String nom) {
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM client Where nom='" + nom + "'");
			while (result.next()) {
				Personne p = new Personne(result.getString("nom"),result.getString("prenomClient"),result.getInt("idClient"));
				return p;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public ArrayList<Personne> findlike(String nom) {
		ArrayList<Personne> liste = new ArrayList<>();
		System.out.println("sql");
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM client Where nomClient like '%" + nom + "%'");
			while (result.next()) {
				System.out.println("client found");
				Personne p = new Personne(result.getString("nomClient"),result.getString("prenomClient"),result.getInt("idClient"));
				liste.add(p);
			}
			return liste;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public ArrayList<Profile> getProfilFromID(String ID){
		ArrayList<Profile> list = new ArrayList<>();
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT cp.idProfil,p.nomProfil FROM cli_prof cp JOIN profil p on p.idProfil = cp.idProfil  where cp.idClient ="+ID);
			while (result.next()) {
				Profile p = new Profile(result.getString("nomProfil"),result.getInt("idProfil"));
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public Personne find() {
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT nom FROM personne");
			while (result.next()) {
				Personne p = new Personne(result.getString("nom"),result.getString("prenomClient"),result.getInt("idClient"));
				return p;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}