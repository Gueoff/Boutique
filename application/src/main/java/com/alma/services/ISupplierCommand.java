package com.alma.services;

import java.util.Collection;

import com.alma.model.Article;
 
public interface ISupplierCommand {

	/**
	 * Permet d'ajouter un article a la boutique en ligne
	 * @param article l'article a ajouter 
	 */
	public void buy(Article article);
	
	/**
	 * Permet d'ajouter une liste d'articles à la boutique en ligne
	 * @param articles la liste des articles 
	 */
	public void buy(Collection<Article> articles);
	
	public Collection<Article> getSupplierArticles();
}
