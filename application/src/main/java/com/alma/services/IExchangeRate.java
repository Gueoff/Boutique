package com.alma.services;

/**
 * currency's converter
 * currencies : AUD BGN BRL CAD CHF CNY CYP CZK DKK EEK EUR GBP HKD HRK HUF IDR ILS INR ISK JPY KRW LTL LVL MTL MXN MYR NOK NZD PHP PLN RON RUB SEK SGD SIT SKK THB TRY USD ZAR
 * 
 * @author Léo Cassiau, Geoffrey Desbrosses
 * @version 0.0.1
 */
public interface IExchangeRate {
	
	/**
	 * convert an amount in a currency in an other currency
	 * @param amount amount to convert
	 * @param from the initial currency
	 * @param to the new currency
	 * @return the amount converted
	 */
	public float convert(float amount, String from, String to);
}
