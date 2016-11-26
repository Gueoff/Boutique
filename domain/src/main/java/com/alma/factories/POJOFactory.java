package com.alma.factories;

import com.alma.model.Article;
import com.alma.model.Cart;
import com.alma.model.Client;
import com.alma.model.IArticle;
import com.alma.model.ICart;
import com.alma.model.IClient;
import com.alma.model.TypeArticle;

public interface POJOFactory {

	public Client getClient(IClient iclient);
	
	public Article getArticle(IArticle iarticle);

	public TypeArticle getTypeArticle();
	
	public Cart getCart(ICart icart);
	
}
