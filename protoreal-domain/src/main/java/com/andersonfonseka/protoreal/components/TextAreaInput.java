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

	public void setRows(int rows) {
		this.rows = rows;
	}



	@Override
	public String doRender() {
		return new TextAreaInputRenderer(this).execute();
	}

	@Override
	public String doEdit() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
