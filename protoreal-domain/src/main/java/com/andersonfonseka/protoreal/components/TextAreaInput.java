package com.andersonfonseka.protoreal.components;

import com.andersonfonseka.protoreal.components.render.TextAreaInputRenderer;

public class TextAreaInput extends Input {

	private int rows = 1;
	
	public TextAreaInput() {
		this("TextArea", 10);
	}
	
	public TextAreaInput(String label, int rows) {
		super(label);
		this.rows = rows;
	}
	
	public int getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = Integer.valueOf(rows);
	}

	@Override
	public String doRender() {
		return new TextAreaInputRenderer(this).execute();
	}

	@Override
	public String doEdit() {
		return new TextAreaInputRenderer(this).executeProperties();
	}

	@Override
	public String doPreview() {
		return new TextAreaInputRenderer(this).executePreview();
	}

	
}
