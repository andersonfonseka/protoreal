package com.andersonfonseka.protoreal.components;

import com.andersonfonseka.protoreal.components.render.NavLinkRenderer;

public class NavLink extends Input {
	
	public NavLink(String label) {
		super(label);
	}
	
	@Override
	public String doRender() {
		return new NavLinkRenderer(this).execute();
	}

	
}
