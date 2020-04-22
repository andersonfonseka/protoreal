package com.andersonfonseka.protoreal.components;

import com.andersonfonseka.protoreal.components.render.NavbarRenderer;

public class Navbar extends Component {

	@Override
	public String doRender() {
		return new NavbarRenderer(this).execute();
	}

	
}
