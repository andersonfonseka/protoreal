package com.andersonfonseka.protoreal;

import com.andersonfonseka.Component;

public class Image extends Component {
	
	private String file;
	
	public Image() {}
	
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String doRender() {
		return new ImageRenderer(this).execute();
	}
	
	public String doEdit() {
		return new ImageRenderer(this).executeProperties();
	}
	
	public String doPreview() {
		return new ImageRenderer(this).executePreview();
	}
	
}
