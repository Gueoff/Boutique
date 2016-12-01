package com.alma.database.tests;

import org.junit.BeforeClass;
import org.junit.AfterClass;
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
	
	@BeforeClass
	public static void resetDatabase() {
		ConnectionDerby.dropTables();
		ConnectionDerby.createTables();
	}
	
	@Test
	public void scenarioTest() {
		IDAOFactory factory = new DAOFactory();
		IDAO<Client> clientDao = factory.createClientDAO();
		IDAO<Article> articleDao = factory.createArticleDAO();
		IDAO<TypeArticle> typeArticleDao = factory.createTypeArticleDAO();
		
		ConnectionDerby.init();
		
		assertEquals("jean diesel", articleDao.find(1).getName());
		assertEquals("leoCassiau", clientDao.find("tutu@gmail.com").getName());
		assertEquals("[pull, jean, veste]" ,typeArticleDao.list(null).toString());
	}
	
	@AfterClass
	public static void deleteDatabase() {
		ConnectionDerby.dropTables();
	}
}

