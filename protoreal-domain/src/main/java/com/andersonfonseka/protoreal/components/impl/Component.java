package com.andersonfonseka.protoreal.components.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.andersonfonseka.protoreal.components.spec.IComponent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Component implements IComponent {
	
	private String siteUuid;
	
	private String pageUuid;
	
	private String uuid = UUID.randomUUID().toString();
	
	private String name;
	
	private String parent;
	
	private IComponent parentComponent;
	
	private String type;
	
	private String title;
	
	private Map<String, IComponent> childrenMap = new HashMap<String, IComponent>();
	
	private List<IComponent> children = new ArrayList<IComponent>();
	
	private boolean deleted;
	
	public Component() {
		this.name = this.getClass().getSimpleName() + "#" + this.uuid;
	}

	public List<IComponent> getChildrenList() {
		return this.children;
	}
	
	public void setChildren(List<? extends IComponent> children) {
		this.children = (List<IComponent>) children;
	}

	public void addChild(IComponent child) {
		child.setParent(this.getUuid());
		this.children.add(child);
		this.childrenMap.put(child.getUuid(), child);
	}

	public void removeChild(IComponent child) {
		this.children.remove(child);
	}
	
	public String toString() {
		String simpleUuid = this.uuid.substring(0, this.uuid.indexOf("-"));
		return this.getClass().getSimpleName() + "-" + simpleUuid;
	}

	@Override
	public String doRender() {return null;}

	@Override
	public String doEdit() {return null;}

	@Override
	public String doPreview() {return null;}
	
}
