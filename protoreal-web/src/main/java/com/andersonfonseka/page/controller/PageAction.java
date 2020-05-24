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
	
	public ActionForward startCreate(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PageForm pageForm = new PageForm(request.getSession().getAttribute("siteId").toString());
		
		request.getSession().setAttribute("pageForm", pageForm);
		
	    return mapping.findForward("successCreateForm");
	}

	public ActionForward startEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PageRepository repository = PageRepository.getInstance();
		Page page = repository.get(request.getParameter("id"));
		
		PageForm pageForm = new PageForm(page.getSite().getUuid());
		
		pageForm.setUuid(page.getUuid());
		pageForm.setTitle(page.getTitle());
		pageForm.setOp("U");
		pageForm.setDescription(page.getDescription());
		pageForm.setDisplayOnMenu(page.isDisplayOnMenu());
		pageForm.setPageType(page.getType());
		pageForm.setContainerType(page.getContainerType());
		pageForm.setHideMenu(page.isHideMenu());
		pageForm.setShowTitle(page.isShowTitle());
		
		pageForm.setCheckDisplayMenu(String.valueOf(page.isDisplayOnMenu()));
		pageForm.setCheckHideMenu(String.valueOf(page.isHideMenu()));
		pageForm.setCheckShowTitle(String.valueOf(page.isShowTitle()));
		
		request.getSession().setAttribute("pageForm", pageForm);

		return mapping.findForward("successEditForm");
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

		if (pageForm.getOp().equals("X")) {
			request.setAttribute("pages", site.getPages());
			return mapping.findForward("success");
		}
		
		
		Page parentPage = null;

		if (null != pageForm.getParentPage()) {
			parentPage = pageRepository.get(pageForm.getParentPage());
		}

		if (pageForm.getOp().equals("C")) {

			Page page = new Page(pageForm.getName());

			page.setName(pageForm.getName());
			page.setTitle(pageForm.getTitle());
			page.setDisplayOnMenu(pageForm.isDisplayOnMenu());
			page.setDescription(pageForm.getDescription());
			page.setType(pageForm.getPageType());
			page.setContainerType(pageForm.getContainerType());
			page.setHideMenu(pageForm.isHideMenu());
			page.setShowTitle(pageForm.isShowTitle());
			
			
			if (null != site && null != parentPage) {

				page.setParent(parentPage);
				site.addChild(page);
			
				parentPage.addChild(page);

			} else {

				page.setParent(site);
				site.addChild(page);
			}

			pageRepository.add(page);

		} else if (pageForm.getOp().equals("U")) {
			
			Page page = pageRepository.get(pageForm.getUuid());
			
			page.setName(pageForm.getName());
			page.setTitle(pageForm.getTitle());
			page.setDisplayOnMenu(new Boolean(pageForm.getCheckDisplayMenu()));
			page.setDescription(pageForm.getDescription());
			page.setType(pageForm.getPageType());
			page.setContainerType(pageForm.getContainerType());
			page.setHideMenu(new Boolean(pageForm.getCheckHideMenu()));
			page.setShowTitle(new Boolean(pageForm.getCheckShowTitle()));
			
			if (null != site && null != parentPage) {

				if (page.getParent() != null) {
					page.getParent().removeChild(page);
				}
				
				page.setParent(parentPage);
				parentPage.addChild(page);

			} else {
				page.setParent(site);
			}

			pageRepository.edit(page);
			
		}

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
