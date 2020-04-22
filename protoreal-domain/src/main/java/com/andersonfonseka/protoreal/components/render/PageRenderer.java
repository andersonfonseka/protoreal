package com.andersonfonseka.protoreal.components.render;

public class PageRenderer extends RendererImpl implements Renderer {

	public PageRenderer(Object component) {
		super("page.vm", component);
	}

	@Override
	public String render() {
		return execute();
	}
	
}
