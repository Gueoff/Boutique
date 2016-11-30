package com.alma.model;

/**
 * shop's client
 * 
 * @author Léo Cassiau, Geoffrey Desbrosses
 * @version 0.0.1
 */
public interface IClient {

	/**
	 * Add an article in the client's cart
	 * 
	 * @param article article added in the client's cart
	 * @return true si ca a marché, else false
	 */
	public boolean addToCart(IArticle article);
	
	/**
	 * Remove an article of the client's cart
	 * 
	 * @param article article removed of the client's cart
	 * @return true if the aricle is remove, else false
	 */
	public boolean removeToCart(IArticle article);
	
	/**
	 * buy all elements in the cart
	 */
	public boolean buy();
}
