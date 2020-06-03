package com.andersonfonseka.protoreal.components.render;

import com.andersonfonseka.Component;
import com.andersonfonseka.Renderer;
import com.andersonfonseka.RendererImpl;

public class SeparatorRenderer extends RendererImpl implements Renderer {

	public SeparatorRenderer(Component component) {
		super("separator.vm", component);
	}
	
}
