package com.andersonfonseka.page.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.validator.ValidatorForm;

import com.andersonfonseka.protoreal.components.Component;

public class DesignForm extends ValidatorForm {
	
	private static final long serialVersionUID = 1L;

	private String componentSelected;
	
	private List<Component> componentList = new ArrayList<Component>();

	public String getComponentSelected() {
		return componentSelected;
	}

	public void setComponentSelected(String componentSelected) {
		this.componentSelected = componentSelected;
	}

	public List<Component> getComponentList() {
		return componentList;
	}

	public void setComponentList(List<Component> componentList) {
		this.componentList = componentList;
	}
	
}
