package com.alma.factories.impl;

import com.alma.factories.POJOFactory;
import com.alma.model.Article;
import com.alma.model.Cart;
import com.alma.model.Client;
import com.alma.model.IArticle;
import com.alma.model.ICart;
import com.alma.model.IClient;
import com.alma.model.TypeArticle;

public class POJOFactoryImpl implements POJOFactory{

	@Override
	public Client getClient(IClient iclient) {
		Client client = (Client) iclient;
		return client;
	}

	@Override
	public Article getArticle(IArticle iarticle) {
		Article article = (Article) iarticle;
		return article;
	}

	@Override
	public TypeArticle getTypeArticle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart getCart(ICart icart) {
		Cart cart = (Cart) icart;
		return cart;
	}

	

}
