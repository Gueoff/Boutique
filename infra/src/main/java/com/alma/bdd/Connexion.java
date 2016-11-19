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


	private Connexion() {
		try {
			connect = DriverManager.getConnection(connection, user, pwd);
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

	public static void creationBDD() {
		// La connexion
		Connection conn = getInstance();
		Statement stmt = null;

		try {
			String gestion = "CREATE DATABASE boutique;";
			stmt = connect.createStatement();
			stmt.execute(gestion);
		} catch (Exception e) {
			System.out.println("Base de données déjà existante ! ");
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

	public static void insertionTables() {
		// La connexion
		Connection conn = getInstance();
		Statement stmt = null;

		try {

			String bdd = "USE boutique";
			String client = "CREATE table client(nom varchar(255), prenom varchar(255), age integer ,email varchar(255) PRIMARY KEY);";
			String article = "CREATE table article(id integer auto_increment PRIMARY KEY, nom varchar(255), description varchar(255), prix long, disponible boolean, id_type integer);";
			String type = "CREATE table typeArticle(id_type integer auto_increment PRIMARY KEY, nom_type varchar(255))";
			
			// execution des requêtes
			stmt = connect.createStatement();
			stmt.executeUpdate(bdd);
			System.out.println("Création des tables....");
			stmt.executeUpdate(client);
			stmt.executeUpdate(article);
			stmt.executeUpdate(type);
			System.out.println("Tables crées avec succès");

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
