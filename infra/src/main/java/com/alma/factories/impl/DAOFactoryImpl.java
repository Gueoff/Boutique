package com.alma.factories.impl;

import java.sql.Connection;

import com.alma.bdd.Connexion;
import com.alma.factories.DAOFactory;
import com.alma.model.Article;
import com.alma.model.Client;
import com.alma.model.TypeArticle;
import com.alma.repositories.DAO;
import com.alma.repositories.impl.ArticleDAO;
import com.alma.repositories.impl.ClientDAO;
import com.alma.repositories.impl.TypeArticleDAO;

public class DAOFactoryImpl implements DAOFactory{

	protected static final Connection conn = Connexion.getInstance();

	
	@Override
	public DAO<Client> getClientDAO() {
		return new ClientDAO(conn);
	}

	@Override
	public DAO<Article> getArticleDAO() {
		return new ArticleDAO(conn);
	}

	@Override
	public DAO<TypeArticle> getTypeArticleDAO() {
		return new TypeArticleDAO(conn);
	}

}
