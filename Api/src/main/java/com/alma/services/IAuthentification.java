package com.alma.services;

/**
 * Authentification web service
 * 
 * @author Léo Cassiau, Geoffrey Desbrosses
 * @version 0.0.1
 */
public interface IAuthentification {

	/**
	 * Sign up of a new client
	 * 
	 * @param client the new client in JSON format
	 * @return true if the new client is in database, else false
	 */
	public boolean signup(String client);
	
	
	/**
	 * login of a client
	 * 
	 * @param email client's email
	 * @param password client's password
	 * @return the client in JSON format
	 */
	public String login(String email, String password);
	
}
