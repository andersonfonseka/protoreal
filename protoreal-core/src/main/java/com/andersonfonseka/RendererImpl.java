package com.andersonfonseka;

public abstract class RendererImpl {

	private Engine engine;

	private Component component;

	public RendererImpl(String template, Component comp) {
		engine = new Engine(template);
		engine.putOnContext(this.getClass().getSimpleName(), comp);
		this.component = comp;
	}

	public String execute() {
		if (!this.component.isDeleted()) {
			return this.engine.execute(Engine.DESIGN);
		}
		return "";
	}

	public String executeProperties() {
		if (!this.component.isDeleted()) {
			return this.engine.execute(Engine.PROPERTIES);
		}
		return "";
	}

	public String executePreview() {
		if (!this.component.isDeleted()) {
			return this.engine.execute(Engine.PREVIEW);
		}
		return "";
	}
}
