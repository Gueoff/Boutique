package com.alma.app;

import com.alma.bdd.Connexion;
import com.alma.factories.DAOFactory;
import com.alma.factories.impl.DAOFactoryImpl;
import com.alma.model.Article;
import com.alma.model.Client;
import com.alma.model.TypeArticle;
import com.alma.repositories.DAO;



public class App {

	public static void main(String[] args) {
		
		//Création des données
		//Connexion.drop();
		Connexion.create();
		Connexion.createTable();
		Connexion.use();
	
		DAOFactory factory = new DAOFactoryImpl();
		DAO<Client> clientDao =  factory.getClientDAO();
		DAO<Article> articleDao = factory.getArticleDAO();
		DAO<TypeArticle> typeArticleDao = factory.getTypeArticleDAO();
		


		clientDao.create(new Client("geof","desb",22,"g@d"));
		articleDao.create(new Article("pull over magueule","un pull bien chaud",39.99, TypeArticle.pull));
		
		Article a = articleDao.find(1);
		System.out.println(a.getName()+a.getType()+a.getId());
		
		Client x = clientDao.find("g@d");
		System.out.println(x.getName());
		 		
	}

}