package com.andersonfonseka.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.andersonfonseka.protoreal.components.Site;

public class SiteRepository {
	
	private static SiteRepository INSTANCE;

	private Map<String, Site> respository = new HashMap<String, Site>();
	
	private SiteRepository() {}
	
	public static SiteRepository getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new SiteRepository();
		}

		return INSTANCE;
	}
	
	public void add(Site site) {
		this.respository.put(site.getUuid(), site);
	}
	
	public List<Site> list(){
		return new ArrayList<Site>(this.respository.values());
	}
	
	public void edit(Site site) {
		this.respository.put(site.getUuid(), site);
	}
	
	public void remove(String uuid) {
		this.respository.remove(uuid);
	}
	
	public Site get(String uuid) {
		return this.respository.get(uuid);
	}
}
