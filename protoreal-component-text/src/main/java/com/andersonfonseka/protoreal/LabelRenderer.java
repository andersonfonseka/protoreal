package com.andersonfonseka.protoreal;

import com.andersonfonseka.Component;
import com.andersonfonseka.Renderer;
import com.andersonfonseka.RendererImpl;

public class LabelRenderer extends RendererImpl implements Renderer {

	public LabelRenderer(Component component) {
		super("label.vm", component);
	}
	
}
