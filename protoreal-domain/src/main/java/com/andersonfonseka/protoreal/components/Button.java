package com.andersonfonseka.protoreal.components;

import java.util.ArrayList;
import java.util.List;

import com.andersonfonseka.protoreal.components.render.ButtonRenderer;

public class Button extends Input {
	
	private String cssClass;
	
	public static final String PRIMARY = "btn btn-primary";
	public static final String SECONDARY = "btn btn-secondary";
	public static final String SUCCESS = "btn btn-success";
	public static final String DANGER = "btn btn-danger";	
	public static final String WARNING = "btn btn-warning";
	public static final String INFO = "btn btn-info";
	public static final String LIGHT = "btn btn-light";
	public static final String DARK = "btn btn-dark";
	public static final String LINK = "btn btn-link";
	
	private List<Component> pages = new ArrayList<Component>();
	
	private String pageUuid;
	
	public Button() {
		super("Button");
		this.cssClass = PRIMARY;
	}
	
	public Button(String label, String cssClass) {
		super(label);
		this.cssClass = cssClass;
	}
	
	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
	
	public List<Component> getPages() {
		return pages;
	}

	public void setPages(List<Component> pages) {
		this.pages = pages;
	}

	public String getPageUuid() {
		return pageUuid;
	}

	public void setPageUuid(String pageUuid) {
		this.pageUuid = pageUuid;
	}

	@Override
	public String doRender() {
		return new ButtonRenderer(this).execute();
	}


	@Override
	public String doEdit() {
		return new ButtonRenderer(this).executeProperties();
	}


	@Override
	public String doPreview() {
		return new ButtonRenderer(this).executePreview();
	}
	
}
