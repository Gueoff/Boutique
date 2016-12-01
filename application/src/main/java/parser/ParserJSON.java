package parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.alma.model.Article;
import com.alma.model.Cart;
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
		json.put("password", client.getPassword());
		
		JSONArray cart = new JSONArray();
		Article article;
		
		for(IArticle iarticle : client.getCart()){
			article = (Article) iarticle;
			cart.put(parseArticle(article));	
		}
		json.put("cart", cart);		
		return json;
	}
	
	
	/**
	 * Crée un client a partie d'un objet JSON
	 * @param client le JSON
	 * @return un client correspondant au JSON
	 */
	public Client parseClient(String client){
		JSONObject json = new JSONObject(client);
		System.out.println(client);

		Client c = new Client();
		c.setName(json.get("name").toString());
		c.setFirstname(json.get("firstname").toString());
		c.setAge(Integer.parseInt(json.get("age").toString()));
		c.setEmail(json.get("email").toString());
		c.setPassword(json.get("password").toString());
		
		JSONArray cartJson = new JSONArray(json.get("cart").toString());
		Cart cart = new Cart();
		for(int i=0; i<cartJson.length(); i++){
			Article article = parseArticle(cartJson.get(i).toString());
			cart.add(article);
		}
		c.setCart(cart);
		return c;
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
	 * crée un objet article a partir d'un String de format JSON
	 * @param article le JSON
	 * @return le nouvel article.
	 */
	public Article parseArticle(String article){
		JSONObject json = new JSONObject(article);
		
		Article a = new Article();
		a.setId(Integer.parseInt(json.get("id").toString()));
		a.setName(json.get("name").toString());
		a.setDescription(json.get("description").toString());
		a.setPrice(Double.parseDouble(json.get("price").toString()));
		a.setAvailable(Boolean.parseBoolean(json.get("available").toString()));
		a.setType(TypeArticle.valueOf(json.get("type").toString()));
		
		return a;
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
	public String parseArticles(Collection<Article> articles){
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
	

	/**
	 * TODO Marche probablement pas
	 * Forme une liste d'objet JSON à partir d'une liste JSON
	 * @param list liste à lire
	 * @return liste d'objet JSON parcourable en java
	 */
	public Collection<String> parseCollection(String list) {
		Collection<String> liste = new ArrayList<String>();
		JSONArray test = new JSONObject(list).getJSONArray("");
		for(Object j : test){
			liste.add(j.toString());
		}
		return liste;
	}
}
