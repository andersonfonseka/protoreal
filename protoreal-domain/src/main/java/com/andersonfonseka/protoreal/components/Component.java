package com.andersonfonseka.protoreal.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public abstract class Component {
	
	private String siteUuid;
	
	private String uuid = UUID.randomUUID().toString();
	
	private String name;
	
	private Component parent;
	
	private String title;
	
	private Map<String, Component> childrenMap = new HashMap<String, Component>();
	
	private List<Component> children = new ArrayList<Component>();
	
	private boolean deleted;
	
	public Component() {
		this.name = this.getClass().getSimpleName() + "#" + this.uuid;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Component getParent() {
		return parent;
	}

	public void setParent(Component parent) {
		this.parent = parent;
	}
	
	public Collection<Component> getChildrenList() {
		return this.children;
	}

	public void addChild(Component child) {
		child.setParent(this);
		this.children.add(child);
		this.childrenMap.put(child.getUuid(), child);
	}
	
	private Component componentAux;
	
	public Component getChildComponent(Component compChild, String uuid) {
		
		Component comp = compChild.childrenMap.get(uuid);
		
		if (null == comp) {
			for (Component component: compChild.getChildrenList()) {
				getChildComponent(component, uuid);
			}
		} else {
			componentAux = comp;
		}
		
		return comp;
	}
	
	public Component getComponentAux() {
		return componentAux;
	}
	
	public void resetComponentAux() {
		componentAux = null;
	}

	public void removeChild(Component child) {
		this.children.remove(child);
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getSimpleName() {
		String simpleUuid = this.uuid.substring(0, this.uuid.indexOf("-"));
		return this.getClass().getSimpleName() + "-" + simpleUuid;
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getSiteUuid() {
		return siteUuid;
	}

	public void setSiteUuid(String siteUuid) {
		this.siteUuid = siteUuid;
	}
	
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public abstract String doRender();
	
	public abstract String doEdit();
	
	public abstract String doPreview();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Component other = (Component) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}
	
}
