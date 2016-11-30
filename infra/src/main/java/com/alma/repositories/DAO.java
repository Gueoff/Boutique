package com.alma.repositories;

import java.sql.Connection;

import com.alma.repositories.IDAO;

public abstract class DAO<T> implements IDAO<T>{
	
	protected Connection connect;
	
	public DAO(Connection conn){
		this.connect = conn;
	}
	
}
