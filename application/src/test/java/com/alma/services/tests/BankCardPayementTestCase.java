package com.alma.services.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.alma.services.BankCardPayement;

public class BankCardPayementTestCase {
	
	@Test
	public void testCheckValid() {
		assertTrue(new BankCardPayement().check("4000123456789009", "0620"));
	}
	
	@Test
	public void testCheckExpDate() {
		assertFalse(new BankCardPayement().check("4000123456789009", "1115"));
	}
	
	@Test
	public void testCheckNoValidCardType() {
		assertFalse(new BankCardPayement().check("0000123456789009", "0620"));
	}
	
	@Test
	public void testCheckNoValidLength() {
		assertFalse(new BankCardPayement().check("4000", "1115"));
	}
	
	@Test
	public void testCheckNoSum10() {
		assertFalse(new BankCardPayement().check("4000123456789001", "1115"));
	}
	
}
