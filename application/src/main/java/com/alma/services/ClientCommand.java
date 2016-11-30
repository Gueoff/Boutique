package com.alma.services;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.alma.bdd.ConnectionMySQL;
import com.alma.factories.DAOFactory;
import com.alma.factories.IDAOFactory;
import com.alma.model.Article;
import com.alma.model.Cart;
import com.alma.model.Client;
import com.alma.model.IArticle;
import com.alma.model.TypeArticle;
import com.alma.repositories.DAO;
import com.alma.repositories.IDAO;
import com.alma.stubs.SupplierStub;

import parser.ParserJSON;

@WebService(endpointInterface = "com.alma.services.ClientCommand")
@SOAPBinding(style = Style.RPC)
public class ClientCommand implements IClientCommand{
	
	private ParserJSON parser = new ParserJSON();
	private IDAOFactory daoFactory = new DAOFactory();
	private IDAO<Client> clientDao = daoFactory.createClientDAO();
	private IDAO<Article> articleDao = daoFactory.createArticleDAO();
	private IDAO<TypeArticle> typeDao = daoFactory.createTypeArticleDAO();

	@Override
	@WebMethod
	public void buy(String client) {
		/*
		Client clientImpl =(Client) client;
		Cart cartImpl = (Cart) cart;
		clientImpl.setCart(cartImpl);
		clientImpl.buy(); //A implementer en appelant service distant de CB
		
		IDAOFactory factoryDao = new DAOFactory();
		IDAO<Article> articleDao =  factoryDao.createArticleDAO();
		for(IArticle article : cartImpl){
			articleDao.delete((Article) article);
			cartImpl.remove(article);
		}*/
	}

	@WebMethod(operationName="addToCart")
	public boolean addToCart(@WebParam(name = "client") String client, @WebParam(name = "article") String article) {
		
		return parser.parseClient(client).addToCart(parser.parseArticle(article));
	}

	@Override
	@WebMethod(operationName="removeToCart")
	public boolean removeToCart(String client, String article) {
		return parser.parseClient(client).removeToCart(parser.parseArticle(article));
	}

	@Override
	@WebMethod(operationName="clearCart")
	public void clearCart(String client) {
		parser.parseClient(client).getCart().clear();
	}

	@Override
	@WebMethod(operationName="getTypesArticle")
	public String getTypesArticle() {
		
		//Mock sans BDD
		/*List<TypeArticle> list = new ArrayList<TypeArticle>();
		list.add(TypeArticle.pull);
		list.add(TypeArticle.jean);*/
		
		
		ConnectionMySQL.use();
		typeDao = daoFactory.createTypeArticleDAO();
		List<TypeArticle> list = typeDao.list("");
		return parser.parseTypesArticle(list);					
	}


	@Override
	@WebMethod(operationName="getArticles")
	public String getArticles(@WebParam(name = "type") String type) {
		ConnectionMySQL.use();
		articleDao = daoFactory.createArticleDAO();
		return parser.parseArticles(articleDao.list(TypeArticle.valueOf(type)));
	}
}
