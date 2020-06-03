package com.andersonfonseka.project.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import com.andersonfonseka.Page;
import com.andersonfonseka.Site;
import com.andersonfonseka.dao.PageRepository;
import com.andersonfonseka.dao.SiteRepository;

public class ProjectForm extends ValidatorForm {
	
	private String uuid;
	
	private SiteRepository siteRepository = SiteRepository.getInstance();
	
	private PageRepository repository = PageRepository.getInstance();
	
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
		site.setChildren(repository.list(site.getUuid()));
		
		if (null != site) {
			this.pageList = new ArrayList(site.getPages());	
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
