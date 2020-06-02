package com.andersonfonseka.protoreal.components.render;

import com.andersonfonseka.protoreal.components.impl.Component;

public class ContainerRenderer extends RendererImpl implements Renderer {

	public ContainerRenderer(Component component) {
		super("container.vm", component);
	}
	
}
