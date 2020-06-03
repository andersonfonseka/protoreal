package com.andersonfonseka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Page extends Component implements IPage {

	private String name;
	
	private String title;
	
	private String description;
	
	private boolean displayOnMenu = false;
	
	private boolean hideMenu = false;
	
	private boolean showTitle = true;
	
	private String containerType = "container"; 
	
	private String type;
	
	private Map<String, IComponent> fastComponents = new HashMap<String, IComponent>();
	
	public Page() {
		super();
	}
	
	public Page(String title) {
		super();
		this.title = title;
	}

	public Site getSite() {
		
		if (getParentComponent() instanceof Site) {
			return (Site) getParentComponent();
		}
		
		return (Site) ((Page) getParentComponent()).getParentComponent();
	}
	
	public List<IComponent> getPagesDisplayOnMenu(){
		return getChildrenList().stream().filter(x -> x instanceof Page && ((Page) x).isDisplayOnMenu()).collect(Collectors.toList());
	}
	
	public boolean getPageChildren(){
	
		boolean result = getChildrenList().stream().filter(x -> x instanceof Page).collect(Collectors.toList()).isEmpty();
		
		return result;
	}
	
	public String doRender() {
		return new PageRenderer(this).execute();
	}
	
	public void addFastComponent(IComponent component) {
		this.fastComponents.put(component.getUuid(), component);
	}

	public List<IComponent> getFastComponents() {
		return new ArrayList<IComponent>(fastComponents.values());
	}
	
	public IComponent getFastComponents(String uuid) {
		return fastComponents.get(uuid);
	}

	public String doEdit() {
		return null;
	}

	public String doPreview() {
		return new PageRenderer(this).executePreview();
	}
	
}
