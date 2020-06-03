package com.andersonfonseka.protoreal.components;

import com.andersonfonseka.Component;
import com.andersonfonseka.protoreal.components.render.SeparatorRenderer;

public class Separator extends Component {

	public String doRender() {
		return new SeparatorRenderer(this).execute();
	}

	public String doEdit() {
		return null;
	}

	public String doPreview() {
		return null;
	}

}
