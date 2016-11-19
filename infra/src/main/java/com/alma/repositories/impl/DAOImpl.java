package com.alma.repositories.impl;

import java.sql.Connection;

import com.alma.repositories.DAO;

public class DAOImpl<T> implements DAO<T>{
	
	protected Connection connect = null;
	
	public DAOImpl(Connection conn){
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
	

	
	

	
}
