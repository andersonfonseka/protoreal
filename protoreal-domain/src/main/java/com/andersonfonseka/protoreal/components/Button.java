package com.andersonfonseka.protoreal.components;

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

	@Override
	public String doRender() {
		return new ButtonRenderer(this).execute();
	}


	@Override
	public String doEdit() {
		return new ButtonRenderer(this).executeProperties();
	}
	
}
