package com.andersonfonseka.protoreal.components.render;

public class ContainerRenderer extends RendererImpl implements Renderer {

	public ContainerRenderer(Object component) {
		super("container.vm", component);
	}

	@Override
	public String render() {
		return execute();
	}
	
}
