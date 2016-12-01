package com.alma.services;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

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

	@WebMethod(operationName="buy")
	@Override
	public boolean buy(@WebParam(name = "article")String article) {
		Article articleImpl = parser.parseArticle(article); 
		if(factory.buy(articleImpl)){
			IDAOFactory factoryDao = new DAOFactory();
			IDAO<Article> articleDao =  factoryDao.createArticleDAO();
			articleDao.create(articleImpl);
			return true;
		}
		return false;
	}


	@WebMethod(operationName="getSupplierArticles")
	@Override
	public String getSupplierArticles() {
		return parser.parseArticles(factory.getArticles());
	}

}
