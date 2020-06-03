package com.andersonfonseka.protoreal;

import com.andersonfonseka.Component;
import com.andersonfonseka.Renderer;
import com.andersonfonseka.RendererImpl;

public class TextInputRenderer extends RendererImpl implements Renderer {

	public TextInputRenderer(Component component) {
		super("textinput.vm", component);
	}
	
}
