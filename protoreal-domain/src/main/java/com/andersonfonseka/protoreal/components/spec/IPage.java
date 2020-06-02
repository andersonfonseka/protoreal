package com.andersonfonseka.protoreal.components.spec;

import java.util.List;

import com.andersonfonseka.protoreal.components.Site;

public interface IPage extends IComponent {

	public Site getSite();

	public String getTitle();

	public void setTitle(String title);

	public String getName();

	public void setName(String name);

	public String getDescription();

	public void setDescription(String description);

	public boolean isDisplayOnMenu();

	public void setDisplayOnMenu(boolean displayOnMenu);

	public boolean isHideMenu();

	public void setHideMenu(boolean hideMenu);

	public boolean isShowTitle();

	public void setShowTitle(boolean showTitle);

	public List<IComponent> getPagesDisplayOnMenu();

	public boolean getPageChildren();

	public String getContainerType();

	public void setContainerType(String containerType);

	public void addFastComponent(IComponent component);

	public List<IComponent> getFastComponents();

	public IComponent getFastComponents(String uuid);

	public String getType();

	public void setType(String type);

}
