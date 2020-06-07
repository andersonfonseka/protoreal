package com.andersonfonseka.dao;

import java.util.List;

import com.andersonfonseka.IComponent;

public interface Repository<T extends IComponent> {

	public T get(String uuid);
	
	public void add(T component);
	
	public void edit(T component);
	
	public void remove(T component);

	public List<? extends IComponent> list(String uuid);
	
	public void setComponentRepository(Repository<IComponent> componentRepository);
	
	public void setMode(String pMode);
	
}
