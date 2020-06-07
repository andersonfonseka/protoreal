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

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageForm extends ValidatorForm {
	
	private String uuid;
	
	private SiteRepository siteRepository = SiteRepository.getInstance();
	
	private PageRepository repository =  new PageRepository();
	
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
	
	@Override
	public void reset(ActionMapping mapping, ServletRequest request) {
		super.reset(mapping, request);
		
		this.parentPage = "";
		this.title = "";
		this.name = "";
		this.description = "";
	}
	
	
	
}
