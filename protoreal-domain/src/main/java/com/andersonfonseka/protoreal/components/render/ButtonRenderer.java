package com.andersonfonseka.protoreal.components.render;

public class ButtonRenderer extends RendererImpl implements Renderer {

	public ButtonRenderer(Object component) {
		super("button.vm", component);
	}

	@Override
	public String render() {
		return execute();
	}
	
}
