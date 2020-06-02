package com.andersonfonseka.protoreal.components;

import com.andersonfonseka.protoreal.components.impl.Component;
import com.andersonfonseka.protoreal.components.render.NavbarRenderer;

public class Navbar extends Component {

	
	public String doRender() {
		return new NavbarRenderer(this).execute();
	}

	
	public String doEdit() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String doPreview() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
