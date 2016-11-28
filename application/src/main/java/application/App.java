package application;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.alma.bdd.Connexion;
import com.alma.factories.DAOFactory;
import com.alma.factories.IDAOFactory;
import com.alma.model.Article;
import com.alma.model.Client;
import com.alma.model.TypeArticle;
import com.alma.repositories.IDAO;

import parser.ParserJSON;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ParserJSON parser = new ParserJSON();
		
		
		List<TypeArticle> liste = new ArrayList<TypeArticle>();
		liste.add(TypeArticle.pull);
		liste.add(TypeArticle.jean);
		System.out.println(parser.parseTypesArticle(liste));
		

		IDAOFactory daoFactory = new DAOFactory();
		IDAO<Client> clientDao;
		IDAO<Article> articleDao;
		
		Connexion.use();
		articleDao = daoFactory.createArticleDAO();
		List<Article> list = articleDao.list(TypeArticle.valueOf("pull"));
		
		System.out.println(parser.parseArticles(list));
		
		Client c = new Client("geof", "desb", 22, "g@d.com");
		Article a = new Article(0, "salopette", "sda", 0, true, TypeArticle.pull);
		c.addToCart(a);

		
		parser.parseClient(c);
		
	}

}
