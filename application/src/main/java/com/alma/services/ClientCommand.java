package com.alma.services;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import org.json.JSONArray;
import org.json.JSONObject;

import com.alma.factories.DAOFactory;
import com.alma.factories.IDAOFactory;
import com.alma.factories.SupplierStub;
import com.alma.model.Article;
import com.alma.model.Cart;
import com.alma.model.Client;
import com.alma.model.IArticle;
import com.alma.model.ISupplier;
import com.alma.model.TypeArticle;
import com.alma.repositories.IDAO;

import parser.ParserJSON;

@WebService(endpointInterface = "com.alma.services.ClientCommand")
@SOAPBinding(style = Style.RPC)
public class ClientCommand implements IClientCommand{
	
	private ISupplier factory = new SupplierStub();
	private ParserJSON parser = new ParserJSON();

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

	//Mock sans BDD
	@Override
	@WebMethod(operationName="getTypesArticle")
	public String getTypesArticle() {
		List<TypeArticle> liste = new ArrayList<TypeArticle>();
		liste.add(TypeArticle.pull);
		liste.add(TypeArticle.jean);
		System.out.println(parser.parseTypesArticle(liste));
		return parser.parseTypesArticle(liste);					
	}


	@Override
	@WebMethod(operationName="getArticles", exclude = true)
	public String getArticles(String type) {		
		return null;
	}

}
