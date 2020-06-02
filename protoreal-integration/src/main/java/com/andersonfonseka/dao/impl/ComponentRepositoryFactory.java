package com.andersonfonseka.dao.impl;

import com.andersonfonseka.protoreal.components.spec.IComponent;

public abstract class ComponentRepositoryFactory {
	
	public static Repository<IComponent> getComponentRepository() {
		return new ComponentRepository();
	}

}
