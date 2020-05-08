package com.andersonfonseka.page.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.andersonfonseka.dao.PageRepository;
import com.andersonfonseka.dao.SiteRepository;
import com.andersonfonseka.page.form.DesignForm;
import com.andersonfonseka.page.form.PageForm;
import com.andersonfonseka.protoreal.components.Page;
import com.andersonfonseka.protoreal.components.Site;

public class PageAction extends DispatchAction {

	public ActionForward main(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		request.getSession().setAttribute("siteId", request.getParameter("siteId"));
		
		SiteRepository siteRepository = SiteRepository.getInstance();
		Site site = siteRepository.get(request.getParameter("siteId"));

		request.getSession().setAttribute("site", site);
		request.setAttribute("pages", site.getPages());

		return mapping.findForward("success");
	}

	public ActionForward startEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		PageForm pageForm = new PageForm(request.getSession().getAttribute("siteId").toString());
		request.getSession().setAttribute("pageForm", pageForm);
		
		return mapping.findForward("successForm");
	}
	
	public ActionForward startDesign(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.getSession().setAttribute("pageId", request.getParameter("id"));
		
		PageRepository repository = PageRepository.getInstance();
		Page page = repository.get(request.getParameter("id"));
		
		DesignForm designForm = new DesignForm();
		designForm.setComponentList(page.getFastComponents());
		
		request.getSession().setAttribute("designForm", designForm);
		
		request.getSession().setAttribute("page", page);
		request.setAttribute("pageRendered", page.doRender());
		
		return mapping.findForward("successDesign");
	}

	public ActionForward create(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		SiteRepository siteRepository = SiteRepository.getInstance();
		PageRepository pageRepository = PageRepository.getInstance();

		PageForm pageForm = (PageForm) form;
		
		Site site = siteRepository.get(request.getSession().getAttribute("siteId").toString());
		
		Page parentPage = null;
		
		if (null != pageForm.getParentPage()) {
			parentPage = pageRepository.get(pageForm.getParentPage());
		}
		
		Page page = new Page(pageForm.getName());
		
		page.setName(pageForm.getName());
		page.setTitle(pageForm.getTitle());
		page.setDisplayOnMenu(pageForm.isDisplayOnMenu());
		page.setDescription(pageForm.getDescription());
		page.setType(pageForm.getPagetType());
		page.setContainerType(pageForm.getContainerType());
		
		if (null != site && null != parentPage) {
			
			page.setParent(parentPage);
			site.addChild(page);
			parentPage.addChild(page);
			
		} else {
			
			page.setParent(site);
			site.addChild(page);
		}
		
		pageRepository.add(page);
		
		request.setAttribute("pages", site.getPages());
		
		pageForm.reset(mapping, request);

		return mapping.findForward("success");
	}

	public ActionForward remove(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PageRepository repository = PageRepository.getInstance();
		Page pageToBeRemoved = repository.get(request.getParameter("id"));
		
		pageToBeRemoved.getParent().removeChild(pageToBeRemoved);
		repository.remove(pageToBeRemoved.getUuid());
		
		Page page = new Page();
		page.setUuid(request.getParameter("id"));
		
		SiteRepository siteRepository = SiteRepository.getInstance();
		Site site = siteRepository.get(request.getSession().getAttribute("siteId").toString());
		site.removeChild(page);
		
		request.setAttribute("pages", site.getPages());

		return mapping.findForward("success");
	}

}
