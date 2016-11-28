package com.alma.model;

/**
 * Article in the shop
 * 
 * @author Léo Cassiau, Geoffrey Desbrosses
 * @version 0.0.1
 */
public interface IArticle {

	/**
	 * check if the article is sold or not
	 * @return true if a client can purchase the article, false else
	 */
	public boolean isAvailable();
	
	/**
	 * set the attribute avaible
	 * @param available true if we can purchase the article, else false
	 */
	public void setAvailable(boolean available);
}
