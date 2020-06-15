package com.andersonfonseka;


import java.util.HashMap;
import java.util.Map;

public class ComponentFactory extends ComponentAbsFactory {
	
	private Map<String, String> mapComponents;
	
	public ComponentFactory() {
		
		mapComponents = new HashMap<String, String>();
		
		mapComponents.put("page", "com.andersonfonseka.Page");
		
		mapComponents.put("form", "com.andersonfonseka.protoreal.Form");
		mapComponents.put("container", "com.andersonfonseka.protoreal.Container");
		mapComponents.put("textInput", "com.andersonfonseka.protoreal.TextInput");
		mapComponents.put("textArea", "com.andersonfonseka.protoreal.TextAreaInput");
		mapComponents.put("button", "com.andersonfonseka.protoreal.Button");
		mapComponents.put("dataTable", "com.andersonfonseka.protoreal.Table");
		mapComponents.put("selectItem", "com.andersonfonseka.protoreal.SelectInput");
		mapComponents.put("label", "com.andersonfonseka.protoreal.Label");
		mapComponents.put("jumbotron", "com.andersonfonseka.protoreal.Jumbotron");
		mapComponents.put("cards", "com.andersonfonseka.protoreal.Card");
		mapComponents.put("carousel", "com.andersonfonseka.protoreal.Carousel");
		
	}
	
	public IComponent create(String comp) {
		
		IComponent component = null;
		
		try {
			component = (IComponent) Class.forName(mapComponents.get(comp)).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return component;
	}

}
