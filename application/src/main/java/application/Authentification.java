package application;

import com.alma.factories.DAOFactory;
import com.alma.factories.impl.DAOFactoryImpl;
import com.alma.model.Client;
import com.alma.repositories.DAO;

import org.apache.log4j.Logger;

public class Authentification {

	private static Logger logger = Logger.getLogger(Authentification.class.getName());

	public boolean login(String login, String password){
		logger.info("Authentification, login : " + login);
		
		DAOFactory factory = new DAOFactoryImpl();
		DAO<Client> clientDao =  factory.getClientDAO();
		Client client = clientDao.find(login);
		if(client.getName() == null){
			return false;
		}
		return true;
	}
	
	public void logup(Client client){
		logger.info("Test2");

		DAOFactory factory = new DAOFactoryImpl();
		DAO<Client> clientDao =  factory.getClientDAO();
		clientDao.create(client);
	}
	
	public static void main(String[] args) {
		Authentification a = new Authentification();
		a.login("toto", "mo2pass");
		//a.logup(null);
	}
}