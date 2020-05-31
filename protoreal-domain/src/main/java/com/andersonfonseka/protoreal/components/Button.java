package com.andersonfonseka.protoreal.components;

import java.util.ArrayList;
import java.util.List;

import com.andersonfonseka.protoreal.components.common.SelectItem;
import com.andersonfonseka.protoreal.components.render.ButtonRenderer;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class Button extends Input {
	
	private List<SelectItem> selectPages = new ArrayList<SelectItem>();
	private List<Component> pages = new ArrayList<Component>();
	private String cssClass = "btn btn-primary";
	private List<SelectItem> cssStyles = new ArrayList<SelectItem>(); 
	private String openType = "default";
	private List<SelectItem> navOpenType = new ArrayList<SelectItem>(); 
	private String alignment = "float-left";
	private List<SelectItem> alignmentOptions = new ArrayList<SelectItem>(); 
	private Page page;
	private String pageUuid;
	
	public Button() {
		super("Button");
	}
	
	public Button(String label, String cssClass) {
		super(label);
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

	public List<SelectItem> getNavOpenType() {
		
		navOpenType.clear();
		
		navOpenType.add(new SelectItem("default", "Navegacao Padrao"));
		navOpenType.add(new SelectItem("", "Modal Padrao"));
		navOpenType.add(new SelectItem("modal-sm", "Modal Pequeno"));
		navOpenType.add(new SelectItem("modal-lg", "Modal Grande"));
		
		
		for (SelectItem selectItem : navOpenType) {
			if (selectItem.getValue().equals(openType)) {
				selectItem.setSelected("checked");
			}
		}
		
		return navOpenType;
	}

	public List<SelectItem> getAlignmentOptions() {
		
		alignmentOptions.clear();
		
		alignmentOptions.add(new SelectItem("float-left", "Esquerda"));
		alignmentOptions.add(new SelectItem("float-right", "Direita"));
		
		for (SelectItem selectItem : alignmentOptions) {
			if (selectItem.getValue().equals(alignment)) {
				selectItem.setSelected("checked");
			}
		}
		
		return alignmentOptions;
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
