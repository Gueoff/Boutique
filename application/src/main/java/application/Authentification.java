package application;

import com.alma.factories.DAOFactory;
import com.alma.factories.impl.DAOFactoryImpl;
import com.alma.model.Client;
import com.alma.repositories.DAO;

public class Authentification {

	public boolean login(String login, String password){
		DAOFactory factory = new DAOFactoryImpl();
		DAO<Client> clientDao =  factory.getClientDAO();
		Client client = clientDao.find(login);
		if(client.getName() == null){
			return false;
		}
		return true;
	}
	
	public void logup(Client client){
		DAOFactory factory = new DAOFactoryImpl();
		DAO<Client> clientDao =  factory.getClientDAO();
		clientDao.create(client);
	}
}
