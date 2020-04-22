package com.andersonfonseka.protoreal.components;

public class Input extends Component {
	
	private String label;
	
	private String placeholder = "";
	
	private String readOnly = "";
	
	public Input(String label) {
		super();
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}
	
	public String isDisabled() {
		return readOnly;
	}

	public void setDisabled(String disabled) {
		this.readOnly = disabled;
	}

	@Override
	public String doRender() {
		// TODO Auto-generated method stub
		return null;
	}
	

	
}
