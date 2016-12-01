package com.alma.factories;

import java.sql.Connection;

import com.alma.database.ConnectionDerby;
import com.alma.factories.IDAOFactory;
import com.alma.model.Article;
import com.alma.model.Client;
import com.alma.model.TypeArticle;
import com.alma.repositories.ArticleDAO;
import com.alma.repositories.ClientDAO;
import com.alma.repositories.IDAO;
import com.alma.repositories.TypeArticleDAO;

public class DAOFactory implements IDAOFactory{

	protected static final Connection conn = ConnectionDerby.getInstance();

	/**
	 * create a new instance of a client DAO
	 * 
	 * @return new client DAO
	 */
	@Override
	public IDAO<Client> createClientDAO() {
		return new ClientDAO(conn);
	}

	@Override
	public IDAO<Article> createArticleDAO() {
		return new ArticleDAO(conn);
	}

	@Override
	public IDAO<TypeArticle> createTypeArticleDAO() {
		return new TypeArticleDAO(conn);
	}

}
