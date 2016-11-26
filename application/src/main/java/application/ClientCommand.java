package application;

import com.alma.factories.DAOFactory;
import com.alma.factories.POJOFactory;
import com.alma.factories.impl.DAOFactoryImpl;
import com.alma.factories.impl.POJOFactoryImpl;
import com.alma.model.Article;
import com.alma.model.Cart;
import com.alma.model.Client;
import com.alma.model.IArticle;
import com.alma.model.ICart;
import com.alma.model.IClient;
import com.alma.repositories.DAO;
import com.alma.services.IClientCommand;

public class ClientCommand implements IClientCommand{
	
	private POJOFactory factory = new POJOFactoryImpl();

	@Override
	public void buy(IClient client, ICart cart) {
		Client clientImpl = factory.getClient(client);
		Cart cartImpl = factory.getCart(cart);
		clientImpl.setCart(cartImpl);
		clientImpl.buy(); //A implementer en appelant service distant de CB
		
		DAOFactory factoryDao = new DAOFactoryImpl();
		DAO<Article> articleDao =  factoryDao.getArticleDAO();
		for(Article article : cartImpl){
			articleDao.delete(article);
			cartImpl.remove(article);
		}
	}

	@Override
	public void addToCart(IClient client, IArticle article) {
		Client c = factory.getClient(client);
		Article a = factory.getArticle(article);
		c.addToCart(a);
	}

	@Override
	public void removeToCart(IClient client, IArticle article) {
		Client c = factory.getClient(client);
		Article a = factory.getArticle(article);
		c.removeToCart(a);
	}

	@Override
	public void clearCart(IClient client) {
		Client c = factory.getClient(client);
		c.getCart().clear();
	}

}
