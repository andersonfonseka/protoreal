package com.andersonfonseka.protoreal;

import com.andersonfonseka.Component;
import com.andersonfonseka.Renderer;
import com.andersonfonseka.RendererImpl;

public class CellRenderer extends RendererImpl implements Renderer {

	public CellRenderer(Component component) {
		super("cell.vm", component);
	}
	
}
