package com.andersonfonseka.page.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.validator.ValidatorForm;

import com.andersonfonseka.protoreal.components.spec.IComponent;

public class DesignForm extends ValidatorForm {
	
	private static final long serialVersionUID = 1L;

	private String componentSelected;
	
	private List<IComponent> componentList = new ArrayList<IComponent>();

	public String getComponentSelected() {
		return componentSelected;
	}

	public void setComponentSelected(String componentSelected) {
		this.componentSelected = componentSelected;
	}

	public List<IComponent> getComponentList() {
		return componentList;
	}

	public void setComponentList(List<IComponent> componentList) {
		this.componentList = componentList;
	}
	
}
