package com.andersonfonseka.protoreal;

import com.andersonfonseka.Component;
import com.andersonfonseka.Renderer;
import com.andersonfonseka.RendererImpl;

public class ImageRenderer extends RendererImpl implements Renderer {

	public ImageRenderer(Component component) {
		super("image.vm", component);
	}
	
}
