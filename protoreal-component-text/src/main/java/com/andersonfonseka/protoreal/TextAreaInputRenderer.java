package com.andersonfonseka.protoreal;

import com.andersonfonseka.Component;
import com.andersonfonseka.Renderer;
import com.andersonfonseka.RendererImpl;

public class TextAreaInputRenderer extends RendererImpl implements Renderer {

	public TextAreaInputRenderer(Component component) {
		super("textareainput.vm", component);
	}
	
}
