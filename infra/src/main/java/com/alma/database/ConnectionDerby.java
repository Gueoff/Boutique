package com.alma.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.alma.factories.DAOFactory;
import com.alma.factories.IDAOFactory;
import com.alma.model.Article;
import com.alma.model.Client;
import com.alma.model.TypeArticle;
import com.alma.repositories.IDAO;

public class ConnectionDerby {
	
	private static Connection connect = null;
	
	private ConnectionDerby()  {
		try {
			connect = DriverManager.getConnection("jdbc:derby:src\\main\\resources\\database;create=true");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getInstance() {
		if (connect == null) {
			new ConnectionDerby();
		}
		return connect;
	}

	public static void dropTables(){
		getInstance();
		Statement stmt = null;
		
		try {
			String client = "DROP TABLE client";
			String article = "DROP TABLE article";
			String type = "DROP TABLE typeArticle";

			stmt = connect.createStatement();
			stmt.execute(client);
			stmt.execute(article);
			stmt.execute(type);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void createTables() {
		getInstance();
		Statement stmt = null;

		try {

			String client = "CREATE TABLE client (name varchar(255), firstname varchar(255), age INT, email varchar(255) PRIMARY KEY, password varchar(255))";
			String article = "CREATE table article(id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), name varchar(255), description varchar(255), price REAL, available boolean, id_type integer)";
			String type = "CREATE table typeArticle(id_type integer PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), name_type varchar(255))";
			
			// execution des requêtes
			stmt = connect.createStatement();
			stmt.executeUpdate(client);
			stmt.executeUpdate(article);
			stmt.executeUpdate(type);

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} 
			catch (SQLException se2) {
			}
		}

	}
	
	public static void init() {
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
	}
}
