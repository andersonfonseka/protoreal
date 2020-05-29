package com.andersonfonseka.dwr.service;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.andersonfonseka.dao.impl.ComponentRepository;
import com.andersonfonseka.dao.impl.PageRepository;
import com.andersonfonseka.protoreal.components.Button;
import com.andersonfonseka.protoreal.components.Card;
import com.andersonfonseka.protoreal.components.Carousel;
import com.andersonfonseka.protoreal.components.Component;
import com.andersonfonseka.protoreal.components.Container;
import com.andersonfonseka.protoreal.components.Jumbotron;
import com.andersonfonseka.protoreal.components.Label;
import com.andersonfonseka.protoreal.components.Page;
import com.andersonfonseka.protoreal.components.SelectInput;
import com.andersonfonseka.protoreal.components.Site;
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
		mapComponents.put("label", Label.class);
		mapComponents.put("jumbotron", Jumbotron.class);
		mapComponents.put("cards", Card.class);
		mapComponents.put("carousel", Carousel.class);
		
	}
	
	public Map<String, String> create(String component, String parent, HttpSession session) throws InstantiationException, IllegalAccessException {
		
		ComponentRepository repository = new ComponentRepository();
		PageRepository pageRepository = PageRepository.getInstance();
		
		Site site = (Site) session.getAttribute("site");
		Page page = (Page) session.getAttribute("page");

		Component comp = repository.get(parent);
		
		Component component2 = mapComponents.get(component).newInstance();
		component2.setSiteUuid(site.getUuid());
		component2.setPageUuid(page.getUuid());
		
		if (null != comp) {
			component2.setParent(comp);
		} else {
			component2.setParent(page);
		}
		
		repository.add(component2);
		
		page = pageRepository.getFull(page.getUuid());
		
		Map<String, String> result = new HashMap<String, String>();
		
		result.put("data", page.doRender());
		result.put("components", getComponents(page));
		
		return result;
	}
	
	private String getComponents(Page page) {
		
		PageRepository pageRepository = PageRepository.getInstance();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div id=\"componentSelected\" class=\"form-group mx-sm-1 mb-1\">");
		sb.append("<select name=\"componentSelected\" style=\"width:200px;\" class=\"form-control-sm\" onchange=\"configure();\">");
		sb.append("<option value=\"0\">Selecione</option>");
	
		List<Component> components = pageRepository.listComponents(page.getUuid());
		
		for (Component comp: components) {
			if (!comp.isDeleted()) {
				sb.append("<option value=" + comp.getUuid() + ">" + comp.getName() + "</option>");	
			}
		}
		
		sb.append("</select>");
		sb.append("</div>");
		
		
		return sb.toString();
	}
	
	public Map<String, String> startEdit(String componentId, HttpSession session) throws InstantiationException, IllegalAccessException{
		
		Site site = (Site) session.getAttribute("site");
		Page page = (Page) session.getAttribute("page");
		
		ComponentRepository componentRepository = new ComponentRepository();
		
		Map<String, String> result = new HashMap<String, String>();
		
		Component component = componentRepository.get(componentId);
		
		if (null != component) {
			
			if (component instanceof Button) {
				Button btn = (Button) component;
				btn.setPages(site.getPages());
			}
			
			result.put("data", component.doEdit());
		}
		
		return result;
	}
	
	public Map<String, String> edit(Map<String, String> form, HttpSession session) throws Exception {
		
		PageRepository pageRepository = PageRepository.getInstance();
		
		Page page = (Page) session.getAttribute("page");
		
		String componentId = form.get("setUuid");
		
		ComponentRepository componentRepository = new ComponentRepository();
		
		Component component = componentRepository.get(componentId);
	
		Map<String, String> result = new HashMap<String, String>();
			
		Iterator<String> it = form.keySet().iterator();
		
		while(it.hasNext()) {
			
			String fieldName = it.next();
			Method method = component.getClass().getMethod(fieldName, String.class);

			method.invoke(component, form.get(fieldName));
		}
		
		if (component instanceof Button) {
			Button btn = (Button) component;
			btn.setPage(pageRepository.get(btn.getPageUuid()));
		
		} else if (component instanceof Container) {
			Container container = (Container) component;
			container.configure(container.getRows(), container.getColumns());
		}

		componentRepository.edit(component);
		
		page = pageRepository.getFull(page.getUuid());
		
		result.put("data", page.doRender());
		result.put("components", getComponents(page));

			
		return result;
	}
	
	public Map<String, String> remove(String componentId, HttpSession session) throws InstantiationException, IllegalAccessException{
		
		PageRepository pageRepository = PageRepository.getInstance();
		
		ComponentRepository componentRepository = new ComponentRepository();
		
		Component component = componentRepository.get(componentId);
		
		componentRepository.remove(component);
		
		Page page = (Page) session.getAttribute("page");
	
		Map<String, String> result = new HashMap<String, String>();
		
		if (null != page.getFastComponents(componentId)) {
			page.getFastComponents(componentId).setDeleted(true);
		}
		
		page = pageRepository.getFull(page.getUuid());
		
		result.put("data", page.doRender());
		result.put("components", getComponents(page));
		
		return result;
	}

}
