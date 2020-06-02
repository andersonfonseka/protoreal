package com.andersonfonseka.protoreal.components.render;

import com.andersonfonseka.protoreal.components.impl.Component;

public class TableRenderer extends RendererImpl implements Renderer {

	public TableRenderer(Component component) {
		super("table.vm", component);
	}
	
}
