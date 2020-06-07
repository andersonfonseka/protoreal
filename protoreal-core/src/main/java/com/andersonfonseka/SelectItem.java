package com.andersonfonseka;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelectItem {
	
	private String value;
	
	private String label;
	
	private String selected;

	public SelectItem(String value, String label) {
		super();
		this.value = value;
		this.label = label;
	}
}
