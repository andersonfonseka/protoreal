package com.andersonfonseka.protoreal;

import com.andersonfonseka.Component;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class Card extends Component {
	
	private String title;

	private String content;
	
	private String fileCard;
	
	private String buttonText="";
	
	private String buttonUrl="";
	
	public Card() {
		this("Teste", "");
	}
	
	public Card(String title, String content) {
		super();
		this.title = title;
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
