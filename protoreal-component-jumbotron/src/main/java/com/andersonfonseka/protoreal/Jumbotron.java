package com.andersonfonseka.protoreal;

import com.andersonfonseka.Component;

public class Jumbotron extends Component {
	
	private String title;
	
	private String subtitle;
	
	private String file;
	
	private String fontColor="#000000";
	
	private String buttonText="";
	
	private String buttonUrl="";

	
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
	
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	public String getFontColor() {
		return fontColor;
	}

	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}

	public String getButtonText() {
		return buttonText;
	}

	public void setButtonText(String buttonText) {
		this.buttonText = buttonText;
	}

	public String getButtonUrl() {
		return buttonUrl;
	}

	public void setButtonUrl(String buttonUrl) {
		this.buttonUrl = buttonUrl;
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
