package com.alma.repositories;

public interface DAO<T> {
		
	public abstract T create(T obj);
	public abstract boolean delete(T obj);
	public abstract boolean update(T obj);
	public abstract T find(int id);
	public abstract T find(String string);

}
