package com.andersonfonseka.protoreal.components;

import java.util.ArrayList;
import java.util.List;

import com.andersonfonseka.protoreal.components.common.SelectItem;
import com.andersonfonseka.protoreal.components.render.LabelRenderer;

public class Label extends Input {
	
	private String type = "";
	
	private List<SelectItem> listTypes = new ArrayList<SelectItem>();
	
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

	@Override
	public String doRender() {
		return new LabelRenderer(this).execute();
	}

	@Override
	public String doEdit() {
		return new LabelRenderer(this).executeProperties();
	}

	@Override
	public String doPreview() {
		return new LabelRenderer(this).executePreview();
	}
	
}
