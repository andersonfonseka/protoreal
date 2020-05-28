package com.andersonfonseka.protoreal.components;

import com.andersonfonseka.protoreal.components.render.CardRenderer;

public class Card extends Component {
	
	private String title;
	
	private String subtitle;

	private String content;
	
	public Card() {
		this("Teste", "Teste XXXX");
	}
	
	public Card(String title, String subtitle) {
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
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String doRender() {
		return new CardRenderer(this).execute();
	}

	@Override
	public String doEdit() {
		return new CardRenderer(this).executeProperties();
	}

	@Override
	public String doPreview() {
		return new CardRenderer(this).executePreview();
	}
	
}
