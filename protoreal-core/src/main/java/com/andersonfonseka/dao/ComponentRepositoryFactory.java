package com.andersonfonseka.dao;

import com.andersonfonseka.IComponent;

public abstract class ComponentRepositoryFactory {
	
	public static Repository<IComponent> getComponentRepository() {
		return new ComponentRepository();
	}

}
