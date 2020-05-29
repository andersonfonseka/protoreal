package com.andersonfonseka.dao.impl;

import com.andersonfonseka.protoreal.components.Component;

public interface Repository<T extends Component> {

	public T get(String uuid);
	
	public void add(T component);
	
	public void edit(T component);
	
	public void remove(String uuid);
	
}