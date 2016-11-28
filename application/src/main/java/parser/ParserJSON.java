package parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.alma.model.Article;
import com.alma.model.Client;
import com.alma.model.IArticle;
import com.alma.model.TypeArticle;

public class ParserJSON {

	/**
	 * Forme un JSONObject a partir d'un client.
	 * @param client le client a transformer en JSONObject.
	 * @return Le client sous format JSON.
	 */
	public JSONObject parseClient(Client client){
		JSONObject json = new JSONObject();
		json.put("name", client.getName());
		json.put("firstname", client.getFirstname());
		json.put("age", client.getAge());
		json.put("email", client.getEmail());
		
		JSONArray cart = new JSONArray();
		Article article;
		
		for(IArticle iarticle : client.getCart()){
			article = (Article) iarticle;
			cart.put(parseArticle(article));	
		}
		json.put("cart", cart);

		
		System.out.println(json);
		
		return json;
	}
	
	
	/**
	 * Forme un JSONObject a partir d'un article.
	 * @param article l'article a transformer en JSONObject.
	 * @return L'article sous format JSON.
	 */
	public JSONObject parseArticle(Article article){
		JSONObject json = new JSONObject();
		json.put("id", article.getId());
		json.put("name", article.getName());
		json.put("description", article.getDescription());
		json.put("price", article.getPrice());
		json.put("available", article.isAvailable());
		json.put("type",article.getType().name());
				
		return json;
	}
	
	/**
	 * Forme un JSONObject a partir d'un type.
	 * @param type le type a transformer en JSONObject.
	 * @return Le type sous format JSON.
	 */
	public JSONObject parseTypeArticle(TypeArticle type){
		JSONObject json = new JSONObject();
		json.put("name", type.name());
		
		return json;
	}
	
	/**
	 * Permet de former une chaine de caractere en format JSON a partir d'une liste d'articles.
	 * @param articles la liste d'articles a parser.
	 * @return une chaine de caractere JSON.
	 */
	public String parseArticles(List<Article> articles){
		Collection<JSONObject> liste = new ArrayList<JSONObject>();
		for(Article article : articles){
			liste.add(parseArticle(article));		
		}
		return liste.toString();
	}
	
	/**
	 * Permet de former une chaine de caractere en format JSON a partir d'une liste de type d'articles.
	 * @param types la liste de types d'articles.
	 * @return une chaine de caractere JSON.
	 */
	public String parseTypesArticle(List<TypeArticle> types){
		Collection<JSONObject> liste = new ArrayList<JSONObject>();
		for(TypeArticle type : types){
			liste.add(parseTypeArticle(type));		
		}
		return liste.toString();
	}
}
