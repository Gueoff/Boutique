package com.alma.services;

/**
 * currency's converter
 * 
 * @author Léo Cassiau, Geoffrey Desbrosses
 * @version 0.0.1
 */
public interface IClientCommand {
		
	/**
	 * Methode servant a acheter un article sur la boutique
	 * @param client le client acheteur
	 */
	public void buy(String client);
	
	/**
	 * Ajouter un article au pannier.
	 * @param client le client voulant l'objet.
	 * @param article l'article a ajouter.
	 * @return vrai si ca a marchén false sinon.
	 */
	public boolean addToCart(String client, String article);
	
	/**
	 * Enlève un article du panier
	 * @param client le client possédant le pannier
	 * @param article l'article a enlever
	 * @return
	 */
	public boolean removeToCart(String client, String article);
	
	/**
	 * Vider le panier
	 * @param client le client possédant le panier
	 */
	public void clearCart(String client);
	
	/**
	 * 
	 * @return
	 */
	public String getTypesArticle();
	
	/**
	 * 
	 * @param type
	 * @return
	 */
	public String getArticles(String type);

}
