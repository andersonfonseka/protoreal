package com.andersonfonseka.protoreal.components.render;

import com.andersonfonseka.protoreal.components.Component;

public class SiteRenderer extends RendererImpl implements Renderer {

	public SiteRenderer(Component component) {
		super("site.vm", component);
	}
	
}
