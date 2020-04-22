package com.andersonfonseka.protoreal.components.render;

public class NavbarRenderer extends RendererImpl implements Renderer {

	public NavbarRenderer(Object component) {
		super("navbar.vm", component);
	}

	@Override
	public String render() {
		return execute();
	}
	
}
