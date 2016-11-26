package com.alma.services;

import java.util.Collection;

import com.alma.model.IArticle;

public interface ISupplierCommand {

	public void buy(IArticle article);
	
	public void buy(Collection<IArticle> articles);
}
