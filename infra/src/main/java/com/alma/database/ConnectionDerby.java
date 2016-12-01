package com.alma.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDerby {
	
	private static Connection connect = null;
	
	private ConnectionDerby()  {
		try {
			connect = DriverManager.getConnection("jdbc:derby:src\\main\\ressources\\database;create=true");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getInstance() {
		if (connect == null) {
			new ConnectionDerby();
		}
		return connect;
	}

	public static void dropTables(){
		getInstance();
		Statement stmt = null;
		
		try {
			String client = "DROP TABLE client";
			String article = "DROP TABLE article";
			String type = "DROP TABLE typeArticle";

			stmt = connect.createStatement();
			stmt.execute(client);
			stmt.execute(article);
			stmt.execute(type);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void createTables() {
		getInstance();
		Statement stmt = null;

		try {

			String client = "CREATE TABLE client (name varchar(255), firstname varchar(255), age INT, email varchar(255) PRIMARY KEY, password varchar(255))";
			String article = "CREATE table article(id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), name varchar(255), description varchar(255), price REAL, available boolean, id_type integer)";
			String type = "CREATE table typeArticle(id_type integer PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), name_type varchar(255))";
			
			// execution des requêtes
			stmt = connect.createStatement();
			stmt.executeUpdate(client);
			stmt.executeUpdate(article);
			stmt.executeUpdate(type);

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} 
			catch (SQLException se2) {
			}
		}

	}
}
