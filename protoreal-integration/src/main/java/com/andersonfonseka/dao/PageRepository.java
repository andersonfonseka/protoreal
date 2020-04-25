package com.andersonfonseka.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.andersonfonseka.protoreal.components.Page;

public class PageRepository {
	
	private static PageRepository INSTANCE;

	private Map<String, Page> respository = new HashMap<String, Page>();
	
	private PageRepository() {}
	
	public static PageRepository getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new PageRepository();
		}

		return INSTANCE;
	}
	
	public void add(Page page) {
		this.respository.put(page.getUuid(), page);
	}
	
	public List<Page> list(){
		
		List<Page> pages = new ArrayList<Page>();
		
		if (!this.respository.values().isEmpty()) {
			pages = new ArrayList<Page>(this.respository.values());
		}
		
		return pages;
	}
	
	public void edit(Page page) {
		this.respository.put(page.getUuid(), page);
	}
	
	public void remove(String uuid) {
		this.respository.remove(uuid);
	}
	
	public Page get(String uuid) {
		return this.respository.get(uuid);
	}
}
