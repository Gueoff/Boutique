package com.alma.factories;

import java.util.ArrayList;
import java.util.Collection;

import com.alma.model.Article;
import com.alma.model.IArticle;
import com.alma.model.TypeArticle;
import com.alma.model.ISupplier;

/**
 * Supplier stub use to simulate the supplier service
 * 
 * @author Léo Cassiau, Geoffrey Desbrosses
 * @version 0.0.1
 */
public class SupplierStub implements ISupplier{

	@Override
	public boolean buy(IArticle iarticle) {
		return true;
	}

	@Override
	public Collection<IArticle> getArticles() {
		Collection<IArticle> articles = new ArrayList<IArticle>();
		articles.add(new Article("Pull en cashemir", "Un superbe pull hyper doux", 250, TypeArticle.pull));
		articles.add(new Article("Jean Levis", "Vient directement des USA", 119.99, TypeArticle.jean));
		return articles;
	}

}
