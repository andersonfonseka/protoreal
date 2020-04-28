package com.andersonfonseka.protoreal.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.andersonfonseka.protoreal.components.render.SelectInputRenderer;

public class SelectInput extends Input {
	
	private String type = "Select";
	
	private List<String> options = new ArrayList<String>();
	
	public SelectInput() {
		this("Selecione", "Option1", "Option2", "Option3");
	}
	
	public SelectInput(String title, String... options) {
		super(title);
		this.options = Arrays.asList(options);
	}

	public List<String> getOptions() {
		return options;
	}
	
	public void setOptions(String pOptions) {
		String[] options = pOptions.split(";");
		this.options = Arrays.asList(options);
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String doRender() {
		return new SelectInputRenderer(this).execute();
	}

	@Override
	public String doEdit() {
		return new SelectInputRenderer(this).executeProperties();
	}

	@Override
	public String doPreview() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
