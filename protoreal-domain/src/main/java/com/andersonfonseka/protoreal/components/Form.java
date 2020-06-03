package com.andersonfonseka.protoreal.components;

import com.andersonfonseka.Component;
import com.andersonfonseka.protoreal.components.render.FormRenderer;

public class Form extends Component {

	
	public String doRender() {
		return new FormRenderer(this).execute();
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
