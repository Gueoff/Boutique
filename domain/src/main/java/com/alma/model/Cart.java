package com.alma.model;

import java.util.ArrayList;

/**
 * Cart of a client in the shop
 * 
 * @author Léo Cassiau, Geoffrey Desbrosses
 * @version 0.0.1
 */
public class Cart extends ArrayList<IArticle> implements ICart{

	private static final long serialVersionUID = 1L;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean add(IArticle e) {
		return super.add(e);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean remove(IArticle o) {
		return super.remove(o);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean contains(IArticle o) {
		Article article = (Article) o;
		for(IArticle ia : this){
			Article a = (Article) ia;
			if(a.getId() == article.getId()){
				return true;
			}
		}
		return false;
	}

	public boolean empty(){
		this.clear();
		return true;
	}
}
