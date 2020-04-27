package com.andersonfonseka.dwr.service;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
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
		page.getChildComponent(page, parent);
		
		Component component2 = mapComponents.get(component).newInstance();
		
		if (null != page.getComponentAux()) {
			page.getComponentAux().addChild(component2);
		} else {
			page.addChild(component2);
		}
		
		page.addFastComponent(component2);
		
		Map<String, String> result = new HashMap<String, String>();
		
		result.put("data", page.doRender());
		result.put("components", getComponents(page));
		
		return result;
	}
	
	private String getComponents(Page page) {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div id=\"componentSelected\" class=\"form-group mx-sm-1 mb-2\">");
		sb.append("<select name=\"componentSelected\" style=\"width:200px;\" class=\"form-control\" onchange=\"configure();\">");
		sb.append("<option value=\"0\">Selecione</option>");
	
		
		for (Component comp: page.getFastComponents()) {
			sb.append("<option value=" + comp.getUuid() + ">" + comp.getName() + "</option>");
		}
		
		sb.append("</select>");
		sb.append("</div>");
		
		
		return sb.toString();
	}
	
	public Map<String, String> startEdit(String componentId, HttpSession session) throws InstantiationException, IllegalAccessException{
		
		Page page = (Page) session.getAttribute("page");
	
		Map<String, String> result = new HashMap<String, String>();
		
		if (null != page.getFastComponents(componentId)) {
			result.put("data", page.getFastComponents(componentId).doEdit());
		}
		
		return result;
	}
	
	public Map<String, String> edit(Map<String, String> form, HttpSession session) throws Exception {
		
		Page page = (Page) session.getAttribute("page");
		
		String componentId = form.get("setUuid");
	
		Map<String, String> result = new HashMap<String, String>();
		
		if (null != page.getFastComponents(componentId)) {
			
			Component component = page.getFastComponents(componentId);
			
			Iterator<String> it = form.keySet().iterator();
			
			while(it.hasNext()) {
				
				String fieldName = it.next();
				Method method = component.getClass().getMethod(fieldName, String.class);
				
				method.invoke(component, form.get(fieldName));
					
			}
			
			result.put("data", page.doRender());
		}
		

		
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
