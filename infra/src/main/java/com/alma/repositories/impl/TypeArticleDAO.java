package com.alma.repositories.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.alma.model.TypeArticle;

public class TypeArticleDAO  extends DAOImpl<TypeArticle>{

	public TypeArticleDAO(Connection conn) {
		super(conn);
	}

	public TypeArticle create(TypeArticle obj) {
		try {
			PreparedStatement prepare = this.connect
					.prepareStatement("INSERT INTO typeArticle (nom_type) VALUES(?)");
			
			prepare.setString(1, obj.name());
			prepare.executeUpdate();
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public boolean delete(TypeArticle obj) {
		return true;
	}

	@Override
	public boolean update(TypeArticle obj) {
		return false;
	}

	@Override
	public TypeArticle find(int id) {		
		TypeArticle type = null;
		
		try {		
			
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY)
					.executeQuery(
							"SELECT * FROM typeArticle WHERE id_type ='" + id +"'");

			if (result.first()) {
				type = TypeArticle.valueOf(result.getString("nom_type"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return type;
	}
	
	public TypeArticle find(String id) {		
		TypeArticle type = null;
		
		try {		
			
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY)
					.executeQuery(
							"SELECT * FROM typeArticle WHERE nom_type ='" + id +"'");

			if (result.first()) {
				type = TypeArticle.valueOf(result.getString("nom_type"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return type;
	}

}