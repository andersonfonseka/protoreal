package com.andersonfonseka.protoreal.components.render;

import com.andersonfonseka.protoreal.components.Component;

public class SeparatorRenderer extends RendererImpl implements Renderer {

	public SeparatorRenderer(Component component) {
		super("separator.vm", component);
	}
	
}
