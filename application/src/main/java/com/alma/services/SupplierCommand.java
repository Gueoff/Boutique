package com.alma.services;

import java.util.Collection;

import com.alma.factories.DAOFactory;
import com.alma.factories.IDAOFactory;
import com.alma.factories.SupplierStub;
import com.alma.model.Article;
import com.alma.model.ISupplier;
import com.alma.repositories.IDAO;

public class SupplierCommand implements ISupplierCommand{

	private ISupplier factory = new SupplierStub();
	
	@Override
	public void buy(Article article) {
		if(factory.buy(article)){
			Article articleImpl = (Article) article; 
			
			IDAOFactory factoryDao = new DAOFactory();
			IDAO<Article> articleDao =  factoryDao.createArticleDAO();
			articleDao.create(articleImpl);
		}
	}

	@Override
	public void buy(Collection<Article> articles) {
		for(Article article : articles){
			this.buy(article);
		}
	}

	@Override
	public Collection<Article> getSupplierArticles() {
		return factory.getArticles();
	}

}
