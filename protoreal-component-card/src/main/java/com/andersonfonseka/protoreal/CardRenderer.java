package com.andersonfonseka.protoreal;

import com.andersonfonseka.Component;
import com.andersonfonseka.Renderer;
import com.andersonfonseka.RendererImpl;

public class CardRenderer extends RendererImpl implements Renderer {

	public CardRenderer(Component component) {
		super("card.vm", component);
	}
	
}
