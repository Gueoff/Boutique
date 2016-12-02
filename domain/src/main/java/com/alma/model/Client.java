package com.alma.model;

/**
 * shop's client
 * 
 * @author Léo Cassiau, Geoffrey Desbrosses
 * @version 0.0.1
 */
public class Client implements IClient{

	/**
	 * client's lastname
	 */
	private String name;

	/**
	 * client's firstname
	 */
	private String firstname;

	/**
	 * client's age
	 */
	private int age;

	/**
	 * client's email
	 */
	private String email;

	/**
	 * client's password
	 */
	private String password;
	/**
	 * client's cart of the shop
	 */
	private ICart cart = new Cart();

	/**
	 * constructor
	 * 
	 * @param name client's lastname
	 * @param firstname client's firstname
	 * @param age client's age
	 * @param email client's email
	 */
	public Client(String name, String firstname, int age, String email, String password) {
		setName(name);
		setFirstname(firstname);
		setAge(age);
		setEmail(email);
		setPassword(password);
	}

	public Client() {
	}

	/**
	 * Add an article in the client's cart
	 * 
	 * @param article article added in the client's cart
	 */
	public boolean addToCart(IArticle article) {
		if (article.isAvailable()) {
			this.cart.add(article);
			return true;
		}
		return false;
	}

	/**
	 * Remove an article of the client's cart
	 * 
	 * @param article article removed of the client's cart
	 */
	public boolean removeToCart(IArticle article) {
		if (this.cart.contains(article)) {
			this.cart.remove(article);
			return true;
		}
		return false;
	}

	/**
	 * buy all elements in the cart
	 */
	public boolean buy(){
		for(IArticle a : cart) {
			a.setAvailable(false);
		}
		// TODO A PLUS DARGENT
		return true;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Cart getCart() {
		return (Cart) cart;
	}

	public void setCart(ICart cart) {
		this.cart = cart;
	}

}
