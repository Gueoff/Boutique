package com.alma.factories;

import com.alma.model.Article;
import com.alma.model.Client;
import com.alma.model.TypeArticle;
import com.alma.repositories.IDAO;

/**
 * Data Access Object Factory of the shop
 * 
 * @author Léo Cassiau, Geoffrey Desbrosses
 * @version 0.0.1
 */
public interface IDAOFactory {
	
	/**
	 * create a new instance of a client DAO
	 * @return new client DAO
	 */
	public IDAO<Client> createClientDAO();
	
	/**
	 * create a new instance of a article DAO
	 * @return new article DAO
	 */
	public IDAO<Article> createArticleDAO();

	/**
	 * create a new instance of a type article DAO
	 * @return new type article DAO
	 */
	public IDAO<TypeArticle> createTypeArticleDAO();
}
