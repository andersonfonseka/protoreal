package com.andersonfonseka.protoreal.components;

import java.util.ArrayList;
import java.util.List;

import com.andersonfonseka.protoreal.components.common.SelectItem;
import com.andersonfonseka.protoreal.components.render.ButtonRenderer;

public class Button extends Input {
	
	private String cssClass;
	
	public static final String PRIMARY = "btn btn-primary";
	public static final String SECONDARY = "btn btn-secondary";
	public static final String SUCCESS = "btn btn-success";
	public static final String DANGER = "btn btn-danger";	
	public static final String WARNING = "btn btn-warning";
	public static final String INFO = "btn btn-info";
	public static final String LIGHT = "btn btn-light";
	public static final String DARK = "btn btn-dark";
	public static final String LINK = "btn btn-link";
	
	private String openType = "default";
	
	private List<SelectItem> selectPages = new ArrayList<SelectItem>();
	
	private List<Component> pages = new ArrayList<Component>();
	
	private List<SelectItem> cssStyles = new ArrayList<SelectItem>(); 
	
	private Page page;
	
	private String pageUuid;
	
	public Button() {
		super("Button");
		this.cssClass = PRIMARY;
	}
	
	public Button(String label, String cssClass) {
		super(label);
		this.cssClass = cssClass;
	}
	
	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
	
	public List<SelectItem> getPages() {
		
		this.selectPages.clear();
		
		for (Component  component : this.pages) {
			Page page = (Page) component;

			SelectItem selectItem = new SelectItem(page.getUuid(), page.getTitle());
			if (page.getUuid().equals(this.pageUuid)) {
				selectItem.setSelected("selected");
			}
			
			this.selectPages.add(selectItem);
		}
		
		return selectPages;
	}

	public void setPages(List<Component> pPages) {
		this.pages = pPages;
	}

	public String getPageUuid() {
		return pageUuid;
	}

	public void setPageUuid(String pageUuid) {
		this.pageUuid = pageUuid;
	}
	
	public String getOpenType() {
		return openType;
	}

	public void setOpenType(String openType) {
		this.openType = openType;
	}
	
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	
	public List<SelectItem> getCssStyles() {
		
		cssStyles.clear();
		
		cssStyles.add(new SelectItem("btn btn-primary", "Primario"));
		cssStyles.add(new SelectItem("btn btn-secondary", "Secundario"));
		cssStyles.add(new SelectItem("btn btn-success", "Sucesso"));
		cssStyles.add(new SelectItem("btn btn-danger", "Erro"));
		cssStyles.add(new SelectItem("btn btn-warning", "Advertencia"));
		cssStyles.add(new SelectItem("btn btn-info", "Informativo"));
		cssStyles.add(new SelectItem("btn btn-light", "Light"));
		cssStyles.add(new SelectItem("btn btn-dark", "Dark"));
		cssStyles.add(new SelectItem("btn btn-link", "Link"));
		
		for (SelectItem selectItem : cssStyles) {
			if (selectItem.getValue().equals(cssClass)) {
				selectItem.setSelected("selected");
			}
		}
		
		return cssStyles;
	}

	public void setCssStyles(List<SelectItem> cssStyles) {
		this.cssStyles = cssStyles;
	}

	@Override
	public String doRender() {
		return new ButtonRenderer(this).execute();
	}


	@Override
	public String doEdit() {
		return new ButtonRenderer(this).executeProperties();
	}


	@Override
	public String doPreview() {
		return new ButtonRenderer(this).executePreview();
	}
	
}
