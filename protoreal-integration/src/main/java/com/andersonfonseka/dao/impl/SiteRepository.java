package com.andersonfonseka.dao.impl;

import java.util.List;

import org.jdbi.v3.core.Jdbi;

import com.andersonfonseka.dao.DbConnection;
import com.andersonfonseka.protoreal.components.Site;

public class SiteRepository {
	
	private static SiteRepository INSTANCE;
	
	private static Jdbi handle;	
	
	private SiteRepository() {}
	
	public static SiteRepository getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new SiteRepository();
		}
		return INSTANCE;
	}
	
	public void add(Site site) {
		
		handle = DbConnection.getInstance().getHandle();
		handle.useHandle(handle -> {
					handle
						.createUpdate("INSERT INTO SITE (UUID, NAME, TITLE, DESCRIPTION, INITIALPAGE) VALUES (?,?,?,?,?)") 
							.bind(0, site.getUuid())
							.bind(1, site.getName())
							.bind(2, site.getTitle())
							.bind(3, site.getDescription())
							.bind(4, site.getInitialPage())
						.execute();
			});
	}
	
	public List<Site> list(){
		
		handle = DbConnection.getInstance().getHandle();
		
		List<Site> results = handle.withHandle(handle -> 
			 handle.createQuery("SELECT * FROM SITE")
            .mapToBean(Site.class)
            .list());
		
		return results;
	}
	
	public void edit(Site site) {
		
		handle = DbConnection.getInstance().getHandle();
		handle.useHandle(handle -> {
					handle
						.createUpdate("UPDATE SITE SET NAME=?, TITLE=?, DESCRIPTION=?, INITIALPAGE=? WHERE UUID=?") 
							.bind(4, site.getUuid())
							.bind(0, site.getName())
							.bind(1, site.getTitle())
							.bind(2, site.getDescription())
							.bind(3, site.getInitialPage())
						.execute();
			});
	}
	
	public void remove(String uuid) {
		
		handle = DbConnection.getInstance().getHandle();
		
		handle.useHandle(handle -> 
			 handle.createUpdate("DELETE FROM SITE WHERE UUID=?")
			.bind(0, uuid)
            .execute());
		
	}
	
	public Site get(String uuid) {
		
		handle = DbConnection.getInstance().getHandle();
		
		Site site = handle.withHandle(handle -> 
			 handle.createQuery("SELECT * FROM SITE WHERE UUID=?")
			.bind(0, uuid)
            .mapToBean(Site.class)
            .findOnly());
		
		return site;
		
	}
}
