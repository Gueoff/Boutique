package com.alma.model;


public class Article {

	private int id;
	private String nom;
	private String description;
	private double prix;
	private boolean disponible;
	private TypeArticle type;
	
	
	
	public Article(int id, String nom, String description, double prix, TypeArticle type) {
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.prix = prix;
		this.type = type;
		this.disponible = true;
	}


	public Article(String nom, String description, double prix, TypeArticle type){
		this.nom = nom;
		this.description = description;
		this.prix = prix;
		this.type = type;
		this.disponible = true;
	}

	
	public Article(int id, String nom, String description, double prix, boolean disponible, TypeArticle type) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.prix = prix;
		this.disponible = disponible;
		this.type = type;
	}


	public Article() {
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}


	public TypeArticle getType() {
		return type;
	}

	public void setType(TypeArticle type) {
		this.type = type;
	}
		
	
}
