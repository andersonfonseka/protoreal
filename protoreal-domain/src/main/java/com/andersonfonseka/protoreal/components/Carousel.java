package com.andersonfonseka.protoreal.components;

import com.andersonfonseka.Component;
import com.andersonfonseka.protoreal.components.render.CarouselRenderer;

public class Carousel extends Component {
	
	private String title;
	
	private String subtitle;

	
	public Carousel() {
		this("Teste", "Teste XXXX");
	}
	
	public Carousel(String title, String subtitle) {
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
		return new CarouselRenderer(this).execute();
	}

	
	public String doEdit() {
		return new CarouselRenderer(this).executeProperties();
	}

	
	public String doPreview() {
		return new CarouselRenderer(this).executePreview();
	}
	
}
