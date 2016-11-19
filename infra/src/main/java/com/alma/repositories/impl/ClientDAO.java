package com.alma.repositories.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.alma.model.Client;


public class ClientDAO extends DAOImpl<Client>{

	public ClientDAO(Connection conn) {
		super(conn);
	}

	public Client create(Client obj) {
		try {
			PreparedStatement prepare = this.connect
					.prepareStatement("INSERT INTO client (nom, prenom, age, email) VALUES(?,?,?,?)");

			prepare.setString(1, obj.getNom());
			prepare.setString(2, obj.getPrenom());
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
		Client client = new Client();
		
		try {			
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY)
					.executeQuery(
							"SELECT * FROM client WHERE email ='" + id +"'");

			if (result.first()) {
				client = new Client(result.getString("nom"), result.getString("prenom"), result.getInt("age"), id);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return client;
	}

}
