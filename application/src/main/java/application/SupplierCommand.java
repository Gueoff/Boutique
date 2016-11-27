package application;

import java.util.Collection;

import com.alma.factories.DAOFactory;
import com.alma.factories.IDAOFactory;
import com.alma.factories.SupplierStub;
import com.alma.model.Article;
import com.alma.model.IArticle;
import com.alma.repositories.IDAO;
import com.alma.services.ISupplier;
import com.alma.services.ISupplierCommand;

public class SupplierCommand implements ISupplierCommand{

	private ISupplier factory = new SupplierStub();
	
	@Override
	public void buy(IArticle article) {
		if(factory.buy(article)){
			Article articleImpl = (Article) article; 
			
			IDAOFactory factoryDao = new DAOFactory();
			IDAO<Article> articleDao =  factoryDao.createArticleDAO();
			articleDao.create(articleImpl);
		}
	}

	@Override
	public void buy(Collection<IArticle> articles) {
		for(IArticle iarticle : articles){
			this.buy(iarticle);
		}
	}

	@Override
	public Collection<IArticle> getSupplierArticles() {
		return factory.getArticles();
	}

}
