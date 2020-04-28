package com.andersonfonseka.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.andersonfonseka.dao.PageRepository;
import com.andersonfonseka.dao.SiteRepository;
import com.andersonfonseka.project.form.ProjectForm;
import com.andersonfonseka.protoreal.components.Page;
import com.andersonfonseka.protoreal.components.Site;

public class ProjectAction extends DispatchAction {

	public ActionForward main(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		SiteRepository repository = SiteRepository.getInstance();
		
		request.setAttribute("projects", repository.list());
		
	    return mapping.findForward("success");
	}
	
	public ActionForward startEdit(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	    return mapping.findForward("successForm");
	}
	
	public ActionForward create(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ProjectForm projectForm = (ProjectForm) form;
		
		Site site = new Site();
		
		site.setName(projectForm.getName());
		site.setTitle(projectForm.getTitle());
		site.setDescription(projectForm.getDescription());

		SiteRepository repository = SiteRepository.getInstance();

		repository.add(site);	
		request.setAttribute("projects", repository.list());
		
	    return mapping.findForward("success");
	}
	
	public ActionForward remove(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		SiteRepository repository = SiteRepository.getInstance();
		repository.remove(request.getParameter("id"));
		
		request.setAttribute("projects", repository.list());
		
	    return mapping.findForward("success");
	}
	
	public ActionForward preview(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		SiteRepository repository = SiteRepository.getInstance();
		Site site = repository.get(request.getParameter("siteId"));
		
		request.setAttribute("sitePreview", site.doRender());

		if (request.getParameter("pageId") != null) {
			
			PageRepository pageRepository = PageRepository.getInstance();
			Page page = pageRepository.get(request.getParameter("pageId"));
			
			request.setAttribute("pageRendered", page.doPreview());
		} else {
			request.setAttribute("pageRendered", "");
		}
		
		return mapping.findForward("successPreview");
	}


	
}
