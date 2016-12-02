package com.alma.services;

/**
 * Payement by credit card service
 * 
 * @author Léo Cassiau, Geoffrey Desbrosses
 * @version 0.0.1
 */
public interface IBankCardPayement {

	/**
	 * Use a web service to pay check if a credit card is valid
	 * @param cardNumber credit card's number
	 * @param expDate expiration date of the credi card
	 * @return true if the credit card is avaible, else false
	 */
	public boolean check(String cardNumber, String expDate);
}
