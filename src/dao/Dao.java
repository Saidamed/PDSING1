package dao;

import java.sql.Connection;

import pojo.Personne;
/**
 * @param <T>
 * this abstract class defines all the methods and parameters needed on DAO classes
 */
public abstract class Dao<T> {

protected Connection connect = null;
	
	public Dao(Connection conn){
		this.connect= conn;
	}
	/**
	 * This method adds a line on the table
	 * @param <T>
	 * @param obj
	 * @return boolean
	 */
	public abstract boolean create(T obj);

	
	public abstract boolean delete(T obj);

	
	public abstract boolean update(T obj);

	/**
	 * This method looks and find on the table an asked line
	 * @param id
	 * @return
	 */
	public abstract T find();
	
	public Personne find(String nom) {
		// TODO Auto-generated method stub
		return null;
	}
}
