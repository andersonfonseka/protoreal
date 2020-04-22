package com.andersonfonseka.protoreal.components.render;

public class NavLinkRenderer extends RendererImpl implements Renderer {

	public NavLinkRenderer(Object component) {
		super("navlink.vm", component);
	}

	@Override
	public String render() {
		return execute();
	}
	
}
