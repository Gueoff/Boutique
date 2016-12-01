package com.alma.services;

public interface IAuthentification {

	public boolean logup(String client);
	
	public String login(String email, String password);
	
}
