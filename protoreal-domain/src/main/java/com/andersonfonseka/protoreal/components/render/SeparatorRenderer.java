package com.andersonfonseka.protoreal.components.render;

public class SeparatorRenderer extends RendererImpl implements Renderer {

	public SeparatorRenderer(Object component) {
		super("separator.vm", component);
	}

	@Override
	public String render() {
		return execute();
	}
	
}
