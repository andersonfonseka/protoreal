package com.andersonfonseka.protoreal;

import com.andersonfonseka.Component;

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
