package com.andersonfonseka.protoreal.components;

import com.andersonfonseka.protoreal.components.impl.Input;
import com.andersonfonseka.protoreal.components.render.NavLinkRenderer;

public class NavLink extends Input {
	
	private String siteUuid; 
	
	private String pageUuid; 
	
	public NavLink(String label) {
		super(label);
	}
	
	public String getPageUuid() {
		return "Projects.do?method=preview&siteId=" + this.siteUuid + "&pageId=" +  pageUuid;
	}

	public void setPageUuid(String pageUuid) {
		this.pageUuid = pageUuid;
	}
	
	public String getSiteUuid() {
		return siteUuid;
	}

	public void setSiteUuid(String siteUuid) {
		this.siteUuid = siteUuid;
	}

	public String doRender() {
		return new NavLinkRenderer(this).execute();
	}

	public String doEdit() {
		return null;
	}

	public String doPreview() {
		return null;
	}

	
}
