package com.andersonfonseka.protoreal.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.andersonfonseka.protoreal.components.render.CellRenderer;

public class Cell extends Component {

	@Override
	public String doRender() {
		return new CellRenderer(this).execute();
	}

	public void addChild(Component child) {
		//if (getChildrenList().isEmpty()) {
			super.addChild(child);
		//}
	}

	@Override
	public Collection<Component> getChildrenList() {
		
		List<Component> component = new ArrayList<Component>();
		
		if (!super.getChildrenList().isEmpty()) {
			for (Component component2: super.getChildrenList()) {
				component.add(component2);
				break;
			}
		}
		
		return component;
	}
	
	
	
}
