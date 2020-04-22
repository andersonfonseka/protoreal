package com.andersonfonseka.protoreal.components;

import com.andersonfonseka.protoreal.components.render.FormRenderer;

public class Form extends Component {

	@Override
	public String doRender() {
		return new FormRenderer(this).execute();
	}
	
}
