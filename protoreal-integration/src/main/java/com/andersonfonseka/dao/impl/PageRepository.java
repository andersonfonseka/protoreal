package com.andersonfonseka.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.andersonfonseka.protoreal.components.Page;

public class PageRepository {
	
	private static PageRepository INSTANCE;

	private Map<String, Page> repository = new HashMap<String, Page>();
	
	private static Connection connection = null;
	
	private DatabaseProps props = new DatabaseProps();
	
	private PageRepository() {
		
		props.setUrl("jdbc:mysql://localhost/protoreal");
		props.setPwd("root");
		props.setUser("root");
		
	}
	
	public static PageRepository getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new PageRepository();
		}

		return INSTANCE;
	}
	
	public void add(Page page) {
		this.repository.put(page.getUuid(), page);
		
		String INSERT_PAGE = "INSERT INTO PAGE (UUID, NAME, TITLE, DESCRIPTION, DISPLAYONMENU, HIDEMENU, SHOWTITLE, CONTAINERTYPE, PARENT) VALUES (?,?,?,?,?,?,?,?, ?) ";
		PreparedStatement pstmt = null;
		
		try {
			
			connection = DbConnection.getInstance().getConnection(props);
			
			pstmt = connection.prepareStatement(INSERT_PAGE);
			
			pstmt.setString(1, page.getUuid());
			pstmt.setString(2, page.getName());
			pstmt.setString(3, page.getTitle());
			pstmt.setString(4, page.getDescription());
			pstmt.setString(5, Boolean.valueOf(page.isDisplayOnMenu()).toString());
			pstmt.setString(6, Boolean.valueOf(page.isHideMenu()).toString());
			pstmt.setString(7, Boolean.valueOf(page.isShowTitle()).toString());
			pstmt.setString(8, Boolean.valueOf(page.isDisplayOnMenu()).toString());
			pstmt.setString(9, page.getParent().getUuid());
			
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
	
	public List<Page> list(){
		
		List<Page> pages = new ArrayList<Page>();
		
		if (!this.repository.values().isEmpty()) {
			pages = new ArrayList<Page>(this.repository.values());
		}
		
		return pages;
	}
	
	public void edit(Page page) {
		this.repository.put(page.getUuid(), page);
		
		String INSERT_PAGE = "UPDATE PAGE SET NAME=?, TITLE=?, DESCRIPTION=?, DISPLAYONMENU=?, HIDEMENU=?, SHOWTITLE=?, CONTAINERTYPE=?, PARENT=? WHERE UUID=?";
		PreparedStatement pstmt = null;
		
		try {
			
			connection = DbConnection.getInstance().getConnection(props);
			
			pstmt = connection.prepareStatement(INSERT_PAGE);
			
			pstmt.setString(1, page.getName());
			pstmt.setString(2, page.getTitle());
			pstmt.setString(3, page.getDescription());
			pstmt.setString(4, Boolean.valueOf(page.isDisplayOnMenu()).toString());
			pstmt.setString(5, Boolean.valueOf(page.isHideMenu()).toString());
			pstmt.setString(6, Boolean.valueOf(page.isShowTitle()).toString());
			pstmt.setString(7, Boolean.valueOf(page.isDisplayOnMenu()).toString());
			pstmt.setString(8, page.getParent().getUuid());
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
		return this.repository.get(uuid);
	}
}
