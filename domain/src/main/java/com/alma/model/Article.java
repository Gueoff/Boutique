package com.alma.model;


public class Article implements IArticle{

	private int id;
	private String name;
	private String description;
	private double price;
	private boolean available;
	private TypeArticle type;
	
	public Article(String name, String description, double price, TypeArticle type){
		setName(name);
		setDescription(description);
		setPrice(price);
		setAvailable(true);
		setType(type);
	}
	
	
	public Article(int id, String name, String description, double price, TypeArticle type) {	
		this(name, description, price, type);
		setId(id);
		
	}

	
	public Article(int id, String name, String description, double price, boolean available, TypeArticle type) {
		this(id, name, description, price, type);
		setAvailable(available);
	}


	public Article() {
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


	public boolean isAvailable() {
		return available;
	}


	public void setAvailable(boolean available) {
		this.available = available;
	}


	public TypeArticle getType() {
		return type;
	}


	public void setType(TypeArticle type) {
		this.type = type;
	}


		
	
}
