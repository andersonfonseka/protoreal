package com.andersonfonseka;

public class PageRenderer extends RendererImpl implements Renderer {

	public PageRenderer(Component component) {
		super("page.vm", component);
	}
	
}
