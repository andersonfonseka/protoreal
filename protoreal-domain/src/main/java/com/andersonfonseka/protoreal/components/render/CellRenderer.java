package com.andersonfonseka.protoreal.components.render;

import com.andersonfonseka.protoreal.components.impl.Component;

public class CellRenderer extends RendererImpl implements Renderer {

	public CellRenderer(Component component) {
		super("cell.vm", component);
	}
	
}
