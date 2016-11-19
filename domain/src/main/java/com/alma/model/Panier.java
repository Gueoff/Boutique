package com.alma.model;

import java.util.ArrayList;
import java.util.List;

public class Panier {

	private List<Article> articles;
	
	public Panier(){
		this.articles = new ArrayList<Article>();
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public boolean add(Article e) {
		return articles.add(e);
	}

	public boolean remove(Object o) {
		return articles.remove(o);
	}

	public int size() {
		return articles.size();
	}

	public boolean contains(Object o) {
		return articles.contains(o);
	}
	
	
}
