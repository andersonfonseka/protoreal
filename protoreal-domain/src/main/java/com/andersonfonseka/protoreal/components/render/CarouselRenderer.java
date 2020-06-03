package com.andersonfonseka.protoreal.components.render;

import com.andersonfonseka.Component;
import com.andersonfonseka.Renderer;
import com.andersonfonseka.RendererImpl;

public class CarouselRenderer extends RendererImpl implements Renderer {

	public CarouselRenderer(Component component) {
		super("carousel.vm", component);
	}
	
}
