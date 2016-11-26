package com.alma.model;


public class Client implements IClient{

	private String name;
	private String firstname;
	private int age;
	private String email;
	private Cart cart = new Cart();
	
	

	public Client(String name, String firstname, int age, String email) {
		super();
		setName(name);
		setFirstname(firstname);
		setAge(age);
		setEmail(email);
	}
	
	public Client() {
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
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

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public void addToCart(Article article){
		if(article.isAvailable()){
			this.cart.add(article);
		}
	}
	
	public void removeToCart(Article article){
		if(this.cart.contains(article)){
			this.cart.remove(article);
		}
	}
	
	
}
