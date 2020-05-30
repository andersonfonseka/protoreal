package com.andersonfonseka.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.jdbi.v3.core.Jdbi;

import com.andersonfonseka.dao.DbConnection;
import com.andersonfonseka.protoreal.components.Button;

class ButtonRepository implements Repository<Button> {
	
	private static Connection connection = null;
	
	private static Jdbi handle;	
	
	public ButtonRepository() {}
	
	public void add(Button button) {
			
		handle = DbConnection.getInstance().getHandle();
		handle.useHandle(handle -> {
					handle
						.createUpdate("INSERT INTO BUTTON (UUID, CSSCLASS, OPENTYPE, ALIGNMENT, PAGEUUID, LABEL, SITEUUID) VALUES (?,?,?,?,?,?,?)") 
							.bind(0, button.getUuid())
							.bind(1, button.getCssClass())
							.bind(2, button.getOpenType())
							.bind(3, button.getAlignment())
							.bind(4, button.getPageUuid())
							.bind(5, button.getLabel())
							.bind(6, button.getSiteUuid())
						.execute();
			});
	}
	
	public void remove(Button button) {}
	
	public Button get(String uuid) {
		
		Button button = null;
		
		String SELECT_ALL = "SELECT * FROM BUTTON WHERE UUID = ?";
		PreparedStatement pstmt = null;
		
		try {
			
			connection = DbConnection.getInstance().getConnection();
			
			pstmt = connection.prepareStatement(SELECT_ALL);
			
			pstmt.setString(1, uuid);
			
			ResultSet resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				
				button = new Button();
				
				button.setUuid(resultSet.getString(1));
				button.setCssClass(resultSet.getString(2));
				button.setOpenType(resultSet.getString(3));
				button.setAlignment(resultSet.getString(4));
				button.setPageUuid(resultSet.getString(5));
				button.setLabel(resultSet.getString(6));
				button.setSiteUuid(resultSet.getString(7));
				
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
		
		return button;
	}

	@Override
	public void edit(Button button) {
		
		String INSERT_PAGE = "UPDATE BUTTON SET CSSCLASS=?, OPENTYPE=?, ALIGNMENT=?, PAGEUUID=?, LABEL=?, SITEUUID=? WHERE UUID=?";
		PreparedStatement pstmt = null;
		
		try {
			
			connection = DbConnection.getInstance().getConnection();
			
			pstmt = connection.prepareStatement(INSERT_PAGE);
			
			pstmt.setString(7, button.getUuid());
			
			pstmt.setString(1, button.getCssClass());
			pstmt.setString(2, button.getOpenType());
			pstmt.setString(3, button.getAlignment());
			pstmt.setString(4, button.getPageUuid());
			pstmt.setString(5, button.getLabel());
			pstmt.setString(6, button.getSiteUuid());
			
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

	@Override
	public List<Button> list(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
