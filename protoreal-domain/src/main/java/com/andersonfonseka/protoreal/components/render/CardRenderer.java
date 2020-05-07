package com.andersonfonseka.protoreal.components.render;

import com.andersonfonseka.protoreal.components.Component;

public class CardRenderer extends RendererImpl implements Renderer {

	public CardRenderer(Component component) {
		super("card.vm", component);
	}
	
}
