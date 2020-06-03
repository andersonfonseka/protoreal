package com.andersonfonseka.protoreal;

import com.andersonfonseka.Component;

public class Cell extends Component {

	
	public String doRender() {
		return new CellRenderer(this).execute();
	}

	public void addChild(Component child) {
		super.addChild(child);
	}

	
	public String doEdit() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String doPreview() {
		return new CellRenderer(this).executePreview();
	}


	
	
}
