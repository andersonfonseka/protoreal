package com.andersonfonseka.protoreal.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.andersonfonseka.protoreal.components.common.SelectItem;
import com.andersonfonseka.protoreal.components.render.SelectInputRenderer;

public class SelectInput extends Input {
	
	private String type = "Select";
	
	private List<SelectItem> listTypes = new ArrayList<SelectItem>();
	
	private List<String> options = new ArrayList<String>();
	
	public SelectInput() {
		this("Rotulo", "Option1", "Option2", "Option3");
	}
	
	public SelectInput(String title, String... options) {
		super(title);
		this.options = Arrays.asList(options);
	}
	
	public List<String> getOptions() {
		return this.options;
	}

	public String getOptionValues() {
		
		StringBuilder sb = new StringBuilder();
		
		for (String opt : options) {
			sb.append(opt + ";");
		}
		
		return sb.toString();
	}
	
	public void setOptions(String pOptions) {
		String[] options = pOptions.split(";");
		this.options = Arrays.asList(options);
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public List<SelectItem> getListTypes() {
		
		listTypes.clear();
		
		listTypes.add(new SelectItem("Select", "Selecao simples"));
		listTypes.add(new SelectItem("SelectM", "Selecao multipla"));
		listTypes.add(new SelectItem("Radio", "Radio"));
		listTypes.add(new SelectItem("Checkbox", "Checkbox"));
		
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
		return new SelectInputRenderer(this).execute();
	}

	@Override
	public String doEdit() {
		return new SelectInputRenderer(this).executeProperties();
	}

	@Override
	public String doPreview() {
		return new SelectInputRenderer(this).executePreview();
	}
	
}
