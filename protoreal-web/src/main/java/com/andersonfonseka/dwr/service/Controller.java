package com.andersonfonseka.dwr.service;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.andersonfonseka.ComponentAbsFactory;
import com.andersonfonseka.IComponent;
import com.andersonfonseka.IPage;
import com.andersonfonseka.Site;
import com.andersonfonseka.dao.ComponentRepositoryFactory;
import com.andersonfonseka.dao.PageRepository;
import com.andersonfonseka.dao.Repository;

public class Controller {
	
	private Repository<IComponent> componentRepository = ComponentRepositoryFactory.getComponentRepository();
	
	private ComponentAbsFactory absFactory;
	
	public Controller(){
		absFactory = ComponentAbsFactory.getInstance();
	}
	
	public Map<String, String> create(String component, String parent, HttpSession session) throws InstantiationException, IllegalAccessException {
		
		PageRepository pageRepository = new PageRepository();
		
		Site site = (Site) session.getAttribute("site");
		IPage page = (IPage) session.getAttribute("page");

		IComponent comp = componentRepository.get(parent);
		
		IComponent component2 = absFactory.create(component);
		
		component2.setSiteUuid(site.getUuid());
		component2.setPageUuid(page.getUuid());
		
		if (null != comp) {
			component2.setParentComponent(comp);
			component2.setParent(comp.getUuid());
		} else {
			component2.setParentComponent(page);
			component2.setParent(page.getUuid());
		}
		
		componentRepository.add(component2);
		
		page = pageRepository.getFull(page.getUuid());
		
		Map<String, String> result = new HashMap<String, String>();
		
		result.put("data", page.doRender());
		result.put("components", getComponents(page));
		
		return result;
	}
	
	public String getComponents(IPage page) {
		
		PageRepository pageRepository =  new PageRepository();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div id=\"componentSelected\" class=\"form-group mx-sm-1 mb-1\">");
		sb.append("<select name=\"componentSelected\" style=\"width:200px;\" class=\"form-control-sm\" onchange=\"configure();\">");
		sb.append("<option value=\"0\">Selecione</option>");
	
		List<IComponent> components = pageRepository.listComponents(page.getUuid());
		
		for (IComponent comp: components) {
			if (!comp.isDeleted()) {
				sb.append("<option value=" + comp.getUuid() + ">" + comp.getSimpleName() + "</option>");	
			}
		}
		
		sb.append("</select>");
		sb.append("</div>");
		
		
		return sb.toString();
	}
	
	public Map<String, String> startEdit(String componentId, HttpSession session) throws InstantiationException, IllegalAccessException{
		
		Map<String, String> result = new HashMap<String, String>();
		
		IComponent component = componentRepository.get(componentId);
		
		if (null != component) {
			result.put("data", component.doEdit());
		}
		
		return result;
	}
	
	public Map<String, String> edit(Map<String, String> form, HttpSession session) throws Exception {
		
		PageRepository pageRepository =  new PageRepository();
		
		IPage page = (IPage) session.getAttribute("page");
		
		String componentId = form.get("setUuid");
		
		IComponent component = componentRepository.get(componentId);
	
		Map<String, String> result = new HashMap<String, String>();
			
		Iterator<String> it = form.keySet().iterator();
		
		while(it.hasNext()) {
			
			String fieldName = it.next();
			Method method = component.getClass().getMethod(fieldName, String.class);

			method.invoke(component, form.get(fieldName));
		}

		componentRepository.edit(component);
		
		page = pageRepository.getFull(page.getUuid());
		
		result.put("data", page.doRender());
		result.put("components", getComponents(page));

			
		return result;
	}
	
	public Map<String, String> remove(String componentId, HttpSession session) throws InstantiationException, IllegalAccessException{
		
		PageRepository pageRepository =  new PageRepository();
		
		IComponent component = componentRepository.get(componentId);
		
		componentRepository.remove(component);
		
		IPage page = (IPage) session.getAttribute("page");
	
		Map<String, String> result = new HashMap<String, String>();
		
		if (null != page.getFastComponents(componentId)) {
			page.getFastComponents(componentId).setDeleted(true);
		}
		
		page = pageRepository.getFull(page.getUuid());
		
		result.put("data", page.doRender());
		result.put("components", getComponents(page));
		
		return result;
	}
	
	public Map<String, String> submitForm(Map<String, String> form, HttpSession session) throws InstantiationException, IllegalAccessException{
		
		PageRepository pageRepository =  new PageRepository();
		
		IComponent component = componentRepository.get(form.get("setUuid"));
		
		Map<String, String> result = new HashMap<String, String>();
		
		return result;
	}

}
