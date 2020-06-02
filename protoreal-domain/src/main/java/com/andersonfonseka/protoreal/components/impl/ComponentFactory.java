package com.andersonfonseka.protoreal.components.impl;


import java.util.HashMap;
import java.util.Map;

import com.andersonfonseka.protoreal.components.Card;
import com.andersonfonseka.protoreal.components.Carousel;
import com.andersonfonseka.protoreal.components.ComponentAbsFactory;
import com.andersonfonseka.protoreal.components.Jumbotron;
import com.andersonfonseka.protoreal.components.Label;
import com.andersonfonseka.protoreal.components.SelectInput;
import com.andersonfonseka.protoreal.components.Table;
import com.andersonfonseka.protoreal.components.TextAreaInput;
import com.andersonfonseka.protoreal.components.TextInput;
import com.andersonfonseka.protoreal.components.spec.IComponent;

public class ComponentFactory extends ComponentAbsFactory {
	
	private Map<String, Class<? extends IComponent>> mapComponents;
	
	public ComponentFactory() {
		
		mapComponents = new HashMap<String, Class<? extends IComponent>>();
		
		mapComponents.put("container", Container.class);
		mapComponents.put("textInput", TextInput.class);
		mapComponents.put("textArea", TextAreaInput.class);
		mapComponents.put("button", Button.class);
		mapComponents.put("dataTable", Table.class);
		mapComponents.put("selectItem", SelectInput.class);
		mapComponents.put("label", Label.class);
		mapComponents.put("jumbotron", Jumbotron.class);
		mapComponents.put("cards", Card.class);
		mapComponents.put("carousel", Carousel.class);
		
	}
	
	public IComponent create(String comp) {
		
		IComponent component = null;
		
		try {
			component = mapComponents.get(comp).newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return component;
	}

}
