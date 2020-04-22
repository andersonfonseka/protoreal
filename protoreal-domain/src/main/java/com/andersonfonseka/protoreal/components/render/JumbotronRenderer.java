package com.andersonfonseka.protoreal.components.render;

public class JumbotronRenderer extends RendererImpl implements Renderer {

	public JumbotronRenderer(Object component) {
		super("jumbotron.vm", component);
	}

	@Override
	public String render() {
		return execute();
	}
	
}
