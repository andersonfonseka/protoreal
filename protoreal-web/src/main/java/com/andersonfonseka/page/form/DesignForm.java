package com.andersonfonseka.page.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.validator.ValidatorForm;

import com.andersonfonseka.protoreal.components.Page;

public class DesignForm extends ValidatorForm {
	
	private String componentSelected;
	
	private List componentList = new ArrayList<Page>();

	public String getComponentSelected() {
		return componentSelected;
	}

	public void setComponentSelected(String componentSelected) {
		this.componentSelected = componentSelected;
	}

	public List getComponentList() {
		return componentList;
	}

	public void setComponentList(List componentList) {
		this.componentList = componentList;
	}
	
}
