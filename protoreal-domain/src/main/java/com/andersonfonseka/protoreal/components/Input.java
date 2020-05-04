package com.andersonfonseka.protoreal.components;

public abstract class Input extends Component {
	
	private String label;
	
	private String placeholder = "";
	
	private String readonly = "";
	
	private String value = "";
	
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
	
	public String getReadonly() {
		
		if (readonly.equals("true")) {
			readonly = "readonly"; 
		}
		
		return readonly;
	}

	public void setReadonly(String readonly) {
		this.readonly = readonly;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String getName() {
		super.setName(this.getClass().getSimpleName() + "#" + this.label);
		return super.getName();
	}
	
}
