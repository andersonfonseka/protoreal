package com.andersonfonseka;

public abstract class ComponentAbsFactory {
	
	public static ComponentFactory getInstance() {
		return new ComponentFactory();
	}

	public abstract IComponent create(String comp);
	
}
