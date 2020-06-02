package com.andersonfonseka.protoreal.components;

import com.andersonfonseka.protoreal.components.impl.ComponentFactory;
import com.andersonfonseka.protoreal.components.spec.IComponent;

public abstract class ComponentAbsFactory {
	
	public static ComponentFactory getInstance() {
		return new ComponentFactory();
	}

	public abstract IComponent create(String comp);
	
}
