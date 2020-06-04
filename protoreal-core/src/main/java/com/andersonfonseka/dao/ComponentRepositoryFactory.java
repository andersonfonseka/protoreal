package com.andersonfonseka.dao;

import com.andersonfonseka.IComponent;

public class ComponentRepositoryFactory {
	
	public static Repository<IComponent> getComponentRepository() {
		return new ComponentRepository();
	}

}
