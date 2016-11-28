package com.alma.services;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.alma.factories.DAOFactory;
import com.alma.factories.IDAOFactory;
import com.alma.factories.SupplierStub;
import com.alma.model.Article;
import com.alma.model.Cart;
import com.alma.model.Client;
import com.alma.model.IArticle;
import com.alma.model.ISupplier;
import com.alma.model.Shop;
import com.alma.model.TypeArticle;
import com.alma.repositories.IDAO;

@WebService(endpointInterface = "application.ClientCommand")
@SOAPBinding(style = Style.RPC)
public class ClientCommand implements IClientCommand{
	
	private ISupplier factory = new SupplierStub();

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
	@WebMethod(operationName="getTypesArticle", exclude = true)
	public String[] getTypesArticle() {
		String[] liste = new String[2];
		liste[0] = TypeArticle.jean.name();
		liste[1] = TypeArticle.pull.name();
		for(int i=0; i<liste.length; i++)
			System.out.println(liste[i]);
		return liste;
	}

	@Override
	@WebMethod(operationName="getShop")
	public Shop getShop() {
		Article x = new Article(0, "toto", "toto", 0, true, TypeArticle.pull);
		Article y = new Article(0, "titi", "titi", 0, true, TypeArticle.pull);
		
		List<Article> la = new ArrayList<Article>();
		la.add(x);
		la.add(y);
		Shop shop = new Shop(la);
		return shop;
	}

}
