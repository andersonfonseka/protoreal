package com.andersonfonseka.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.andersonfonseka.dao.DbConnection;
import com.andersonfonseka.protoreal.components.Component;
import com.andersonfonseka.protoreal.components.Page;

public class PageRepository {
	
	private static PageRepository INSTANCE;

	private Map<String, Page> repository = new HashMap<String, Page>();
	
	private static Connection connection = null;
	
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
		this.repository.put(page.getUuid(), page);
		
		String INSERT_PAGE = "INSERT INTO PAGE (UUID, NAME, TITLE, DESCRIPTION, DISPLAYONMENU, HIDEMENU, SHOWTITLE, CONTAINERTYPE, PARENT, SITEUUID) VALUES (?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		
		try {
			
			connection = DbConnection.getInstance().getConnection();
			
			pstmt = connection.prepareStatement(INSERT_PAGE);
			
			pstmt.setString(1, page.getUuid());
			pstmt.setString(2, page.getName());
			pstmt.setString(3, page.getTitle());
			pstmt.setString(4, page.getDescription());
			pstmt.setString(5, Boolean.valueOf(page.isDisplayOnMenu()).toString());
			pstmt.setString(6, Boolean.valueOf(page.isHideMenu()).toString());
			pstmt.setString(7, Boolean.valueOf(page.isShowTitle()).toString());
			pstmt.setString(8, page.getContainerType());
			pstmt.setString(9, page.getParent());
			pstmt.setString(10, page.getSiteUuid());
			
			
			pstmt.execute();
			pstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	List<Component> results = new ArrayList<Component>();
	
	public List<Component> list(String uuid){
		
		this.results.clear();
		
		String SELECT_ALL = "SELECT * FROM PAGE WHERE SITEUUID = ?";
		PreparedStatement pstmt = null;
		
		try {
			
			connection = DbConnection.getInstance().getConnection();
			
			pstmt = connection.prepareStatement(SELECT_ALL);
			
			pstmt.setString(1, uuid);
			
			ResultSet resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				
				Page page = new Page();
				
				page.setUuid(resultSet.getString(1));
				page.setName(resultSet.getString(2));
				page.setTitle(resultSet.getString(3));
				page.setDescription(resultSet.getString(4));
				page.setDisplayOnMenu(new Boolean(resultSet.getString(5)));
				page.setHideMenu(new Boolean(resultSet.getString(6)));
				page.setShowTitle(new Boolean(resultSet.getString(7)));
				page.setContainerType(resultSet.getString(8));
				
				Page parent = new Page();
						parent.setUuid(resultSet.getString(9));
				
				page.setParent(parent.getUuid());
				
				page.setSiteUuid(resultSet.getString(10));
				
				if (page.getParent().equals(page.getSiteUuid())) {
					list(page.getUuid(), page.getChildrenList());
				}
				
				results.add(page);
			}
			
			pstmt.execute();
			pstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return results;
	}
	
	public List<Component> list(String uuid, List<Component> results){
				
		String SELECT_ALL = "SELECT * FROM PAGE WHERE PARENT = ?";
		PreparedStatement pstmt = null;
		
		try {
			
			connection = DbConnection.getInstance().getConnection();
			
			pstmt = connection.prepareStatement(SELECT_ALL);
			
			pstmt.setString(1, uuid);
			
			ResultSet resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				
				Page page = new Page();
				
				page.setUuid(resultSet.getString(1));
				page.setName(resultSet.getString(2));
				page.setTitle(resultSet.getString(3));
				page.setDescription(resultSet.getString(4));
				page.setDisplayOnMenu(new Boolean(resultSet.getString(5)));
				page.setHideMenu(new Boolean(resultSet.getString(6)));
				page.setShowTitle(new Boolean(resultSet.getString(7)));
				page.setContainerType(resultSet.getString(8));
				
				Page parent = new Page();
						parent.setUuid(resultSet.getString(9));
				
				page.setParent(parent.getUuid());
				
				page.setSiteUuid(resultSet.getString(10));
				results.add(page);
			}
			
			pstmt.execute();
			pstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return results;
	}
	
	public List<Component> listComponents(String uuid) {
		
		ComponentRepository componentRepository = new ComponentRepository();
		
		List<Component> results = new ArrayList<Component>();
		
		String SELECT_ALL = "SELECT * FROM COMPONENTS WHERE PAGEUUID = ?";
		PreparedStatement pstmt = null;
		
		try {
			
			connection = DbConnection.getInstance().getConnection();
			
			pstmt = connection.prepareStatement(SELECT_ALL);
			
			pstmt.setString(1, uuid);
			
			ResultSet resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				
				Component component = componentRepository.get(resultSet.getString(1));
				results.add(component);
			}
			
			pstmt.execute();
			pstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return results;
	}
	
	public void edit(Page page) {
		this.repository.put(page.getUuid(), page);
		
		String INSERT_PAGE = "UPDATE PAGE SET NAME=?, TITLE=?, DESCRIPTION=?, DISPLAYONMENU=?, HIDEMENU=?, SHOWTITLE=?, CONTAINERTYPE=?, PARENT=? WHERE UUID=?";
		PreparedStatement pstmt = null;
		
		try {
			
			connection = DbConnection.getInstance().getConnection();
			
			pstmt = connection.prepareStatement(INSERT_PAGE);
			
			pstmt.setString(1, page.getName());
			pstmt.setString(2, page.getTitle());
			pstmt.setString(3, page.getDescription());
			pstmt.setString(4, Boolean.valueOf(page.isDisplayOnMenu()).toString());
			pstmt.setString(5, Boolean.valueOf(page.isHideMenu()).toString());
			pstmt.setString(6, Boolean.valueOf(page.isShowTitle()).toString());
			pstmt.setString(7, page.getContainerType());
			pstmt.setString(8, page.getParent());
			pstmt.setString(9, page.getUuid());
			
			pstmt.execute();
			pstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void remove(String uuid) {
		this.repository.remove(uuid);
	}
	
	public Page get(String uuid) {
		
		Page page = null;
		
		String SELECT_ALL = "SELECT * FROM PAGE WHERE UUID = ?";
		PreparedStatement pstmt = null;
		
		try {
			
			connection = DbConnection.getInstance().getConnection();
			
			pstmt = connection.prepareStatement(SELECT_ALL);
			
			pstmt.setString(1, uuid);
			
			ResultSet resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				
				page = new Page();
				
				page.setUuid(resultSet.getString(1));
				page.setName(resultSet.getString(2));
				page.setTitle(resultSet.getString(3));
				page.setDescription(resultSet.getString(4));
				page.setDisplayOnMenu(new Boolean(resultSet.getString(5)));
				page.setHideMenu(new Boolean(resultSet.getString(6)));
				page.setShowTitle(new Boolean(resultSet.getString(7)));
				page.setContainerType(resultSet.getString(8));
				
				Page parent = new Page();
						parent.setUuid(resultSet.getString(9));
				
				page.setParent(parent.getUuid());
				page.setParentComponent(parent);
				
				page.setSiteUuid(resultSet.getString(10));
				
				page.setChildren(list(page.getUuid(), page.getChildrenList()));

			}
			
			pstmt.execute();
			pstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return page;
	}
	
	public Page getFull(String uuid) {
		
		Page page = get(uuid);
		page.setChildren(componentRepository.list(page.getUuid()));
		return page;
		
	}
	
}
