package com.alma.model;

/**
 * Article in the shop
 * 
 * @author Léo Cassiau, Geoffrey Desbrosses
 * @version 0.0.1
 */
public class Article implements IArticle {

	/**
	 * article's ID
	 */
	private int id;

	/**
	 * article's name
	 */
	private String name;

	/**
	 * a description of the article
	 */
	private String description;

	/**
	 * article's price
	 */
	private double price;

	/**
	 * when a article is sold, he becomes unavaible
	 */
	private boolean available;

	/**
	 * type's article : jean, pull, etc...
	 */
	private TypeArticle type;

	/**
	 * article's constructor
	 * 
	 * @param name
	 *            article's name
	 * @param description
	 *            article's description
	 * @param price
	 *            article's price
	 * @param type
	 *            article's type
	 */
	public Article(String name, String description, double price, TypeArticle type) {
		setName(name);
		setDescription(description);
		setPrice(price);
		setAvailable(true);
		setType(type);
	}

	/**
	 * article's constructor
	 * 
	 * @param name
	 *            article's name
	 * @param description
	 *            article's description
	 * @param price
	 *            article's price
	 * @param type
	 *            article's type
	 * @param id
	 *            article's ID
	 */
	public Article(int id, String name, String description, double price, TypeArticle type) {
		this(name, description, price, type);
		setId(id);
	}

	/**
	 * article's constructor
	 * 
	 * @param name
	 *            article's name
	 * @param description
	 *            article's description
	 * @param price
	 *            article's price
	 * @param type
	 *            article's type
	 * @param id
	 *            article's ID
	 * @param available
	 *            true if a client can purchase the article, else false
	 */
	public Article(int id, String name, String description, double price, boolean available, TypeArticle type) {
		this(id, name, description, price, type);
		setAvailable(available);
	}
	
	/**
	 * check if the article is sold or not
	 * @return true if a client can purchase the article, false else
	 */
	@Override
	public boolean isAvailable() {
		return available;
	}
	
	/**
	 * set the attribute avaible
	 * @param available true if we can purchase the article, else false
	 */
	@Override
	public void setAvailable(boolean available) {
		this.available = available;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public TypeArticle getType() {
		return type;
	}

	public void setType(TypeArticle type) {
		this.type = type;
	}

}
