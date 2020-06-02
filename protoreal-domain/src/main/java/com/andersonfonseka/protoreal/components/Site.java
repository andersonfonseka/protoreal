package com.andersonfonseka.protoreal.components;

import java.util.List;
import java.util.stream.Collectors;

import com.andersonfonseka.protoreal.components.impl.Component;
import com.andersonfonseka.protoreal.components.impl.Page;
import com.andersonfonseka.protoreal.components.render.SiteRenderer;
import com.andersonfonseka.protoreal.components.spec.IComponent;

public class Site extends Component {

	private String name;

	private String title;

	private String description;
	
	private String initialPage;

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
	
	public String getInitialPage() {
		return initialPage;
	}

	public void setInitialPage(String initialPage) {
		this.initialPage = initialPage;
	}

	public List<IComponent> getPages(){
		return getChildrenList().stream().filter(x -> x instanceof Page).collect(Collectors.toList());
	}
	
	public String getNavBar(boolean render) {
		
		Navbar navbar = new Navbar();
		navbar.setTitle(this.title);
		
		if (!render) {
			
			for(IComponent component : this.getChildrenList()) {
				if (component instanceof Page) {
					
					Page page = (Page) component;
					
					if (!page.isDisplayOnMenu()) {
						continue;
					}
					
					if (page.getPagesDisplayOnMenu().isEmpty() && page.getSiteUuid().equals(page.getParent())) {
						
						NavLink navLink = new NavLink(page.getTitle());
						navLink.setSiteUuid(page.getSiteUuid());
						navLink.setPageUuid(page.getUuid());
						navbar.addChild(navLink);
					
					} else if (!page.getPagesDisplayOnMenu().isEmpty()) {
						
						NavDropdown dropdown = new NavDropdown(page.getTitle());
						navbar.addChild(dropdown);

						for(IComponent componentPage : page.getPagesDisplayOnMenu()) {
						
							if (componentPage instanceof Page) {
								Page subPage = (Page) componentPage;
								
								if (!subPage.isDisplayOnMenu()) {
									continue;
								}
								
								NavLink link = new NavLink(subPage.getTitle());
								link.setSiteUuid(page.getSiteUuid());
								link.setPageUuid(subPage.getUuid());
								
								dropdown.addOptions(link);
							}
						}
					}
				}
			}
		}
		
		return navbar.doRender();
	}

	public String doRender() {
		return new SiteRenderer(this).execute();
	}

	public String doEdit() {
		return null;
	}

	public String doPreview() {
		return null;
	}

}
