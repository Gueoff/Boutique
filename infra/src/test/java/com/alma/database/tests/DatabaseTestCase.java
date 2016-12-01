package com.alma.database.tests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.alma.database.ConnectionDerby;
import com.alma.factories.DAOFactory;
import com.alma.factories.IDAOFactory;
import com.alma.model.Article;
import com.alma.model.Client;
import com.alma.model.TypeArticle;
import com.alma.repositories.IDAO;


public class DatabaseTestCase {
	
	@Before
	public void resetDatabase() {
		ConnectionDerby.dropTables();
		ConnectionDerby.createTables();
	}

	@Test
	public void scenarioTest() {
		IDAOFactory factory = new DAOFactory();
		IDAO<Client> clientDao = factory.createClientDAO();
		IDAO<Article> articleDao = factory.createArticleDAO();
		IDAO<TypeArticle> typeArticleDao = factory.createTypeArticleDAO();
		
		typeArticleDao.create(TypeArticle.pull);
		typeArticleDao.create(TypeArticle.jean);
		typeArticleDao.create(TypeArticle.veste);
		clientDao.create(new Client("geoffrey", "desbrosses", 22, "g@d.com","azerty"));
		clientDao.create(new Client("leoCassiau", "non", 60, "tutu@gmail.com","uiop"));
		articleDao.create(new Article("jean diesel", "un jean qu iresiste a tout", 39.99, TypeArticle.jean));
		articleDao.create(new Article("pull over vert", "un pull bien chaud", 78, TypeArticle.pull));
		articleDao.create(new Article("pull over bleu", "un pull tres fin", 23.8, TypeArticle.pull));
		articleDao.create(new Article("pull over rouge", "un pull bien chaud", 78, TypeArticle.pull));
		articleDao.create(new Article("pull over jaune", "un pull bien chaud", 78, TypeArticle.pull));
		articleDao.create(new Article("veste en cuir", "du cuir veritable", 109.99, TypeArticle.veste));
		
		assertEquals("jean diesel", articleDao.find(1).getName());
		assertEquals("leoCassiau", clientDao.find("tutu@gmail.com").getName());
	}
}

