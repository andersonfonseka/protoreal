package com.andersonfonseka.protoreal.components.render;

public class SelectInputRenderer extends RendererImpl implements Renderer {

	public SelectInputRenderer(Object component) {
		super("selectinput.vm", component);
	}

	@Override
	public String render() {
		return execute();
	}
	
}
