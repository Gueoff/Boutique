package com.alma.services;

import com.alma.model.IArticle;
import com.alma.model.ICart;
import com.alma.model.IClient;

public interface IClientCommand {
		
	/**
	 * Methode servant a acheter un article sur la boutique
	 * @param client le client acheteur
	 * @param cart le panier contenant les articles a acheter
	 */
	public void buy(IClient client, ICart cart);
	
	/**
	 * Ajouter un article au pannier
	 * @param client le client voulant l'objet
	 * @param article l'article a ajouter
	 */
	public void addToCart(IClient client, IArticle article);
	
	/**
	 * Enlève un article du panier
	 * @param client le client possédant le pannier
	 * @param article l'article a enlever
	 */
	public void removeToCart(IClient client, IArticle article);
	
	/**
	 * Vider le panier
	 * @param client le client possédant le panier
	 */
	public void clearCart(IClient client);

}
