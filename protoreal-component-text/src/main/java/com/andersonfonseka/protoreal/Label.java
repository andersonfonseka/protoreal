package com.andersonfonseka.protoreal;

import java.util.ArrayList;
import java.util.List;

import com.andersonfonseka.Input;
import com.andersonfonseka.SelectItem;

public class Label extends Input {
	
	private String type = "";
	
	private List<SelectItem> listTypes = new ArrayList<SelectItem>();
	
	private String style = "";
	
	private List<SelectItem> listStyles = new ArrayList<SelectItem>();
	
	public Label() {
		this("Text");
	}
	
	public Label(String label) {
		super(label);
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	public List<SelectItem> getListTypes() {
		
		listTypes.clear();
		
		listTypes.add(new SelectItem("lb-md", "Padrao"));
		listTypes.add(new SelectItem("lb-sm", "Pequeno"));
		listTypes.add(new SelectItem("lb-lg", "Grande"));
		
		for (SelectItem selectItem : listTypes) {
			if (selectItem.getValue().equals(this.type)) {
				selectItem.setSelected("selected");
			}
		}
		
		return listTypes;
	}

	public void setListTypes(List<SelectItem> listTypes) {
		this.listTypes = listTypes;
	}
	
	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}
	
	
	public List<SelectItem> getListStyles() {
		
		listStyles.clear();
		
		listStyles.add(new SelectItem("font-weight-normal", "Normal"));
		listStyles.add(new SelectItem("font-weight-bold", "Negrito"));
		listStyles.add(new SelectItem("font-weight-light", "Light"));
		listStyles.add(new SelectItem("font-italic", "Italico"));
		
		for (SelectItem selectItem : listStyles) {
			if (selectItem.getValue().equals(this.style)) {
				selectItem.setSelected("selected");
			}
		}
		
		return listStyles;
	}
	
	
	public String doRender() {
		return new LabelRenderer(this).execute();
	}

	
	public String doEdit() {
		return new LabelRenderer(this).executeProperties();
	}

	
	public String doPreview() {
		return new LabelRenderer(this).executePreview();
	}
	
}
