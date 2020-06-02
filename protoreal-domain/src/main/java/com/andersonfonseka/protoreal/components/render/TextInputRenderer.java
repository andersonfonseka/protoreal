package com.andersonfonseka.protoreal.components.render;

import com.andersonfonseka.protoreal.components.impl.Component;

public class TextInputRenderer extends RendererImpl implements Renderer {

	public TextInputRenderer(Component component) {
		super("textinput.vm", component);
	}
	
}
