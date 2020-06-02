package com.andersonfonseka.dao.impl;

import java.util.List;

import com.andersonfonseka.protoreal.components.spec.IComponent;

public interface Repository<T extends IComponent> {

	public T get(String uuid);
	
	public void add(T component);
	
	public void edit(T component);
	
	public void remove(T component);

	public List<? extends IComponent> list(String uuid);
	
}
