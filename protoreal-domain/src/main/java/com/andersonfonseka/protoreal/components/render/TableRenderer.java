package com.andersonfonseka.protoreal.components.render;

import com.andersonfonseka.Component;
import com.andersonfonseka.Renderer;
import com.andersonfonseka.RendererImpl;

public class TableRenderer extends RendererImpl implements Renderer {

	public TableRenderer(Component component) {
		super("table.vm", component);
	}
	
}
