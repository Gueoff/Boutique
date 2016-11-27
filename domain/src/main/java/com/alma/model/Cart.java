package com.alma.model;

import java.util.ArrayList;

/**
 * Cart of a client in the shop
 * 
 * @author Léo Cassiau, Geoffrey Desbrosses
 * @version 0.0.1
 */
public class Cart extends ArrayList<IArticle> implements ICart{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Add an article in the cart
	 * @param a article added
	 * @return true if it works, false else
	 */
	@Override
	public boolean add(IArticle e) {
		return super.add(e);
	}

	/**
	 * Remove an article of the cart
	 * @param a article removed
	 * @return true if it works, false else
	 */
	@Override
	public boolean remove(IArticle o) {
		return super.remove(o);
	}
	
	/**
	 * Check if the cart contains an article
	 * 
	 * @param a article checked
	 * @return true if the article is in the carte, else false
	 */
	@Override
	public boolean contains(IArticle o) {
		return super.contains(o);
	}

}
