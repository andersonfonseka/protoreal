package com.andersonfonseka.protoreal.components;

import com.andersonfonseka.protoreal.components.render.CardRenderer;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class Card extends Component {
	
	private String title;
	
	private String subtitle;

	private String content;
	
	public Card() {
		this("Teste", "Teste XXXX", "");
	}
	
	public Card(String title, String subtitle, String content) {
		super();
		this.title = title;
		this.subtitle = subtitle;
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
