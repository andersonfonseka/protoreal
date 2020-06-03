package com.andersonfonseka;

import java.util.List;

public interface IComponent {
	
	public String getUuid();

	public void setUuid(String uuid);
	
	public String getParent();

	public void setParent(String parent);
	
	public IComponent getParentComponent();

	public void setParentComponent(IComponent parentComponent);

	public List<IComponent> getChildrenList() ;

	public void addChild(IComponent child) ;

	public void removeChild(IComponent child);
	
	public String getTitle();
	
	public void setTitle(String title);
	
	public String getName();

	public void setName(String name);
	
	public String getSiteUuid();

	public void setSiteUuid(String siteUuid);
	
	public String getPageUuid();

	public void setPageUuid(String pageUuid);

	public boolean isDeleted();

	public void setDeleted(boolean deleted);
	
	public String getType();

	public void setType(String type);
	
	public String getSimpleName();
	
	public String doRender();
	
	public String doEdit();
	
	public String doPreview();

}
