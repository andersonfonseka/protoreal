package com.andersonfonseka.project.form;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

public class ProjectForm extends ValidatorForm {
	
	private static final long serialVersionUID = 1L;

	private String name;
	
	private String title;
	
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public void reset(ActionMapping mapping, ServletRequest request) {
		super.reset(mapping, request);

		this.title = "";
		this.name = "";
		this.description = "";
	}

}
