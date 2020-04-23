package com.andersonfonseka.protoreal.components;

import com.andersonfonseka.protoreal.components.render.CellRenderer;

public class Cell extends Component {

	@Override
	public String doRender() {
		return new CellRenderer(this).execute();
	}

	public void addChild(Component child) {
		super.addChild(child);
	}

	@Override
	public String doEdit() {
		// TODO Auto-generated method stub
		return null;
	}


	
	
}
