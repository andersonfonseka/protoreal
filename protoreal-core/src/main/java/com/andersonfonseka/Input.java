package com.andersonfonseka;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Input extends Component implements IInput {
	
	private String label;
	
	private String placeholder = "";
	
	private String readonly = "";
	
	private String value = "";
	
	public Input() {}
	
	public Input(String label) {
		super();
		this.label = label;
	}
	
	public String getReadonly() {
		
		if (readonly.equals("true")) {
			readonly = "readonly"; 
		}
		
		return readonly;
	}
	
	@Override
	public String getName() {
		super.setName(this.getClass().getSimpleName() + "#" + this.label);
		return super.getName();
	}
	
	public String getSimpleName() {
		super.setName(this.getClass().getSimpleName() + "#" + this.value);
		return super.getName();
	}
	
}
