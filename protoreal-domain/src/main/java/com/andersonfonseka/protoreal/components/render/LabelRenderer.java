package com.andersonfonseka.protoreal.components.render;

import com.andersonfonseka.protoreal.components.Component;

public class LabelRenderer extends RendererImpl implements Renderer {

	public LabelRenderer(Component component) {
		super("label.vm", component);
	}
	
}
