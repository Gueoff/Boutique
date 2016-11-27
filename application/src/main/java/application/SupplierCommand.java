package application;

import java.util.Collection;

import com.alma.factories.DAOFactory;
import com.alma.factories.POJOFactory;
import com.alma.factories.impl.DAOFactoryImpl;
import com.alma.factories.impl.POJOFactoryImpl;
import com.alma.model.Article;
import com.alma.model.IArticle;
import com.alma.repositories.DAO;
import com.alma.services.ISupplierCommand;

public class SupplierCommand implements ISupplierCommand{

	private POJOFactory factory = new POJOFactoryImpl();
	
	@Override
	public void buy(IArticle article) {
		Article articleImpl = factory.getArticle(article);
		
		DAOFactory factoryDao = new DAOFactoryImpl();
		DAO<Article> articleDao =  factoryDao.getArticleDAO();
		articleDao.create(articleImpl);		
	}

	@Override
	public void buy(Collection<IArticle> articles) {
		DAOFactory factoryDao = new DAOFactoryImpl();
		DAO<Article> articleDao =  factoryDao.getArticleDAO();
		
		for(IArticle iarticle : articles){
			Article articleImpl = factory.getArticle(iarticle);
			articleDao.create(articleImpl);
		}

	}

	@Override
	public Collection<IArticle> getSupplierArticles() {
		return factory.getArticles();
	}

}
