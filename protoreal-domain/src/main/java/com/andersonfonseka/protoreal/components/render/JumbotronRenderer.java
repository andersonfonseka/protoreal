package com.andersonfonseka.protoreal.components.render;

import com.andersonfonseka.protoreal.components.impl.Component;

public class JumbotronRenderer extends RendererImpl implements Renderer {

	public JumbotronRenderer(Component component) {
		super("jumbotron.vm", component);
	}
	
}
