package com.alma.services;

import java.util.Collection;

import com.alma.model.IArticle;
 
public interface ISupplierCommand {

	/**
	 * Permet d'ajouter un article a la boutique en ligne
	 * @param article l'article a ajouter 
	 */
	public void buy(IArticle article);
	
	/**
	 * Permet d'ajouter une liste d'articles à la boutique en ligne
	 * @param articles la liste des articles 
	 */
	public void buy(Collection<IArticle> articles);
	
	public Collection<IArticle> getSupplierArticles();
}
