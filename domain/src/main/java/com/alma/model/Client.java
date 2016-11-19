package com.alma.model;


public class Client {

	private String nom;
	private String prenom;
	private int age;
	private String email;
	private Panier panier;
	
	

	public Client(String nom, String prenom, int age, String email) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.email = email;
		this.panier = new Panier();
	}
	
	public Client() {
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Panier getPanier() {
		return panier;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}

	public void ajouterAuPanier(Article article){
		if(article.isDisponible()){
			this.panier.add(article);
			article.setDisponible(false);
		}
	}
	
	public void enleverDuPanier(Article article){
		if(this.panier.contains(article)){
			this.panier.remove(article);
			article.setDisponible(true);
		}
	}
}
