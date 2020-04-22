package com.andersonfonseka.dwr.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.andersonfonseka.protoreal.components.Button;
import com.andersonfonseka.protoreal.components.Component;
import com.andersonfonseka.protoreal.components.Container;
import com.andersonfonseka.protoreal.components.Page;
import com.andersonfonseka.protoreal.components.SelectInput;
import com.andersonfonseka.protoreal.components.Table;
import com.andersonfonseka.protoreal.components.TextAreaInput;
import com.andersonfonseka.protoreal.components.TextInput;

public class Controller {
	
	private Map<String, Class<? extends Component>> mapComponents;
	
	public Controller(){
		mapComponents = new HashMap<String, Class<? extends Component>>();
		
		mapComponents.put("container", Container.class);
		mapComponents.put("textInput", TextInput.class);
		mapComponents.put("textArea", TextAreaInput.class);
		mapComponents.put("button", Button.class);
		mapComponents.put("dataTable", Table.class);
		mapComponents.put("selectItem", SelectInput.class);
		
	}
	
	public Map<String, String> create(String component, String parent, HttpSession session) throws InstantiationException, IllegalAccessException{
		
		Page page = (Page) session.getAttribute("page");
	
		page.resetComponentAux();
		Component parentComponent = page.getChildComponent(page, parent);
		
		if (null != page.getComponentAux()) {
			page.getComponentAux().addChild(mapComponents.get(component).newInstance());
		} else {
			page.addChild(mapComponents.get(component).newInstance());
		}
		
		Map<String, String> result = new HashMap<String, String>();
		
		result.put("data", page.doRender());
		
		return result;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getMap(HttpSession session) {
		return (Map<String, Object>) session.getAttribute("mapTree");
	}
	
	public void setMap(HttpSession session, Map<String, Object> map) {
		session.setAttribute("mapTree", map);
	}


}
