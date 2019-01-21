package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import pojo.Personne;



public class PersonneDAO extends Dao<Personne>{
	
	private Connection con;
	
	//C'est le constructeur de PersonneDAO.
	 
	
	
	public PersonneDAO(Connection conn) {
		super(conn);
        this.con=conn;
	}
	
	public Connection getConnection(){
        return this.con;
    }

	@Override
	public boolean create(Personne obj) {
		   try{
	            this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeUpdate("INSERT INTO personne(nom) values (\'"+obj.getNom()+"\')");
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
		  try{
	            ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT nom FROM personne Where nom='"+nom+"'");
	            while(result.next()) {
	            	Personne p = new Personne(result.getString("nom"));
	                return p;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null;
	}

	@Override
	public Personne find () {
		 try{
	            ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT nom FROM personne");
	            while(result.next()) {
	            	Personne p = new Personne(result.getString("nom"));
	                return p;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null;
	}

}
