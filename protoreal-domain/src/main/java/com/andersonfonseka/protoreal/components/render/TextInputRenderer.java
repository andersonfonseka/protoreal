package com.andersonfonseka.protoreal.components.render;

public class TextInputRenderer extends RendererImpl implements Renderer {

	public TextInputRenderer(Object component) {
		super("textinput.vm", component);
	}

	@Override
	public String render() {
		return execute();
	}
	
}
