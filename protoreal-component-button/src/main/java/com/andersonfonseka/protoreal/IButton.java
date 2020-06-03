package com.andersonfonseka.protoreal;

import java.util.List;

import com.andersonfonseka.IComponent;
import com.andersonfonseka.Page;
import com.andersonfonseka.SelectItem;

public interface IButton extends IComponent {

	public List<SelectItem> getPages();

	public List<SelectItem> getCssStyles();

	public List<SelectItem> getNavOpenType();

	public List<SelectItem> getAlignmentOptions();

	public String getCssClass();

	public String getOpenType();

	public String getAlignment();

	public String getLabel();

	public void setSelectPages(List<SelectItem> selectPages);

	public void setPages(List<IComponent> pages);

	public void setCssClass(String cssClass);

	public void setCssStyles(List<SelectItem> cssStyles);

	public void setOpenType(String openType);

	public void setNavOpenType(List<SelectItem> navOpenType);

	public void setAlignment(String alignment);

	public void setAlignmentOptions(List<SelectItem> alignmentOptions);

	public void setPage(Page page);

	public void setPageUuid(String pageUuid);

}
