package com.andersonfonseka.project.form;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import com.andersonfonseka.dao.SiteRepository;
import com.andersonfonseka.protoreal.components.Page;
import com.andersonfonseka.protoreal.components.Site;

public class ProjectForm extends ValidatorForm {
	
	private String uuid;
	
	private SiteRepository siteRepository = SiteRepository.getInstance();
	
	private Site site;
	
	private static final long serialVersionUID = 1L;

	private String name;
	
	private String title;
	
	private String description;
	
	private String initialPage;

	private List<Page> pageList = new ArrayList<Page>();
	
	private String op = "N";
	
	public ProjectForm() {}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ProjectForm(String siteUUid) {
		site = siteRepository.get(siteUUid);
		if (null != site) {
			this.pageList = new ArrayList(site.getPages().stream().filter(x -> !(x.getParent() instanceof Page)).collect(Collectors.toList()));	
		}

	}
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
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
	
	public String getInitialPage() {
		return initialPage;
	}

	public void setInitialPage(String initialPage) {
		this.initialPage = initialPage;
	}

	public List<Page> getPageList() {
		return pageList;
	}

	public void setPageList(List<Page> pageList) {
		this.pageList = pageList;
	}
	
	@Override
	public void reset(ActionMapping mapping, ServletRequest request) {
		super.reset(mapping, request);

		this.title = "";
		this.name = "";
		this.description = "";
	}

}
