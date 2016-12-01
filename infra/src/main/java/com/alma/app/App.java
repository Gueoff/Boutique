package com.alma.app;

import com.alma.bdd.ConnectionDerby;
import com.alma.bdd.ConnectionMySQL;
import com.alma.factories.DAOFactory;
import com.alma.factories.IDAOFactory;
import com.alma.model.Article;
import com.alma.model.Client;
import com.alma.model.TypeArticle;
import com.alma.repositories.IDAO;

public class App {

	public static void main(String[] args) {

		// Création de la base de données et des tables
		ConnectionMySQL.drop();
		ConnectionMySQL.create();
		ConnectionMySQL.createTables();
		ConnectionMySQL.use();
		//ConnectionDerby.createTables();
		
		IDAOFactory factory = new DAOFactory();
		IDAO<Client> clientDao = factory.createClientDAO();
		IDAO<Article> articleDao = factory.createArticleDAO();
		IDAO<TypeArticle> typeArticleDao = factory.createTypeArticleDAO();

		//Init de la demo
		typeArticleDao.create(TypeArticle.pull);
		typeArticleDao.create(TypeArticle.jean);
		typeArticleDao.create(TypeArticle.veste);
		clientDao.create(new Client("geoffrey", "desbrosses", 22, "g@d.com","azerty"));
		articleDao.create(new Article("jean diesel", "un jean qu iresiste a tout", 39.99, TypeArticle.jean));
		articleDao.create(new Article("pull over vert", "un pull bien chaud", 78, TypeArticle.pull));
		articleDao.create(new Article("pull over bleu", "un pull tres fin", 23.8, TypeArticle.pull));
		articleDao.create(new Article("pull over rouge", "un pull bien chaud", 78, TypeArticle.pull));
		articleDao.create(new Article("pull over jaune", "un pull bien chaud", 78, TypeArticle.pull));
		articleDao.create(new Article("veste en cuir", "du cuir veritable", 109.99, TypeArticle.veste));
		
	}

}
