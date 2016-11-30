package com.alma.services;
 
public interface ISupplierCommand {

	/**
	 * Permet d'ajouter un article a la boutique en ligne
	 * @param article l'article a ajouter 
	 */
	public void buy(String article);
	
	/**
	 * Permet d'ajouter une liste d'articles à la boutique en ligne
	 * @param articles la liste des articles 
	 */
	public void buyArticles(String articles);
	
	/**
	 * 
	 * @return
	 */
	public String getSupplierArticles();
}
