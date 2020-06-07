package com.andersonfonseka;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Site extends Component {

	private String name;

	private String title;

	private String description;
	
	private String initialPage;

	public Site() {
		super();
	}

	public Site(String title) {
		super();
		this.title = title;
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
