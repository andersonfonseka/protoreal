package com.andersonfonseka.protoreal.components.render;

public class NavDropdownRenderer extends RendererImpl implements Renderer {

	public NavDropdownRenderer(Object component) {
		super("navdropdown.vm", component);
	}

	@Override
	public String render() {
		return execute();
	}
	
}
