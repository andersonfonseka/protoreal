package com.andersonfonseka.dao.impl;

public abstract class ComponentRepositoryFactory {
	
	@SuppressWarnings("rawtypes")
	public static Repository getComponentRepository() {
		return new ComponentRepository();
	}

}
