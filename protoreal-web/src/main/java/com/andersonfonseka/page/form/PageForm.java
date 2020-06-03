package com.andersonfonseka.page.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import com.andersonfonseka.Page;
import com.andersonfonseka.Site;
import com.andersonfonseka.common.SelectItem;
import com.andersonfonseka.dao.PageRepository;
import com.andersonfonseka.dao.SiteRepository;

public class PageForm extends ValidatorForm {
	
	private String uuid;
	
	private SiteRepository siteRepository = SiteRepository.getInstance();
	
	private PageRepository repository = PageRepository.getInstance();
	
	private Site site;
	
	private static final long serialVersionUID = 1L;

	private String name;
	
	private String title;
	
	private String description;
	
	private String parentPage;
	
	private String pageType;
	
	private List<SelectItem> typeList = new ArrayList<SelectItem>();
	
	private String containerType = "container";
	
	private List<SelectItem> containerTypeList = new ArrayList<SelectItem>();
	
	private boolean displayOnMenu = false;
	
	private boolean hideMenu = false;
	
	private boolean showTitle = true;
	
	private List<Page> pageList = new ArrayList<Page>();
	
	private String op = "N";
	
	private String checkDisplayMenu;
	
	private String checkHideMenu;
	
	private String checkShowTitle;
	
	public PageForm() {}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PageForm(String siteUUid) {
		
		site = siteRepository.get(siteUUid);
		site.setChildren(repository.list(site.getUuid()));
		
		if (null != site) {
			this.pageList = new ArrayList(site.getPages());	
		}
		
		this.typeList.add(new SelectItem("padrao", "Padrao"));
		this.typeList.add(new SelectItem("modal", "Modal"));
		
		this.containerTypeList.add(new SelectItem("container", "Padrao"));
		this.containerTypeList.add(new SelectItem("container-fluid", "Estendido"));
	}
		
	public List<Page> getPageList() {
		return pageList;
	}

	public void setPageList(List<Page> pageList) {
		this.pageList = pageList;
	}

	public String getParentPage() {
		return parentPage;
	}

	public void setParentPage(String parentPage) {
		this.parentPage = parentPage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isDisplayOnMenu() {
		return displayOnMenu;
	}

	public void setDisplayOnMenu(boolean displayOnMenu) {
		this.displayOnMenu = displayOnMenu;
	}
	
	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pagetType) {
		this.pageType = pagetType;
	}
	
	public List<SelectItem> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<SelectItem> typeList) {
		this.typeList = typeList;
	}
	
	public String getContainerType() {
		return containerType;
	}

	public void setContainerType(String containerType) {
		this.containerType = containerType;
	}

	public List<SelectItem> getContainerTypeList() {
		return containerTypeList;
	}

	public void setContainerTypeList(List<SelectItem> containerTypeList) {
		this.containerTypeList = containerTypeList;
	}
	
	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public boolean isHideMenu() {
		return hideMenu;
	}

	public void setHideMenu(boolean hideMenu) {
		this.hideMenu = hideMenu;
	}
	
	public boolean isShowTitle() {
		return showTitle;
	}

	public void setShowTitle(boolean showTitle) {
		this.showTitle = showTitle;
	}

	public String getCheckDisplayMenu() {
		return checkDisplayMenu;
	}

	public void setCheckDisplayMenu(String checkDisplayMenu) {
		this.checkDisplayMenu = checkDisplayMenu;
	}

	public String getCheckHideMenu() {
		return checkHideMenu;
	}

	public void setCheckHideMenu(String checkHideMenu) {
		this.checkHideMenu = checkHideMenu;
	}
	
	public String getCheckShowTitle() {
		return checkShowTitle;
	}

	public void setCheckShowTitle(String checkShowTitle) {
		this.checkShowTitle = checkShowTitle;
	}

	@Override
	public void reset(ActionMapping mapping, ServletRequest request) {
		super.reset(mapping, request);
		
		this.parentPage = "";
		this.title = "";
		this.name = "";
		this.description = "";
	}
	
	
	
}
