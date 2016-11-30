package com.alma.stubs;

import java.util.ArrayList;
import java.util.Collection;

import com.alma.model.Article;
import com.alma.model.TypeArticle;

/**
 * Supplier stub use to simulate the supplier service
 * 
 * @author Léo Cassiau, Geoffrey Desbrosses
 * @version 0.0.1
 */
public class SupplierStub {

	public boolean buy(Article iarticle) {
		return true;
	}

	public Collection<Article> getArticles() {
		Collection<Article> articles = new ArrayList<Article>();
		articles.add(new Article("Pull en cashemir", "Un superbe pull hyper doux", 250, TypeArticle.pull));
		articles.add(new Article("Jean Levis", "Vient directement des USA", 119.99, TypeArticle.jean));
		return articles;
	}

}
