package com.andersonfonseka;

import java.util.ArrayList;
import java.util.List;

public class NavDropdown extends Input {
	
	private List<NavLink> options = new ArrayList<NavLink>();
	
	public NavDropdown(String title) {
		super(title);
	}

	public List<NavLink> getOptions() {
		return options;
	}
	
	public void addOptions(NavLink option) {
		options.add(option);
	}
	
	
	public String doRender() {
		return new NavDropdownRenderer(this).execute();
	}

	
	public String doEdit() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String doPreview() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
