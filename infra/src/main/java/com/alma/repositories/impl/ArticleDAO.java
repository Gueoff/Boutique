package com.alma.repositories.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.alma.factories.DAOFactory;
import com.alma.factories.impl.DAOFactoryImpl;
import com.alma.model.Article;
import com.alma.model.TypeArticle;
import com.alma.repositories.DAO;

public class ArticleDAO extends DAOImpl<Article>{

	public ArticleDAO(Connection conn) {
		super(conn);
	}

	public Article create(Article obj) {
		try {
			
			DAOFactory factory = new DAOFactoryImpl();
			DAO<TypeArticle> typeArticleDao =  factory.getTypeArticleDAO();
			if(typeArticleDao.find(obj.getType().name()) == null){
				typeArticleDao.create(obj.getType());
			}
			
			PreparedStatement prepare = this.connect
					.prepareStatement("INSERT INTO article (nom, description, prix, disponible, id_type) VALUES(?,?,?,?, (SELECT id_type from typeArticle where typeArticle.nom_type = '"+ obj.getType().name()+"' )) ");
			
			
			prepare.setString(1, obj.getNom());
			prepare.setString(2, obj.getDescription());
			prepare.setDouble(3, obj.getPrix());
			prepare.setBoolean(4, obj.isDisponible());

			prepare.executeUpdate();
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public boolean delete(Article obj) {
		try {
			PreparedStatement prepare = this.connect.prepareStatement("DELETE FROM article WHERE id=" + obj.getId());
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}

	@Override
	public boolean update(Article obj) {
		return false;
	}

	@Override
	public Article find(int id) {		
		Article article = new Article();
		
		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY)
					.executeQuery(
							"SELECT * FROM article, typeArticle WHERE article.id_type = typeArticle.id_type AND id =" + id );

			if (result.first()) {
				TypeArticle type = TypeArticle.valueOf(result.getString("nom_type"));
				article = new Article(id, result.getString("nom"), result.getString("description"), result.getLong("prix"), result.getBoolean("disponible"), type);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return article;
	}


}
