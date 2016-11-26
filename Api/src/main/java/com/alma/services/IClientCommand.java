package com.alma.services;

import com.alma.model.IArticle;
import com.alma.model.ICart;
import com.alma.model.IClient;

public interface IClientCommand {
		
	public void buy(IClient client, ICart cart);
	
	public void addToCart(IClient client, IArticle article);
	
	public void removeToCart(IClient client, IArticle article);
	
	public void clearCart(IClient client);

}
