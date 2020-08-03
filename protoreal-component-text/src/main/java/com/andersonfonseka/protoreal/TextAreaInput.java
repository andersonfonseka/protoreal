package com.andersonfonseka.protoreal;

import com.andersonfonseka.Input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TextAreaInput extends Input {

	private int rows = 1;
	
	private String content;
	
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
