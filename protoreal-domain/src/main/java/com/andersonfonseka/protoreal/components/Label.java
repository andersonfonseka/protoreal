package com.andersonfonseka.protoreal.components;

import com.andersonfonseka.protoreal.components.render.LabelRenderer;

public class Label extends Input {
	
	public Label() {
		this("Text");
	}
	
	public Label(String label) {
		super(label);
	}
	
	@Override
	public String doRender() {
		return new LabelRenderer(this).execute();
	}

	@Override
	public String doEdit() {
		return new LabelRenderer(this).executeProperties();
	}

	@Override
	public String doPreview() {
		return new LabelRenderer(this).executePreview();
	}
	
}
