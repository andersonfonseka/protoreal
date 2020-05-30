package com.andersonfonseka.dao.impl;

import java.util.List;

import com.andersonfonseka.protoreal.components.Component;

public interface Repository<T extends Component> {

	public T get(String uuid);
	
	public void add(T component);
	
	public void edit(T component);
	
	public void remove(T component);
	
	public List<T> list(String uuid);
	
}
