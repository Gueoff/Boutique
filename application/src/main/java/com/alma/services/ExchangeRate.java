package com.alma.services;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.soap.*;

@WebService(endpointInterface = "com.alma.services.ExchangeRate")
public class ExchangeRate implements IExchangeRate {

	private static final String webServiceUrl = "http://currencyconverter.kowabunga.net/converter.asmx";
	
	
		
	@WebMethod(operationName="convert")
	@Override
	public float convert(@WebParam(name = "amount")float amount,@WebParam(name = "from") String from, @WebParam(name = "to") String to) {
		try {
			// Create SOAP Connection
	        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
	        SOAPConnection soapConnection = soapConnectionFactory.createConnection();
	
	        // Send SOAP Message to SOAP Server
	        SOAPMessage soapResponse = soapConnection.call(createSOAPRequestConversionAmount(amount, from, to), webServiceUrl);
	        soapConnection.close();
	        
	        return readSOAPResponseConversionAmount(soapResponse);
		} 
		catch(SOAPException e) {
			System.err.println("Impossible to use the service.");
			e.printStackTrace();
			return -1;
		}
	}
	
	@WebMethod(exclude = true)
    private SOAPMessage createSOAPRequestConversionAmount(float amount, String from, String to) throws SOAPException {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage(); 

        // Headers
        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", "http://tempuri.org/GetConversionAmount");
        
        // SOAP Body
        SOAPBody body = soapMessage.getSOAPPart().getEnvelope().getBody();
        SOAPElement getConversionAmountElement = body.addChildElement("GetConversionAmount", "", "http://tempuri.org/");
        SOAPElement currencyFromElement = getConversionAmountElement.addChildElement("CurrencyFrom");
        SOAPElement currencyToElement = getConversionAmountElement.addChildElement("CurrencyTo");
        SOAPElement rateDateElement = getConversionAmountElement.addChildElement("RateDate");
        SOAPElement amountElement = getConversionAmountElement.addChildElement("Amount");
        
        // Values
        currencyFromElement.setValue(from);
        currencyToElement.setValue(to);
        rateDateElement.setValue(this.getLastUpdateDate());
        amountElement.setValue(String.valueOf(amount));
        soapMessage.saveChanges();

        return soapMessage;
    }
    
	@WebMethod(exclude = true)
	private static float readSOAPResponseConversionAmount(SOAPMessage m) throws SOAPException {
		return Float.parseFloat(m.getSOAPBody().getChildNodes().item(0).getChildNodes().item(0).getChildNodes().item(0).getNodeValue());
	}
    
	@WebMethod
	public String getLastUpdateDate() {
		try {
			// Create SOAP Connection
	        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
	        SOAPConnection soapConnection = soapConnectionFactory.createConnection();
	
	        // Send SOAP Message to SOAP Server
	        SOAPMessage soapResponse = soapConnection.call(createSOAPRequestLastUpdateDate(), webServiceUrl);
	        soapConnection.close();
	        
	        return readSOAPResponseLastUpdateDate(soapResponse);
		} 
		catch(SOAPException e) {
			System.err.println("Impossible to use the service.");
			e.printStackTrace();
			return "2016-11-25T00:00:00";
		}
	}
	
	@WebMethod(exclude = true)
    private SOAPMessage createSOAPRequestLastUpdateDate() throws SOAPException {
    	 MessageFactory messageFactory = MessageFactory.newInstance();
         SOAPMessage soapMessage = messageFactory.createMessage();
         
         // Headers
         MimeHeaders headers = soapMessage.getMimeHeaders();
         headers.addHeader("SOAPAction", "http://tempuri.org/GetLastUpdateDate");
         
         // SOAP Body
         SOAPBody body = soapMessage.getSOAPPart().getEnvelope().getBody();
         body.addChildElement("GetLastUpdateDate", "", "http://tempuri.org/");
         soapMessage.saveChanges();

         return soapMessage;
    }
    
	@WebMethod(exclude = true)
    private String readSOAPResponseLastUpdateDate(SOAPMessage m) throws SOAPException {
		return m.getSOAPBody().getChildNodes().item(0).getChildNodes().item(0).getChildNodes().item(0).getNodeValue();
    }

}
