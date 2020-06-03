package com.andersonfonseka;

public class SiteRenderer extends RendererImpl implements Renderer {

	public SiteRenderer(Component component) {
		super("site.vm", component);
	}
	
}
