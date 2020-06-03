package com.andersonfonseka.protoreal;

import com.andersonfonseka.Component;
import com.andersonfonseka.Renderer;
import com.andersonfonseka.RendererImpl;

public class SelectInputRenderer extends RendererImpl implements Renderer {

	public SelectInputRenderer(Component component) {
		super("selectinput.vm", component);
	}
	
}
