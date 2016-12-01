package com.alma.services;
 
public interface ISupplierCommand {

	/**
	 * Permet d'ajouter un article a la boutique en ligne
	 * @param article l'article a ajouter 
	 */
	public boolean buy(String article);
	
	/**
	 * 
	 * @return
	 */
	public String getSupplierArticles();
}
