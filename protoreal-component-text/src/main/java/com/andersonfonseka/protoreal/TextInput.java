package com.andersonfonseka.protoreal;

import java.util.ArrayList;
import java.util.List;

import com.andersonfonseka.Input;
import com.andersonfonseka.SelectItem;

public class TextInput extends Input {

	private String type = "text";
	
	private List<SelectItem> listTypes = new ArrayList<SelectItem>();
	
	public TextInput() {
		this("Rotulo");
	}
	
	public TextInput(String label) {
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
		
		listTypes.add(new SelectItem("text", "Texto"));
		listTypes.add(new SelectItem("date", "Data"));
		listTypes.add(new SelectItem("number", "Numero"));
		listTypes.add(new SelectItem("password", "Senha"));
		listTypes.add(new SelectItem("email", "E-mail"));
		listTypes.add(new SelectItem("file", "Arquivo"));
		
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
	
	
	public String doRender() {
		return new TextInputRenderer(this).execute();
	}

	
	public String doEdit() {
		return new TextInputRenderer(this).executeProperties();
	}

	
	public String doPreview() {
		return new TextInputRenderer(this).executePreview();
	}
	
}
