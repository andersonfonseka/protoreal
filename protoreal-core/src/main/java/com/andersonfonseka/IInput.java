package com.andersonfonseka;

public interface IInput {
	
	public String getLabel();

	public void setLabel(String label);

	public String getPlaceholder();

	public void setPlaceholder(String placeholder);
	
	public void setReadonly(String readonly);
	
	public String getValue();

	public void setValue(String value);


}
