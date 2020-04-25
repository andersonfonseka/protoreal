package com.andersonfonseka.protoreal.components;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.andersonfonseka.protoreal.components.render.PageRenderer;

public class Page extends Component {

	private String name;
	
	private String title;
	
	private String description;
	
	private boolean initial = false;
	
	private boolean displayOnMenu = false;
	
	private List<Component> fastComponents = new ArrayList<Component>();
	
	public Page() {
		super();
	}
	
	public Site getSite() {
		return (Site) getParent();
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

	public boolean isInitial() {
		return initial;
	}

	public void setInitial(boolean initial) {
		this.initial = initial;
	}
	
	public boolean isDisplayOnMenu() {
		return displayOnMenu;
	}

	public void setDisplayOnMenu(boolean displayOnMenu) {
		this.displayOnMenu = displayOnMenu;
	}
	
	public List<Component> getPagesDisplayOnMenu(){
		return getChildrenList().stream().filter(x -> x instanceof Page && ((Page) x).isDisplayOnMenu()).collect(Collectors.toList());
	}

	@Override
	public String doRender() {
		return new PageRenderer(this).render();
	}
	
	public void addFastComponent(Component component) {
		this.fastComponents.add(component);
	}

	public List<Component> getFastComponents() {
		return fastComponents;
	}

	@Override
	public String doEdit() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
