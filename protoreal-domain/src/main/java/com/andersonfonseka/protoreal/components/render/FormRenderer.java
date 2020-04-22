package com.andersonfonseka.protoreal.components.render;

public class FormRenderer extends RendererImpl implements Renderer {

	public FormRenderer(Object component) {
		super("form.vm", component);
	}

	@Override
	public String render() {
		return execute();
	}
	
}
