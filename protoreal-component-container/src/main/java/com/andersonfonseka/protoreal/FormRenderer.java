package com.andersonfonseka.protoreal;

import com.andersonfonseka.Component;
import com.andersonfonseka.Renderer;
import com.andersonfonseka.RendererImpl;

public class FormRenderer extends RendererImpl implements Renderer {

	public FormRenderer(Component component) {
		super("form.vm", component);
	}
	
}
