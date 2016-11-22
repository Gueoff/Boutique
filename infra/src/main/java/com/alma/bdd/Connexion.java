package com.alma.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Connexion {

	private static String driver = "com.mysql.jdbc.Driver";
	private static String connection = "jdbc:mysql://localhost";
	private static String user = "root";
	private static String pwd = "root";
	private static Connection connect = null;
	private static final String PORT = System.getProperty("mysql.port");
	
	
	
	private Connexion() {
		try {
			connect = DriverManager.getConnection(String.format("jdbc:mysql://localhost:%s/root?user=root&password=root", this.PORT));		
			//connect = DriverManager.getConnection(connection, user, pwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getInstance() {
		if (connect == null) {
			new Connexion();
		}
		return connect;
	}

	public static void create() {
		// La connexion
		Connection conn = getInstance();
		Statement stmt = null;

		try {
			String gestion = "CREATE DATABASE boutique;";
			stmt = connect.createStatement();
			stmt.execute(gestion);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void use(){
		Connection conn = getInstance();
		Statement stmt = null;
		
		try {
			String use = "USE boutique;";
			stmt = connect.createStatement();
			stmt.execute(use);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void drop(){
		Connection conn = getInstance();
		Statement stmt = null;
		
		try {
			String use = "DROP DATABASE boutique;";
			stmt = connect.createStatement();
			stmt.execute(use);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void createTable() {
		Connection conn = getInstance();
		Statement stmt = null;

		try {

			String bdd = "USE boutique";
			String client = "CREATE table client(name varchar(255), firstname varchar(255), age integer, email varchar(255) PRIMARY KEY);";
			String article = "CREATE table article(id integer auto_increment PRIMARY KEY, name varchar(255), description varchar(255), price long, available boolean, id_type integer);";
			String type = "CREATE table typeArticle(id_type integer auto_increment PRIMARY KEY, name_type varchar(255))";
			
			// execution des requêtes
			stmt = connect.createStatement();
			stmt.executeUpdate(bdd);
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
