package com.andersonfonseka.protoreal.components;

import com.andersonfonseka.protoreal.components.render.TextInputRenderer;

public class TextInput extends Input {

	private String type = "text";
	
	public TextInput() {
		this("Text");
	}
	
	public TextInput(String label) {
		super(label);
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String doRender() {
		return new TextInputRenderer(this).execute();
	}

	@Override
	public String doEdit() {
		return new TextInputRenderer(this).executeProperties();
	}

	@Override
	public String doPreview() {
		return new TextInputRenderer(this).executePreview();
	}
	
}
