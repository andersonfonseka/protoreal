package com.andersonfonseka.protoreal.components;

import com.andersonfonseka.protoreal.components.render.SeparatorRenderer;

public class Separator extends Component {

	@Override
	public String doRender() {
		return new SeparatorRenderer(this).execute();
	}

}
