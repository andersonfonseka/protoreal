package com.andersonfonseka.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.andersonfonseka.protoreal.components.Component;

public class ComponentRepository {

	private Map<String, Repository> repositories = new HashMap<String, Repository>();
	
	public ComponentRepository() {
		this.repositories.put("com.andersonfonseka.protoreal.components.Container", new ContainerRepository());
		this.repositories.put("com.andersonfonseka.protoreal.components.Row", new RowsRepository());
		this.repositories.put("com.andersonfonseka.protoreal.components.Cell", new CellRepository());
		this.repositories.put("com.andersonfonseka.protoreal.components.TextInput", new TextInputRepository());
		this.repositories.put("com.andersonfonseka.protoreal.components.SelectInput", new SelectInputRepository());
		this.repositories.put("com.andersonfonseka.protoreal.components.Button", new ButtonRepository());
		this.repositories.put("com.andersonfonseka.protoreal.components.Label", new LabelRepository());
		this.repositories.put("com.andersonfonseka.protoreal.components.TextAreaInput", new TextAreaInputRepository());
		
	}
	
	public void add(Component component) {
		
		String INSERT_SITE = "INSERT INTO COMPONENTS (UUID, PARENT, TYPE, SITEUUID) VALUES (?,?,?,?) ";
		
		Connection connection = null;
		PreparedStatement pstmt = null;

		
		try {
			
			connection = DbConnection.getInstance().getConnection();
			
			pstmt = connection.prepareStatement(INSERT_SITE);
			
			pstmt.setString(1, component.getUuid());
			
			if (component.getParent() != null) {
				pstmt.setString(2, component.getParent().getUuid());
			} else {
				pstmt.setNull(2, java.sql.Types.NULL);
			}
			
			pstmt.setString(3, component.getClass().getName());
			pstmt.setString(4, component.getSiteUuid());
		
			pstmt.execute();
			pstmt.close();
			
			this.repositories.get(component.getClass().getName()).add(component);
			
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
	
	public List<Component> list(String uuid){
		
		List<Component> results = new ArrayList<Component>();
		
		String SELECT_ALL = "SELECT * FROM COMPONENTS WHERE PARENT = ?";
		PreparedStatement pstmt = null;
		Connection connection = null;
		
		try {
			
			connection = DbConnection.getInstance().getConnection();
			
			pstmt = connection.prepareStatement(SELECT_ALL);
			
			pstmt.setString(1, uuid);
			
			ResultSet resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				
				String type = resultSet.getString(3);
				results.add(this.repositories.get(type).get(resultSet.getString(1)));
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

	
	public void remove(String uuid) {
		//this.repository.remove(uuid);
	}
	
	public Component get(String uuid) {
		
		Component component = null;
		
		String GET_SITE = "SELECT * FROM COMPONENTS WHERE UUID=?";
		PreparedStatement pstmt = null;
		Connection connection = null;
		
		try {
			
			connection = DbConnection.getInstance().getConnection();
			
			pstmt = connection.prepareStatement(GET_SITE);
			
			pstmt.setString(1, uuid);
			
			ResultSet resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				
				component = new Component() {
					
					@Override
					public String doRender() {
						// TODO Auto-generated method stub
						return null;
					}
					
					@Override
					public String doPreview() {
						// TODO Auto-generated method stub
						return null;
					}
					
					@Override
					public String doEdit() {
						// TODO Auto-generated method stub
						return null;
					}
				};
				
				component.setUuid(resultSet.getString(1));
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
		
		return component;
		
	}
}
