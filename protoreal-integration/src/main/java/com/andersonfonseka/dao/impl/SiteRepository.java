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
import com.andersonfonseka.protoreal.components.Site;

public class SiteRepository {
	
	private static SiteRepository INSTANCE;

	private Map<String, Site> repository = new HashMap<String, Site>();
	
	private static Connection connection = null;
	
	private SiteRepository() {}
	
	public static SiteRepository getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new SiteRepository();
		}

		return INSTANCE;
	}
	
	public void add(Site site) {
		
		this.repository.put(site.getUuid(), site);
		
		String INSERT_SITE = "INSERT INTO SITE (UUID, NAME, TITLE, DESCRIPTION, INITIALPAGE) VALUES (?,?,?,?,?) ";
		PreparedStatement pstmt = null;
		
		try {
			
			connection = DbConnection.getInstance().getConnection();
			
			pstmt = connection.prepareStatement(INSERT_SITE);
			
			pstmt.setString(1, site.getUuid());
			pstmt.setString(2, site.getName());
			pstmt.setString(3, site.getTitle());
			pstmt.setString(4, site.getDescription());
			pstmt.setString(5, site.getInitialPage());
			
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
	
	public List<Site> list(){
		
		List<Site> results = new ArrayList<Site>();
		
		String SELECT_ALL = "SELECT * FROM SITE";
		PreparedStatement pstmt = null;
		
		try {
			
			connection = DbConnection.getInstance().getConnection();
			
			pstmt = connection.prepareStatement(SELECT_ALL);
			
			ResultSet resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				
				Site site = new Site();
				
				site.setUuid(resultSet.getString(1));
				site.setName(resultSet.getString(2));
				site.setTitle(resultSet.getString(3));
				site.setDescription(resultSet.getString(4));
				site.setInitialPage(resultSet.getString(5));
				
				results.add(site);
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
	
	public void edit(Site site) {
		
		this.repository.put(site.getUuid(), site);
		
		String UPDATE_SITE = "UPDATE SITE SET NAME=?, TITLE=?, DESCRIPTION=?, INITIALPAGE=? WHERE UUID=? ";
		PreparedStatement pstmt = null;
		
		try {
			
			connection = DbConnection.getInstance().getConnection();
			
			pstmt = connection.prepareStatement(UPDATE_SITE);
			
			pstmt.setString(1, site.getName());
			pstmt.setString(2, site.getTitle());
			pstmt.setString(3, site.getDescription());
			pstmt.setString(4, site.getInitialPage());
			pstmt.setString(5, site.getUuid());
			
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
	
	public Site get(String uuid) {
		
		Site site = new Site();
		
		String GET_SITE = "SELECT * FROM SITE WHERE UUID=?";
		PreparedStatement pstmt = null;
		
		try {
			
			connection = DbConnection.getInstance().getConnection();
			
			pstmt = connection.prepareStatement(GET_SITE);
			
			pstmt.setString(1, uuid);
			
			ResultSet resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {

				site.setUuid(resultSet.getString(1));
				site.setName(resultSet.getString(2));
				site.setTitle(resultSet.getString(3));
				site.setDescription(resultSet.getString(4));
				site.setInitialPage(resultSet.getString(5));
			
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
		
		return site;
		
	}
}
