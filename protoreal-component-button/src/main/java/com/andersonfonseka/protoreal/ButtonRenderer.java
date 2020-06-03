package com.andersonfonseka.protoreal;

import com.andersonfonseka.Component;
import com.andersonfonseka.Renderer;
import com.andersonfonseka.RendererImpl;

public class ButtonRenderer extends RendererImpl implements Renderer {

	public ButtonRenderer(Component component) {
		super("button.vm", component);
	}
	
}
