package com.andersonfonseka.protoreal.components.render;

public class SiteRenderer extends RendererImpl implements Renderer {

	public SiteRenderer(Object component) {
		super("site.vm", component);
	}

	@Override
	public String render() {
		return execute();
	}
	
}
