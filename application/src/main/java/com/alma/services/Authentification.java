package com.alma.services;

import com.alma.factories.DAOFactory;
import com.alma.factories.IDAOFactory;
import com.alma.model.Client;
import com.alma.repositories.IDAO;

import parser.ParserJSON;

import javax.jws.*;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import org.apache.log4j.Logger;

@WebService(endpointInterface = "com.alma.services.Authentification")
@SOAPBinding(style = Style.RPC)
public class Authentification implements IAuthentification{

	private static Logger logger = Logger.getLogger(Authentification.class.getName());
	private ParserJSON parser = new ParserJSON();
	private IDAOFactory daoFactory = new DAOFactory();
	private IDAO<Client> clientDao = daoFactory.createClientDAO();

	
	@Override
	@WebMethod(operationName="logup")
	public boolean logup(@WebParam(name = "client") String client) {
		clientDao.create(parser.parseClient(client));
		return true;
	}
	
	@Override
	@WebMethod(operationName="login")
	public String login(@WebParam(name = "email")String email, @WebParam(name = "password")String password) {
		Client client = clientDao.find(email);
		return parser.parseClient(client).toString();
	}

}
