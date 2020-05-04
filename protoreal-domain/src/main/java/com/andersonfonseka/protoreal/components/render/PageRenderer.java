package com.andersonfonseka.protoreal.components.render;

import com.andersonfonseka.protoreal.components.Component;

public class PageRenderer extends RendererImpl implements Renderer {

	public PageRenderer(Component component) {
		super("page.vm", component);
	}
	
}
