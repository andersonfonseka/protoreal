package com.andersonfonseka.protoreal.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.andersonfonseka.protoreal.components.render.PageRenderer;

public class Page extends Component {

	private String name;
	
	private String title;
	
	private String description;
	
	private boolean displayOnMenu = false;
	
	private boolean hideMenu = false;
	
	private String containerType = "container"; 
	
	private String type;
	
	private Map<String, Component> fastComponents = new HashMap<String, Component>();
	
	public Page() {
		super();
	}
	
	public Site getSite() {
		
		if (getParent() instanceof Site) {
			return (Site) getParent();
		}
		
		return (Site) ((Page) getParent()).getParent();
	}

	public Page(String title) {
		super();
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean isDisplayOnMenu() {
		return displayOnMenu;
	}

	public void setDisplayOnMenu(boolean displayOnMenu) {
		this.displayOnMenu = displayOnMenu;
	}
	
	public boolean isHideMenu() {
		return hideMenu;
	}

	public void setHideMenu(boolean hideMenu) {
		this.hideMenu = hideMenu;
	}

	public List<Component> getPagesDisplayOnMenu(){
		return getChildrenList().stream().filter(x -> x instanceof Page && ((Page) x).isDisplayOnMenu()).collect(Collectors.toList());
	}
	
	public boolean getPageChildren(){
	
		boolean result = getChildrenList().stream().filter(x -> x instanceof Page).collect(Collectors.toList()).isEmpty();
		
		return result;
	}
	
	public String getContainerType() {
		return containerType;
	}

	public void setContainerType(String containerType) {
		this.containerType = containerType;
	}

	@Override
	public String doRender() {
		return new PageRenderer(this).execute();
	}
	
	public void addFastComponent(Component component) {
		this.fastComponents.put(component.getUuid(), component);
	}

	public List<Component> getFastComponents() {
		return new ArrayList<Component>(fastComponents.values());
	}
	
	public Component getFastComponents(String uuid) {
		return fastComponents.get(uuid);
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String doEdit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String doPreview() {
		return new PageRenderer(this).executePreview();
	}
	
}
