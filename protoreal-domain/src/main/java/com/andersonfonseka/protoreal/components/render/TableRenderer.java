package com.andersonfonseka.protoreal.components.render;

public class TableRenderer extends RendererImpl implements Renderer {

	public TableRenderer(Object component) {
		super("table.vm", component);
	}

	@Override
	public String render() {
		return execute();
	}
	
}
