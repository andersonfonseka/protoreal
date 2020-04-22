package com.andersonfonseka.protoreal.components.render;

public class TextAreaInputRenderer extends RendererImpl implements Renderer {

	public TextAreaInputRenderer(Object component) {
		super("textareainput.vm", component);
	}

	@Override
	public String render() {
		return execute();
	}
	
}
