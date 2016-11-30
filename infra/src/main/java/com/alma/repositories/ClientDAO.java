package com.alma.repositories;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.alma.model.Client;


public class ClientDAO extends DAO<Client>{

	public ClientDAO(Connection conn) {
		super(conn);
	}

	public Client create(Client obj) {
		try {
			PreparedStatement prepare = this.connect
					.prepareStatement("INSERT INTO client (name, firstname, age, email) VALUES(?,?,?,?)");

			prepare.setString(1, obj.getName());
			prepare.setString(2, obj.getFirstname());
			prepare.setInt(3, obj.getAge());
			prepare.setString(4, obj.getEmail());

			prepare.executeUpdate();
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public boolean delete(Client obj) {
		try {
			PreparedStatement prepare = this.connect.prepareStatement("DELETE FROM client WHERE email=" + obj.getEmail());
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}

	@Override
	public boolean update(Client obj) {
		return false;
	}

	@Override
	public Client find(String id) {		
		Client client = null;
		
		try {			
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY)
					.executeQuery(
							"SELECT * FROM client WHERE email ='" + id +"'");

			if (result.first()) {
				client = new Client(result.getString("name"), result.getString("firstname"), result.getInt("age"), id);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return client;
	}

	@Override
	public Client find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> list(Object args) {
		// TODO Auto-generated method stub
		return null;
	}

}
