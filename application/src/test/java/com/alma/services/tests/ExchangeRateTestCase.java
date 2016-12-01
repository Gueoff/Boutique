package com.alma.services.tests;

import static org.junit.Assert.*;
import org.junit.Test;

import com.alma.services.ExchangeRate;

public class ExchangeRateTestCase {

	@Test
	public void testDollarsToEuros() {
		assertEquals(0.9409993, new ExchangeRate().convert(1, "USD", "EUR"), 0.0000001);
	}
	
}
