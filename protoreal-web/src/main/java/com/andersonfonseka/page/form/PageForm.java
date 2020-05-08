package com.andersonfonseka.page.form;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import com.andersonfonseka.common.SelectItem;
import com.andersonfonseka.dao.SiteRepository;
import com.andersonfonseka.protoreal.components.Page;
import com.andersonfonseka.protoreal.components.Site;

public class PageForm extends ValidatorForm {
	
	private SiteRepository siteRepository = SiteRepository.getInstance();
	
	private Site site;
	
	private static final long serialVersionUID = 1L;

	private String name;
	
	private String title;
	
	private String description;
	
	private String parentPage;
	
	private String pagetType;
	
	private List<SelectItem> typeList = new ArrayList<SelectItem>();
	
	private String containerType = "container";
	
	private List<SelectItem> containerTypeList = new ArrayList<SelectItem>();
	
	private boolean displayOnMenu = false;
	
	private List<Page> pageList = new ArrayList<Page>();
	
	public PageForm() {}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PageForm(String siteUUid) {
		site = siteRepository.get(siteUUid);
		if (null != site) {
			this.pageList = new ArrayList(site.getPages().stream().filter(x -> !(x.getParent() instanceof Page)).collect(Collectors.toList()));	
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
	
	public String getPagetType() {
		return pagetType;
	}

	public void setPagetType(String pagetType) {
		this.pagetType = pagetType;
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

	@Override
	public void reset(ActionMapping mapping, ServletRequest request) {
		super.reset(mapping, request);
		
		this.parentPage = "";
		this.title = "";
		this.name = "";
		this.description = "";
	}
	
	
	
}
