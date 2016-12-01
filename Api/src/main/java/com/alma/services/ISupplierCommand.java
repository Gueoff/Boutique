package com.alma.services;
 
public interface ISupplierCommand {

	/**
	 * Add an article to the shop
	 * @param article article added in JSON format
	 * @return true if the article is bought, else false 
	 */
	public boolean buy(String article);
	
	/**
	 * get a list of all articles of the supplier
	 * @return article's list in JSON format
	 */
	public String getSupplierArticles();
}
