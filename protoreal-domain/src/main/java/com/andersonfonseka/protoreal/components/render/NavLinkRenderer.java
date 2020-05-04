package com.andersonfonseka.protoreal.components.render;

import com.andersonfonseka.protoreal.components.Component;

public class NavLinkRenderer extends RendererImpl implements Renderer {

	public NavLinkRenderer(Component component) {
		super("navlink.vm", component);
	}
	
}
