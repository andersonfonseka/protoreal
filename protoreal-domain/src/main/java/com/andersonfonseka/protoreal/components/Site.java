package com.andersonfonseka.protoreal.components;

import java.util.List;
import java.util.stream.Collectors;

import com.andersonfonseka.protoreal.components.render.SiteRenderer;

public class Site extends Component {

	private String name;

	private String title;

	private String description;

	public Site() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Site(String title) {
		super();
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<Component> getPages(){
		return getChildrenList().stream().filter(x -> x instanceof Page).collect(Collectors.toList());
	}
	
	public String getNavBar() {
		
		Navbar navbar = new Navbar();
		navbar.setTitle(this.title);
		
		for(Component component : this.getChildrenList()) {
			if (component instanceof Page) {
				
				Page page = (Page) component;
				
				if (!page.isDisplayOnMenu()) {
					continue;
				}
				
				if (page.getPagesDisplayOnMenu().isEmpty() && !(page.getParent() instanceof Page)) {
					
					NavLink navLink = new NavLink(page.getTitle());
					navbar.addChild(navLink);
				
				} else if (!page.getPagesDisplayOnMenu().isEmpty()) {
					
					NavDropdown dropdown = new NavDropdown(page.getTitle());
					navbar.addChild(dropdown);

					for(Component componentPage : page.getPagesDisplayOnMenu()) {
					
						if (componentPage instanceof Page) {
							Page subPage = (Page) componentPage;
							
							if (!subPage.isDisplayOnMenu()) {
								continue;
							}
							
							dropdown.addOptions(subPage.getTitle());
						}
					}
				}
			}
		}
		
		return navbar.doRender();
	}

	@Override
	public String doRender() {
		
		
		return new SiteRenderer(this).execute();
	}

}
