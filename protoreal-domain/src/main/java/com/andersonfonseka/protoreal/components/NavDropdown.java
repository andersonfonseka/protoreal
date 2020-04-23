package com.andersonfonseka.protoreal.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.andersonfonseka.protoreal.components.render.NavDropdownRenderer;

public class NavDropdown extends Input {
	
	private List<String> options = new ArrayList<String>();
	
	public NavDropdown(String title) {
		super(title);
	}

	public List<String> getOptions() {
		return options;
	}
	
	public void addOptions(String option) {
		options.add(option);
	}
	
	@Override
	public String doRender() {
		return new NavDropdownRenderer(this).execute();
	}

	@Override
	public String doEdit() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
