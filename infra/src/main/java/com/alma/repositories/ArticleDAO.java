package com.alma.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.alma.factories.DAOFactory;
import com.alma.factories.IDAOFactory;
import com.alma.model.Article;
import com.alma.model.TypeArticle;
import com.alma.repositories.IDAO;

public class ArticleDAO extends DAO<Article>{

	public ArticleDAO(Connection conn) {
		super(conn);
	}

	public Article create(Article obj) {
		try {
			
			IDAOFactory factory = new DAOFactory();
			IDAO<TypeArticle> typeArticleDao =  factory.createTypeArticleDAO();
			if(typeArticleDao.find(obj.getType().name()) == null){
				typeArticleDao.create(obj.getType());
			}
			
			PreparedStatement prepare = this.connect.prepareStatement("INSERT INTO article (name, description, price, available, id_type) VALUES(?,?,?,?, (SELECT id_type from typeArticle where typeArticle.name_type = '"+ obj.getType().name()+"' )) ");
			prepare.setString(1, obj.getName());
			prepare.setString(2, obj.getDescription());
			prepare.setDouble(3, obj.getPrice());
			prepare.setBoolean(4, obj.isAvailable());

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
		try {
			PreparedStatement prepare = this.connect
					.prepareStatement("UPDATE article SET name='" + obj.getName() + "', description ='" + obj.getDescription() + "', price = " + obj.getPrice() + ", available = " + obj.isAvailable()+" WHERE id =" + obj.getId() );
			prepare.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;

	}

	@Override
	public Article find(int id) {		
		Article article = null;
		
		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY)
					.executeQuery(
							"SELECT * FROM article, typeArticle WHERE article.id_type = typeArticle.id_type AND id =" + id );

			if (result.first()) {
				TypeArticle type = TypeArticle.valueOf(result.getString("name_type"));
				article = new Article(id, result.getString("name"), result.getString("description"), result.getLong("price"), result.getBoolean("available"), type);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return article;
	}

	
	public List<Article> list(Object args){
		Article article = null;
		TypeArticle type = (TypeArticle) args;
		List<Article> articles = new ArrayList<Article>();
		
		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY)
					.executeQuery(
							"SELECT * FROM article, typeArticle WHERE article.id_type = typeArticle.id_type AND typeArticle.name_type ='" + type.name() +"'");

			while (result.next()) {
				article = new Article(result.getInt("id"), result.getString("name"), result.getString("description"), result.getLong("price"), result.getBoolean("available"), type);
				articles.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return articles;		
	}

	@Override
	public Article find(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
