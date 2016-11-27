package application;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

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

@WebService(endpointInterface = "application.ClientCommand")
@SOAPBinding(style = Style.RPC)
public class ClientCommand implements IClientCommand{
	
	private POJOFactory factory = new POJOFactoryImpl();

	@Override
	@WebMethod(exclude = true)
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

	@WebMethod
	public void addToCart(Client client, Article article) {
		Client c = factory.getClient(client);
		Article a = factory.getArticle(article);
		c.addToCart(a);
	}

	@Override
	@WebMethod(exclude = true)
	public void removeToCart(IClient client, IArticle article) {
		Client c = factory.getClient(client);
		Article a = factory.getArticle(article);
		c.removeToCart(a);
	}

	@Override
	@WebMethod(exclude = true)
	public void clearCart(IClient client) {
		Client c = factory.getClient(client);
		c.getCart().clear();
	}

	@Override
	@WebMethod(exclude = true)
	public void addToCart(IClient client, IArticle article) {
		this.addToCart(client, article);
	}

}
