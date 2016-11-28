package com.alma.app;

import java.util.ArrayList;
import java.util.List;

import com.alma.bdd.Connexion;
import com.alma.factories.DAOFactory;
import com.alma.factories.IDAOFactory;
import com.alma.model.Article;
import com.alma.model.Client;
import com.alma.model.TypeArticle;
import com.alma.repositories.IDAO;

public class App {

	public static void main(String[] args) {

		// Création des données
		//Connexion.drop();
		//Connexion.create();
		//Connexion.createTable();
		Connexion.use();

		IDAOFactory factory = new DAOFactory();
		IDAO<Client> clientDao = factory.createClientDAO();
		IDAO<Article> articleDao = factory.createArticleDAO();
		IDAO<TypeArticle> typeArticleDao = factory.createTypeArticleDAO();
/*
		clientDao.create(new Client("geof", "desb", 22, "g@d"));
		articleDao.create(new Article("pull over magueule", "un pull bien chaud", 39.99, TypeArticle.pull));
		articleDao.create(new Article("pull over nop", "un pull bien chaud", 39.99, TypeArticle.pull));
		List<Article> l = articleDao.list(TypeArticle.pull);
		System.out.println(l);
		*/
		List x = typeArticleDao.list("");
		System.out.println(x);
		/*
		Article a = articleDao.find(1);
		System.out.println(a.getName() + a.getType() + a.getId());

		Client x = clientDao.find("g@d");
		System.out.println(x.getName());
	*/
	}

}
