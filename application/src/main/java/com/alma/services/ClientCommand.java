package com.alma.services;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.alma.factories.DAOFactory;
import com.alma.factories.IDAOFactory;
import com.alma.model.Article;
import com.alma.model.TypeArticle;
import com.alma.repositories.IDAO;

import parser.ParserJSON;

@WebService(endpointInterface = "com.alma.services.ClientCommand")
@SOAPBinding(style = Style.RPC)
public class ClientCommand implements IClientCommand{
	
	private ParserJSON parser = new ParserJSON();
	private IDAOFactory daoFactory = new DAOFactory();
	//private IDAO<Client> clientDao = daoFactory.createClientDAO(); inscription && connexion
	private IDAO<Article> articleDao = daoFactory.createArticleDAO();
	private IDAO<TypeArticle> typeDao = daoFactory.createTypeArticleDAO();

	@Override
	@WebMethod
	public boolean buy(@WebParam(name = "client") String client) {
		return parser.parseClient(client).buy(); //A implementer en appelant service distant de CB
	}

	@WebMethod(operationName="addToCart")
	public boolean addToCart(@WebParam(name = "client") String client, @WebParam(name = "article") String article) {
		return parser.parseClient(client).addToCart(parser.parseArticle(article));
	}

	@Override
	@WebMethod(operationName="removeToCart")
	public boolean removeToCart(@WebParam(name = "client") String client, @WebParam(name = "article") String article) {
		return parser.parseClient(client).removeToCart(parser.parseArticle(article));
	}

	@Override
	@WebMethod(operationName="clearCart")
	public boolean clearCart(@WebParam(name = "client") String client) {
		return parser.parseClient(client).getCart().empty();
	}

	@Override
	@WebMethod(operationName="getTypesArticle")
	public String getTypesArticle() {
		typeDao = daoFactory.createTypeArticleDAO();
		List<TypeArticle> list = typeDao.list(null);
		return parser.parseTypesArticle(list);					
	}


	@Override
	@WebMethod(operationName="getArticles")
	public String getArticles(@WebParam(name = "type") String type) {
		articleDao = daoFactory.createArticleDAO();
		return parser.parseArticles(articleDao.list(TypeArticle.valueOf(type)));
	}
}
