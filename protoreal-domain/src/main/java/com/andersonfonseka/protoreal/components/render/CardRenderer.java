package com.andersonfonseka.protoreal.components.render;

import com.andersonfonseka.protoreal.components.impl.Component;

public class CardRenderer extends RendererImpl implements Renderer {

	public CardRenderer(Component component) {
		super("card.vm", component);
	}
	
}
