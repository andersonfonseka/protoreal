package com.andersonfonseka.protoreal.components;

import com.andersonfonseka.protoreal.components.impl.Component;
import com.andersonfonseka.protoreal.components.render.JumbotronRenderer;

public class Jumbotron extends Component {
	
	private String title;
	
	private String subtitle;

	
	public Jumbotron() {
		this("Teste", "Teste XXXX");
	}
	
	public Jumbotron(String title, String subtitle) {
		super();
		this.title = title;
		this.subtitle = subtitle;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	
	public String doRender() {
		return new JumbotronRenderer(this).execute();
	}

	
	public String doEdit() {
		return new JumbotronRenderer(this).executeProperties();
	}

	
	public String doPreview() {
		return new JumbotronRenderer(this).executePreview();
	}
	
}
