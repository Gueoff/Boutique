package com.alma.factories;

import com.alma.model.Article;
import com.alma.model.Client;
import com.alma.model.TypeArticle;
import com.alma.repositories.DAO;

public interface DAOFactory {
	
	public DAO<Client> getClientDAO();
	
	public DAO<Article> getArticleDAO();

	public DAO<TypeArticle> getTypeArticleDAO();
}
