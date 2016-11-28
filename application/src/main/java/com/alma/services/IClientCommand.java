package com.alma.services;

import com.alma.model.Article;
import com.alma.model.Cart;
import com.alma.model.Client;
import com.alma.model.Shop;

public interface IClientCommand {
		
	/**
	 * Methode servant a acheter un article sur la boutique
	 * @param client le client acheteur
	 * @param cart le panier contenant les articles a acheter
	 */
	public void buy(Client client, Cart cart);
	
	/**
	 * Ajouter un article au pannier
	 * @param client le client voulant l'objet
	 * @param article l'article a ajouter
	 */
	public void addToCart(Client client, Article article);
	
	/**
	 * Enlève un article du panier
	 * @param client le client possédant le pannier
	 * @param article l'article a enlever
	 */
	public void removeToCart(Client client, Article article);
	
	/**
	 * Vider le panier
	 * @param client le client possédant le panier
	 */
	public void clearCart(Client client);
	
	public Shop getShop();
	
	public String[] getTypesArticle();

}
