package com.andersonfonseka.protoreal;

import com.andersonfonseka.Component;
import com.andersonfonseka.Renderer;
import com.andersonfonseka.RendererImpl;

public class JumbotronRenderer extends RendererImpl implements Renderer {

	public JumbotronRenderer(Component component) {
		super("jumbotron.vm", component);
	}
	
}
