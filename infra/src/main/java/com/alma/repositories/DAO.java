package com.alma.repositories;

import java.sql.Connection;
import java.util.List;

import com.alma.repositories.IDAO;

public class DAO<T> implements IDAO<T>{
	
	protected Connection connect = null;
	
	public DAO(Connection conn){
	this.connect = conn;
	}

	@Override
	public T create(T obj) {
		return null;
	}

	@Override
	public boolean delete(T obj) {
		return false;
	}

	@Override
	public boolean update(T obj) {
		return false;
	}

	@Override
	public T find(int id) {
		return null;
	}
	
	public T find(String id){
		return null;
	}

	@Override
	public List<T> list(Object args) {
		return null;
	}
	

	
	

	
}
