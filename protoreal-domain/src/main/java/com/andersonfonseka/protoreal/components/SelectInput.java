package com.andersonfonseka.protoreal.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.andersonfonseka.protoreal.components.render.SelectInputRenderer;

public class SelectInput extends Input {
	
	private List<String> options = new ArrayList<String>();
	
	public SelectInput() {
		this("Select", "Option1", "Option2", "Option3");
	}
	
	public SelectInput(String title, String... options) {
		super(title);
		this.options = Arrays.asList(options);
	}

	public List<String> getOptions() {
		return options;
	}
	
	@Override
	public String doRender() {
		return new SelectInputRenderer(this).execute();
	}

	@Override
	public String doEdit() {
		// TODO Auto-generated method stub
		return this.getClass().getSimpleName();
	}

	
}
