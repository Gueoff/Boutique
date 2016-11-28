package com.alma.services;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import org.json.JSONArray;
import org.json.JSONObject;

import com.alma.bdd.Connexion;
import com.alma.factories.DAOFactory;
import com.alma.factories.IDAOFactory;
import com.alma.factories.SupplierStub;
import com.alma.model.Article;
import com.alma.model.Cart;
import com.alma.model.Client;
import com.alma.model.IArticle;
import com.alma.model.ISupplier;
import com.alma.model.TypeArticle;
import com.alma.repositories.DAO;
import com.alma.repositories.IDAO;

import parser.ParserJSON;

@WebService(endpointInterface = "com.alma.services.ClientCommand")
@SOAPBinding(style = Style.RPC)
public class ClientCommand implements IClientCommand{
	
	private ISupplier factory = new SupplierStub();
	private ParserJSON parser = new ParserJSON();
	private IDAOFactory daoFactory = new DAOFactory();
	private IDAO<Client> clientDao = daoFactory.createClientDAO();
	private IDAO<Article> articleDao = daoFactory.createArticleDAO();
	private IDAO<TypeArticle> typeDao = daoFactory.createTypeArticleDAO();

	@Override
	@WebMethod(exclude = true)
	public void buy(Client client, Cart cart) {
		Client clientImpl =(Client) client;
		Cart cartImpl = (Cart) cart;
		clientImpl.setCart(cartImpl);
		clientImpl.buy(); //A implementer en appelant service distant de CB
		
		IDAOFactory factoryDao = new DAOFactory();
		IDAO<Article> articleDao =  factoryDao.createArticleDAO();
		for(IArticle article : cartImpl){
			articleDao.delete((Article) article);
			cartImpl.remove(article);
		}
	}

	@WebMethod(exclude = true)
	public void addToCart(Client client, Article article) {
		System.out.println("la deuxieme");
		System.out.println(client.getFirstname());
		Client c = (Client) client;
		Article a = (Article) article;
		c.addToCart(a);
	}

	@Override
	@WebMethod(exclude = true)
	public void removeToCart(Client client, Article article) {
		Client c = (Client) client;
		Article a = (Article) article;
		c.removeToCart(a);
	}

	@Override
	@WebMethod(exclude = true)
	public void clearCart(Client client) {
		Client c = (Client) client;
		c.getCart().clear();
	}

	@Override
	@WebMethod(operationName="getTypesArticle")
	public String getTypesArticle() {
		
		//Mock sans BDD
		/*List<TypeArticle> list = new ArrayList<TypeArticle>();
		list.add(TypeArticle.pull);
		list.add(TypeArticle.jean);*/
		
		
		Connexion.use();
		typeDao = daoFactory.createTypeArticleDAO();
		List<TypeArticle> list = typeDao.list("");
		System.out.println(parser.parseTypesArticle(list));
		return parser.parseTypesArticle(list);					
	}


	@Override
	@WebMethod(operationName="getArticles")
	public String getArticles(String type) {
		System.out.println(type);
		Connexion.use();
		articleDao = daoFactory.createArticleDAO();
		List<Article> list = articleDao.list(TypeArticle.valueOf(type));
		return parser.parseArticles(list);
	}
}
