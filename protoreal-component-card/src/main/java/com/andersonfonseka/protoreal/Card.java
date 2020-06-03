package com.andersonfonseka.protoreal;

import com.andersonfonseka.Component;

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

	
	public String doRender() {
		return new CardRenderer(this).execute();
	}

	
	public String doEdit() {
		return new CardRenderer(this).executeProperties();
	}

	
	public String doPreview() {
		return new CardRenderer(this).executePreview();
	}
	
}
