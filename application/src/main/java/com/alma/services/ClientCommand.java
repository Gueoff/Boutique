package com.alma.services;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import org.apache.log4j.Logger;

import com.alma.database.ConnectionDerby;
import com.alma.database.ConnectionMySQL;
import com.alma.factories.DAOFactory;
import com.alma.factories.IDAOFactory;
import com.alma.model.Article;
import com.alma.model.Client;
import com.alma.model.TypeArticle;
import com.alma.repositories.IDAO;

import parser.ParserJSON;

@WebService(endpointInterface = "com.alma.services.ClientCommand")
@SOAPBinding(style = Style.RPC)
public class ClientCommand implements IClientCommand{
	
	private static Logger logger = Logger.getLogger(ClientCommand.class.getName());
	private ParserJSON parser = new ParserJSON();
	private IDAOFactory daoFactory = new DAOFactory();
	private IDAO<Article> articleDao = daoFactory.createArticleDAO();
	private IDAO<TypeArticle> typeDao = daoFactory.createTypeArticleDAO();

	@Override
	@WebMethod(operationName="buy")
	public boolean buy(@WebParam(name = "client") String client) {
		Client c =parser.parseClient(client);
		logger.info(c.getName() + "buy the articles in this cart : " + c.getCart());
		return c.buy();
	}

	@WebMethod(operationName="addToCart")
	public boolean addToCart(@WebParam(name = "client") String client, @WebParam(name = "article") String article) {
		Client c = parser.parseClient(client);
		Article a = parser.parseArticle(article);
		logger.info(c.getName() + " add to this cart the article " + a.getName());
		return c.addToCart(a);
	}

	@Override
	@WebMethod(operationName="removeToCart")
	public boolean removeToCart(@WebParam(name = "client") String client, @WebParam(name = "article") String article) {
		Client c = parser.parseClient(client);
		Article a = parser.parseArticle(article);
		logger.info(c.getName() + " remove to this cart the article " + a.getName());
		return c.removeToCart(a);
	}

	@Override
	@WebMethod(operationName="clearCart")
	public boolean clearCart(@WebParam(name = "client") String client) {
		Client c = parser.parseClient(client);
		logger.info(c.getName() + " has cleared this cart");
		return c.getCart().empty();
	}

	@Override
	@WebMethod(operationName="getTypesArticle")
	public String getTypesArticle() {
		//ConnectionMySQL.use();
		//ConnectionDerby.createTables();
		//ConnectionDerby.init();
		logger.info("get types articles");
		typeDao = daoFactory.createTypeArticleDAO();
		List<TypeArticle> list = typeDao.list(null);
		return parser.parseTypesArticle(list);					
	}


	@Override
	@WebMethod(operationName="getArticles")
	public String getArticles(@WebParam(name = "type") String type) {
		//ConnectionMySQL.use();
		logger.info("get articles of type :" + TypeArticle.valueOf(type));
		articleDao = daoFactory.createArticleDAO();
		return parser.parseArticles(articleDao.list(TypeArticle.valueOf(type)));
	}
}
