package com.alma.model;

/**
 * Cart of a client in the shop
 * 
 * @author Léo Cassiau, Geoffrey Desbrosses
 * @version 0.0.1
 */
public interface ICart extends Iterable<IArticle>{

	/**
	 * Add an article in the cart
	 * 
	 * @param a article added
	 * @return true if it works, false else
	 */
	public boolean add(IArticle a);

	/**
	 * Remove an article of the cart
	 * 
	 * @param a article removed
	 * @return true if it works, false else
	 */
	public boolean remove(IArticle a);

	/**
	 * Numbers of articles in the cart
	 * 
	 * @return numbers of articles
	 */
	public int size();

	/**
	 * Check if the cart contains an article
	 * 
	 * @param a article checked
	 * @return true if the article is in the carte, else false
	 */
	public boolean contains(IArticle a);
	
	/**
	 * delete all articles
	 */
	public void clear();
}
