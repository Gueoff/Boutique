package com.alma.services;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import org.apache.log4j.Logger;

import com.alma.factories.DAOFactory;
import com.alma.factories.IDAOFactory;
import com.alma.model.Article;
import com.alma.repositories.IDAO;
import com.alma.stubs.SupplierStub;

import parser.ParserJSON;

@WebService(endpointInterface = "com.alma.services.SupplierCommand")
@SOAPBinding(style=Style.RPC)
public class SupplierCommand implements ISupplierCommand{

	private SupplierStub factory = new SupplierStub();
	private ParserJSON parser = new ParserJSON();
	private static Logger logger = Logger.getLogger(Authentification.class.getName());

	@WebMethod(operationName="buy")
	@Override
	public boolean buy(@WebParam(name = "article")String article) {
		Article a = parser.parseArticle(article);
		logger.info("buy the article "+ a.getName() +"of the supllier");
		if(factory.buy(a)){
			IDAOFactory factoryDao = new DAOFactory();
			IDAO<Article> articleDao =  factoryDao.createArticleDAO();
			articleDao.create(a);
			return true;
		}
		return false;
	}


	@WebMethod(operationName="getSupplierArticles")
	@Override
	public String getSupplierArticles() {
		logger.info("get the articles of the supllier");
		return parser.parseArticles(factory.getArticles());
	}

}
