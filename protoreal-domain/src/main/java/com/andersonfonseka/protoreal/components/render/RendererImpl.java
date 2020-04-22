package com.andersonfonseka.protoreal.components.render;

import com.andersonfonseka.protoreal.engine.Engine;

public abstract class RendererImpl {

	private Engine engine;
	
	public RendererImpl(String template, Object component) {
		engine = new Engine(template);
		engine.putOnContext(this.getClass().getSimpleName(), component);
	}

	public String execute() {
		return this.engine.execute();
	}
}
