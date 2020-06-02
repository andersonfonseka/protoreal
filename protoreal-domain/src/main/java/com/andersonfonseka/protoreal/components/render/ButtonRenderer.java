package com.andersonfonseka.protoreal.components.render;

import com.andersonfonseka.protoreal.components.impl.Component;

public class ButtonRenderer extends RendererImpl implements Renderer {

	public ButtonRenderer(Component component) {
		super("button.vm", component);
	}
	
}
