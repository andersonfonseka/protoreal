package com.andersonfonseka.protoreal.components.render;

public class CellRenderer extends RendererImpl implements Renderer {

	public CellRenderer(Object component) {
		super("cell.vm", component);
	}

	@Override
	public String render() {
		return execute();
	}
	
}
