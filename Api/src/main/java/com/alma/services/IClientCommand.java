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
	 * @return vrai si le client a bien acheté les articles du panier, faux sinon
	 */
	public boolean buy(String client);
	
	/**
	 * Ajouter un article au pannier.
	 * @param client le client voulant l'objet.
	 * @param article l'article a ajouter.
	 * @return vrai si ca a marché faux sinon.
	 */
	public boolean addToCart(String client, String article);
	
	/**
	 * Enlève un article du panier
	 * @param client le client possédant le pannier
	 * @param article l'article a enlever
	 * @return vrai si l'article a bien été supprimé, faux sinon
	 */
	public boolean removeToCart(String client, String article);
	
	/**
	 * Vider le panier
	 * @param client le client possédant le panier
	 * @return vrai si le panier a bien été vidé, faux sinon
	 */
	public boolean clearCart(String client);
	
	/**
	 * Recupère tous les types d'articles de la boutique
	 * @return liste des articles en format JSON
	 */
	public String getTypesArticle();
	
	/**
	 * Recupère tous les articles d'un type donné
	 * @param type le type d'article à récupéré
	 * @return liste des articles en format JSON
	 */
	public String getArticles(String type);

}
