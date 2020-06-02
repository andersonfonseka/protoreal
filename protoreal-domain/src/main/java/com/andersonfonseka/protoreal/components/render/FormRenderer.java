package com.andersonfonseka.protoreal.components.render;

import com.andersonfonseka.protoreal.components.impl.Component;

public class FormRenderer extends RendererImpl implements Renderer {

	public FormRenderer(Component component) {
		super("form.vm", component);
	}
	
}
