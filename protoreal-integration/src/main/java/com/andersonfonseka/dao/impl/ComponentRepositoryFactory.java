package com.andersonfonseka.dao.impl;

public abstract class ComponentRepositoryFactory {
	
	public static Repository getComponentRepository() {
		return new ComponentRepository();
	}

}
