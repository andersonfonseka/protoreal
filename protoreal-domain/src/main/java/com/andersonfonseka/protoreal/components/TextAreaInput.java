package com.andersonfonseka.protoreal.components;

import com.andersonfonseka.protoreal.components.impl.Input;
import com.andersonfonseka.protoreal.components.render.TextAreaInputRenderer;

public class TextAreaInput extends Input {

	private int rows = 1;
	
	public TextAreaInput() {
		this("Rotulo", 10);
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

	
	public String doRender() {
		return new TextAreaInputRenderer(this).execute();
	}

	
	public String doEdit() {
		return new TextAreaInputRenderer(this).executeProperties();
	}

	
	public String doPreview() {
		return new TextAreaInputRenderer(this).executePreview();
	}

	
}
