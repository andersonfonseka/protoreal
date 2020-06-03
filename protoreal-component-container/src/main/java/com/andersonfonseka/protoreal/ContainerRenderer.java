package com.andersonfonseka.protoreal;

import com.andersonfonseka.Component;
import com.andersonfonseka.Renderer;
import com.andersonfonseka.RendererImpl;

public class ContainerRenderer extends RendererImpl implements Renderer {

	public ContainerRenderer(Component component) {
		super("container.vm", component);
	}
	
}
