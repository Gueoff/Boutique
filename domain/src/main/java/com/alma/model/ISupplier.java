package com.alma.model;

import java.util.Collection;

/**
 * Supplier of the shop
 * 
 * @author Léo Cassiau, Geoffrey Desbrosses
 * @version 0.0.1
 */
public interface ISupplier {
	
	/**
	 * Buy an article of the supplier
	 * 
	 * @param iarticle supplier's article
	 * @return true if the article is available, else false
	 */
	public boolean buy(Article iarticle);
	
	/**
	 * get all available article of the supplier
	 * 
	 * @return article's list
	 */
	public Collection<Article> getArticles();
	
}
