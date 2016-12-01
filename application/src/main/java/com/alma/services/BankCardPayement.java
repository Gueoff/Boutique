package com.alma.services;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;

@WebService(endpointInterface = "com.alma.services.BankCardPayement")
public class BankCardPayement implements IBankCardPayement {

	private static final String webServiceUrl = "https://secure.ftipgw.com/ArgoFire/validate.asmx";
	private static Logger logger = Logger.getLogger(BankCardPayement.class.getName());
	
	@Override
	@WebMethod
	public boolean check(String cardNumber, String expDate) {
		logger.info("Check credit card");
		try {
			// Create SOAP Connection
	        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
	        SOAPConnection soapConnection = soapConnectionFactory.createConnection();
	
	        // Send SOAP Message to SOAP Server
	        SOAPMessage soapResponse = soapConnection.call(createSOAPRequestCheckCard(cardNumber, expDate), webServiceUrl);
	        soapConnection.close();
	        
	        return readSOAPResponseCheckCard(soapResponse);
		} 
		catch(SOAPException e) {
			System.err.println("Impossible to use the service.");
			e.printStackTrace();
			return false;
		}
	}
	
	@WebMethod(exclude = true)
    private SOAPMessage createSOAPRequestCheckCard(String cardNumber, String expDate) throws SOAPException {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage(); 

        // Headers
        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", "http://localhost/SmartPayments/ValidCard");
        
        // SOAP Body
        SOAPBody body = soapMessage.getSOAPPart().getEnvelope().getBody();
        SOAPElement validCardElement = body.addChildElement("ValidCard", "", "http://localhost/SmartPayments/");
        SOAPElement cardNumberElement = validCardElement.addChildElement("CardNumber");
        SOAPElement expDateElement = validCardElement.addChildElement("ExpDate");

        // Values
        cardNumberElement.setValue(cardNumber);
        expDateElement.setValue(expDate);
        soapMessage.saveChanges();

        return soapMessage;
    }
	
	@WebMethod(exclude = true)
	private boolean readSOAPResponseCheckCard(SOAPMessage m) throws SOAPException {
		return m.getSOAPBody().getChildNodes().item(0).getChildNodes().item(0).getChildNodes().item(0).getNodeValue().equals("0");
	}
	
}
