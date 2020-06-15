package com.andersonfonseka.protoreal;

import com.andersonfonseka.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Form extends Component {
	
	private String url = "";
	
	public Form() {
		setTitle("");
	}
	
	public String doRender() {
		return new FormRenderer(this).execute();
	}

	
	public String doEdit() {
		return new FormRenderer(this).executeProperties();
	}

	
	public String doPreview() {
		return new FormRenderer(this).executePreview();
	}
	
}
