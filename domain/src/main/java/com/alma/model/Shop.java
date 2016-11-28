package com.alma.model;

import java.util.List;

public class Shop implements IShop{

	private List<Article> articles;
	private List<Client> clients;
	
	public Shop(List<Article> articles, List<Client> clients) {
		this(articles);
		this.clients = clients;
	}


	public Shop(List<Article> articles) {
		this.articles = articles;
	}
	

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}
	
	
}
