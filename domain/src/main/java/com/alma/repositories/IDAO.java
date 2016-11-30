package com.alma.repositories;

import java.util.List;

/**
 * Data Access Object of the shop
 * 
 * @author Léo Cassiau, Geoffrey Desbrosses
 * @version 0.0.1
 * @param <T> class of the data object
 */
public interface IDAO<T> {

	/**
	 * create a new object in the database
	 * 
	 * @param obj object created
	 * @return obj
	 */
	public T create(T obj);
	
	/**
	 * delete an object in the database
	 * 
	 * @param obj object deleted
	 * @return true if the object is deleted, else false
	 */
	public boolean delete(T obj);
	
	/**
	 * update an object in the database
	 * 
	 * @param obj object updated
	 * @return true if the object is updated, else false
	 */
	public boolean update(T obj);
	
	/**
	 * find a object in the database by the id
	 * 
	 * @param id object's ID
	 * @return the object in the database if the ID exist, else false
	 */
	public T find(int id);
	
	/**
	 * find a object in the database by the id
	 * 
	 * @param id object's ID
	 * @return the object in the database if the ID exist, else false
	 */
	public T find(String id);

	/**
	 * list all instances of an object in the database depends of the args
	 * @param args nocomprendo
	 * @return the list in the database which correspond with the args
	 */
	public List<T> list(Object args);
}
