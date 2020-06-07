package com.andersonfonseka.dao;

import java.util.ArrayList;
import java.util.List;

import org.jdbi.v3.core.Jdbi;

import com.andersonfonseka.Component;
import com.andersonfonseka.IComponent;
import com.andersonfonseka.IPage;
import com.andersonfonseka.Page;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageRepository extends RepositoryImpl implements Repository<IPage> {
	
	private static PageRepository INSTANCE;
	
	private static Jdbi handle;	
	
	private String mode = "run";
	
	private ComponentRepository componentRepository;
	
	public PageRepository() {}
	
	
	public void add(IPage page) {
		
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

	List<IPage> results = new ArrayList<IPage>();
	
	public List<IPage> list(String uuid){
		
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
	
	public List<IComponent> list(String uuid, List<IComponent> results){
		
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
	
	public List<IComponent> listComponents(String uuid) {
		
		ComponentRepository componentRepository = new ComponentRepository();
		
		List<IComponent> results = new ArrayList<IComponent>();
		
		List<Component> resultComponent = handle.withHandle(handle -> 
		 			handle.createQuery("SELECT * FROM COMPONENTS WHERE PAGEUUID = ? ORDER BY TIMESTAMP")
					.bind(0, uuid)
			        .mapToBean(Component.class)
			       .list());
		
		for (IComponent comp : resultComponent) {
			
			IComponent component = componentRepository.get(comp.getUuid());
			results.add(component);
		}
		
		return results;
	}
	
	public void edit(IPage page) {
		
		handle = DbConnection.getInstance().getHandle();
		handle.useHandle(handle -> {
					handle
						.createUpdate("UPDATE PAGE SET NAME=?, TITLE=?, DESCRIPTION=?, DISPLAYONMENU=?, HIDEMENU=?, SHOWTITLE=?, CONTAINERTYPE=?, PARENT=?, SITEUUID=? WHERE UUID=?") 
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
		
		Page page = (Page) get(getMode(), uuid, "SELECT * FROM PAGE WHERE UUID = ?", Page.class);
		
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
	
	public void remove(IPage component) {
		remove(getMode(), component.getUuid(), "DELETE FROM PAGE WHERE UUID = ?");
	}
	
	public void setMode(String mode) {
		this.mode = mode;
		
		if (this.mode.equals(DbConnection.TEST_MODE)) {
			
			handle = DbConnection.getInstance(getMode()).getHandle();
			
			handle.useHandle(handle -> {
				handle.execute("CREATE TABLE IF NOT EXISTS PAGE (UUID VARCHAR, NAME VARCHAR, TITLE VARCHAR, DESCRIPTION VARCHAR, DISPLAYONMENU VARCHAR, HIDEMENU VARCHAR, SHOWTITLE VARCHAR, CONTAINERTYPE VARCHAR, PARENT VARCHAR, SITEUUID VARCHAR)");
			});
		}
	}	
	
}
