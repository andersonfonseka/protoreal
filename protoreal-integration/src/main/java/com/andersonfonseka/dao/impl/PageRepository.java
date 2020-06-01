package com.andersonfonseka.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.jdbi.v3.core.Jdbi;

import com.andersonfonseka.dao.DbConnection;
import com.andersonfonseka.protoreal.components.Component;
import com.andersonfonseka.protoreal.components.Page;

public class PageRepository extends RepositoryImpl implements Repository {
	
	private static PageRepository INSTANCE;
	
	private static Jdbi handle;	
	
	private ComponentRepository componentRepository;
	
	private PageRepository() {
		componentRepository = new ComponentRepository();
	}
	
	public static PageRepository getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new PageRepository();
		}

		return INSTANCE;
	}
	
	public void add(Page page) {
		
		handle = DbConnection.getInstance().getHandle();
		handle.useHandle(handle -> {
					handle
						.createUpdate("INSERT INTO PAGE (UUID, NAME, TITLE, DESCRIPTION, DISPLAYONMENU, HIDEMENU, SHOWTITLE, CONTAINERTYPE, PARENT, SITEUUID) VALUES (?,?,?,?,?,?,?,?,?,?)") 
							.bind(0, page.getUuid())
							.bind(1, page.getName())
							.bind(2, page.getTitle())
							.bind(3, page.getDescription())
							.bind(4, Boolean.valueOf(page.isDisplayOnMenu()).toString())
							.bind(5, Boolean.valueOf(page.isHideMenu()).toString())
							.bind(6, Boolean.valueOf(page.isShowTitle()).toString())
							.bind(7, page.getContainerType())
							.bind(8, page.getParent())
							.bind(9, page.getSiteUuid())
						.execute();
			});
		
	}

	List<Component> results = new ArrayList<Component>();
	
	public List<Component> list(String uuid){
		
		this.results.clear();
		
		handle = DbConnection.getInstance().getHandle();
		
		List<Page> resultPage = handle.withHandle(handle -> 
			 handle.createQuery("SELECT * FROM PAGE WHERE SITEUUID = ?")
			 .bind(0, uuid)
             .mapToBean(Page.class)
            .list());
		
		for (Page page : resultPage) {
			
			Page parent = new Page();
			
			parent.setUuid(page.getParent());
	
			page.setParent(parent.getUuid());
			
			if (page.getParent().equals(page.getSiteUuid())) {
				list(page.getUuid(), page.getChildrenList());
			}
			
			results.add(page);
		}
		
		return results;
	}
	
	public List<Component> list(String uuid, List<Component> results){
		
		handle = DbConnection.getInstance().getHandle();
		
		List<Page> resultPage = handle.withHandle(handle -> 
			 handle.createQuery("SELECT * FROM PAGE WHERE PARENT = ?")
			 .bind(0, uuid)
             .mapToBean(Page.class)
            .list());
				
		for (Page page : resultPage) {
			
			Page parent = new Page();
			
			parent.setUuid(page.getParent());
	
			page.setParent(parent.getUuid());
			
			if (page.getParent().equals(page.getSiteUuid())) {
				list(page.getUuid(), page.getChildrenList());
			}
			
			results.add(page);
		}
		
		return results;
	}
	
	public List<Component> listComponents(String uuid) {
		
		ComponentRepository componentRepository = new ComponentRepository();
		
		List<Component> results = new ArrayList<Component>();
		
		List<Component> resultComponent = handle.withHandle(handle -> 
		 			handle.createQuery("SELECT * FROM COMPONENTS WHERE PAGEUUID = ?")
					.bind(0, uuid)
			        .mapToBean(Component.class)
			       .list());
		
		for (Component comp : resultComponent) {
			
			Component component = componentRepository.get(comp.getUuid());
			results.add(component);
		}
		
		return results;
	}
	
	public void edit(Page page) {
		
		handle = DbConnection.getInstance().getHandle();
		handle.useHandle(handle -> {
					handle
						.createUpdate("UPDATE PAGE SET NAME=?, TITLE=?, DESCRIPTION=?, DISPLAYONMENU=?, HIDEMENU=?, SHOWTITLE=?, CONTAINERTYPE=?, PARENT=? WHERE UUID=?") 
							.bind(9, page.getUuid())
							.bind(0, page.getName())
							.bind(1, page.getTitle())
							.bind(2, page.getDescription())
							.bind(3, Boolean.valueOf(page.isDisplayOnMenu()).toString())
							.bind(4, Boolean.valueOf(page.isHideMenu()).toString())
							.bind(5, Boolean.valueOf(page.isShowTitle()).toString())
							.bind(6, page.getContainerType())
							.bind(7, page.getParent())
							.bind(8, page.getSiteUuid())
						.execute();
			});
		
		
	}
	
	public Page get(String uuid) {
		
		Page page = (Page) get(uuid, "SELECT * FROM PAGE WHERE UUID = ?", Page.class);
		
		Page parent = new Page();
		
		if (null != page) {

			parent.setUuid(page.getParent());

			page.setParent(parent.getUuid());
			page.setParentComponent(parent);
			
			page.setChildren(list(page.getUuid(), page.getChildrenList()));
			
		}
		
		return page;
	}
	
	public Page getFull(String uuid) {
		
		Page page = get(uuid);
		page.setChildren(componentRepository.list(page.getUuid()));
		return page;
		
	}

	@Override
	public void add(Component component) {}

	@Override
	public void edit(Component component) {}

	@Override
	public void remove(Component component) {
		remove(component.getUuid(), "DELETE FROM PAGE WHERE UUID = ?");
	}
	
}
