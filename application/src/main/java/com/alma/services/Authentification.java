package com.alma.services;

import com.alma.factories.DAOFactory;
import com.alma.factories.IDAOFactory;
import com.alma.model.Client;
import com.alma.repositories.IDAO;

import javax.jws.*;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import org.apache.log4j.Logger;

@WebService(endpointInterface = "com.alma.services.Authentification")
@SOAPBinding(style = Style.RPC)
public class Authentification {

	private static Logger logger = Logger.getLogger(Authentification.class.getName());

	@WebMethod
	public boolean login(String login, String password){
		logger.info("Authentification, login : " + login);
		
		IDAOFactory factory = new DAOFactory();
		IDAO<Client> clientDao =  factory.createClientDAO();
		Client client = clientDao.find(login);
		if(client.getName() == null){
			return false;
		}
		return true;
	}
	
	@WebMethod
	public void logup(Client client){
		logger.info("Test2");

		IDAOFactory factory = new DAOFactory();
		IDAO<Client> clientDao =  factory.createClientDAO();
		clientDao.create(client);
	}
	
	@WebMethod(exclude = true)
	public static void main(String[] args) {
		Authentification a = new Authentification();
		a.login("toto", "mo2pass");
		//a.logup(null);
	}
}
